package es.uplace.uplace

import kotlinx.android.synthetic.main.activity_search.*

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.util.Log
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ArrayAdapter
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

class SearchActivity : AppCompatActivity() {

    private lateinit var searchPagerAdapter: SearchPageAdapter

    private val params: MutableMap<String, String> = HashMap()
    private var intentCity: String? = null
    private var intentCategory: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setSupportActionBar(toolbar_top)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
                this, R.array.dwelling_types, android.R.layout.simple_spinner_dropdown_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        filter_category_spinner.adapter = adapter

        getIntentExtras()

        findPropertiesByCriteria()
    }

    private fun getIntentExtras() {
        intentCity = intent.getStringExtra("city")
        intentCategory = intent.getStringExtra("category")
    }

    private fun findPropertiesByCriteria() {

        params.clear()

        intentCity?.let { params.put("city.equals", it) }
        intentCategory?.let { params.put("category.equals", it) }
        params["sort"] = "id,asc"

        val paramMap: Map<String, String> = HashMap(params)
        paramMap.forEach { p -> Log.d("ncs", "$p") }

        val propertyService = PropertyService.create()
        val call = propertyService.findPropertiesByCriteria(paramMap)
        Log.d("ncs", "Call: " + call.toString())

        call.enqueue(object : Callback<Content> {
            override fun onFailure(call: Call<Content>?, t: Throwable?) {
                Toast.makeText(this@SearchActivity, "Error en Callback", Toast.LENGTH_SHORT).show()
                pg_bar.visibility = ProgressBar.GONE
            }

            override fun onResponse(call: Call<Content>?, response: Response<Content>?) {
                if (response != null) {
                    val content: Content? = response.body()
                    val properties = content?.content!!
                    setupViewPager(properties)
                } else {
                    Toast.makeText(this@SearchActivity, getString(R.string.no_response), Toast.LENGTH_LONG).show()
                }
                pg_bar.visibility = ProgressBar.GONE
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        val filter: MenuItem? = menu?.findItem(R.id.action_filters)
        filter?.setOnMenuItemClickListener(
                FilterIconClickListener(this, container, AccelerateDecelerateInterpolator())
        )

        return super.onCreateOptionsMenu(menu)
    }

    private fun setupViewPager(properties: List<Property>?) {

        searchPagerAdapter = SearchPageAdapter(supportFragmentManager)

        val listSearchFragment = ListSearchFragment.instance(properties = ArrayList(properties))
        val mapFragment = MapSearchFragment()

        searchPagerAdapter.fragments = listOf(listSearchFragment, mapFragment)

        view_pager.adapter = searchPagerAdapter

        tabs.setupWithViewPager(view_pager)

        tabs.getTabAt(0)!!.setIcon(R.drawable.ic_list)
        tabs.getTabAt(1)!!.setIcon(R.drawable.ic_map)
    }
}
