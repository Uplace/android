package es.uplace.uplace

import android.content.Context
import kotlinx.android.synthetic.main.activity_search.*

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import es.uplace.uplace.adapters.SearchPageAdapter
import es.uplace.uplace.domain.Content
import es.uplace.uplace.domain.Property

import es.uplace.uplace.fragments.ListSearchFragment
import es.uplace.uplace.fragments.MapSearchFragment
import es.uplace.uplace.retrofit.PropertyService
import kotlinx.android.synthetic.main.filter_drop.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.view.inputmethod.InputMethodManager


class SearchActivity : AppCompatActivity() {

    private lateinit var searchPagerAdapter: SearchPageAdapter

    var properties: ArrayList<Property> = arrayListOf()

    var listSearchFragment = ListSearchFragment.instance(properties)
    var mapFragment = MapSearchFragment.instance(properties)

    private val params: MutableMap<String, String> = HashMap()
    private var priceFrom: Int = 0
    private var priceTo: Int = 10000

    private var intentCity: String? = null
    private var intentCategory: String? = null

    var newSearch = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        Log.d("ncs", "onCreate")

        setSupportActionBar(toolbar_top)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        setupViewPager()

        for (i in 0 until filters_drop.childCount) {
            val v = filters_drop.getChildAt(i)
            val params = v.layoutParams as LinearLayout.LayoutParams
            params.bottomMargin = resources.getDimensionPixelSize(R.dimen.filter_item_margin_bottom)
            v.layoutParams = params
        }

        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                Constants.categories as List<CharSequence>?)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        filter_category_spinner.adapter = adapter

        filter_price_multislider.setOnThumbValueChangeListener(
                { _, _, thumbIndex, value ->
                    when (thumbIndex) {
                        0 -> priceFrom = value * 100
                        1 -> priceTo = value * 100
                    }
                    filter_price_text.text = "Price: $priceFrom€ - $priceTo€"
                }
        )

        getIntentExtras()

        makeCriteriaFromMain()
        findProperties()

        filter_done_button.setOnClickListener {
            if (!MainActivity().isCityValid(filter_city_edit_text.text)) {
                filter_city_text_input.error = getString(R.string.error_city)
            } else {
                filter_city_text_input.error = null

                params.clear()

                params["sort"] = "id,asc"
                params["city.equals"] = filter_city_edit_text.text.toString()
                if (filter_category_spinner.selectedItemPosition != 0)
                    params["category.equals"] = filter_category_spinner.selectedItem.toString()
                params["price.greaterOrEqualThan"] = priceFrom.toString()
                params["price.lessOrEqualThan"] = priceTo.toString()

                newSearch = true
                findProperties()
            }

            val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(currentFocus!!.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        val filter: MenuItem? = menu?.findItem(R.id.action_filters)
        filter?.setOnMenuItemClickListener(
                FilterIconClickListener(this, container, AccelerateDecelerateInterpolator())
        )

        return super.onCreateOptionsMenu(menu)
    }

    private fun getIntentExtras() {
        intentCity = intent.getStringExtra("city")
        filter_city_edit_text.setText(intentCity)
        intentCategory = intent.getStringExtra("category")
        if (intentCategory != null)
            filter_category_spinner.setSelection(
                    Constants.categories.indexOf(intentCategory!!)
            )
    }

    private fun setupViewPager() {

        searchPagerAdapter = SearchPageAdapter(supportFragmentManager)

        searchPagerAdapter.fragments = listOf(listSearchFragment, mapFragment)

        view_pager.adapter = searchPagerAdapter

        tabs.setupWithViewPager(view_pager)

        tabs.getTabAt(0)!!.setIcon(R.drawable.ic_list)
        tabs.getTabAt(1)!!.setIcon(R.drawable.ic_map)
    }

    private fun makeCriteriaFromMain() {
        params.clear()

        intentCity?.let { params["city.equals"] = it }
        intentCategory?.let { params["category.equals"] = it }
        params["sort"] = "id,asc"
    }

    private fun findProperties() {
        val paramMap: HashMap<String, String> = HashMap(params)
        val propertyService = PropertyService.create()
        val call = propertyService.findPropertiesByCriteria(paramMap)

        call.enqueue(object : Callback<Content> {
            override fun onFailure(call: Call<Content>?, t: Throwable?) {
                Toast.makeText(this@SearchActivity, "Error en Callback", Toast.LENGTH_SHORT).show()
                pg_bar.visibility = ProgressBar.GONE
            }

            override fun onResponse(call: Call<Content>?, response: Response<Content>?) {
                if (response != null) {
                    val content: Content? = response.body()
                    if (content?.content != null) {
                        properties.clear()
                        properties = content.content as ArrayList<Property>

                        listSearchFragment.updatePropertyList(properties)
                        mapFragment.updateProperties(properties, newSearch)
                    }
                } else {
                    Toast.makeText(this@SearchActivity, getString(R.string.no_response), Toast.LENGTH_LONG).show()
                }
                pg_bar.visibility = ProgressBar.GONE
            }
        })
    }
}
