package es.uplace.uplace

import kotlinx.android.synthetic.main.activity_search.*

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.support.v7.widget.SearchView
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import es.uplace.uplace.adapters.SearchPageAdapter
import es.uplace.uplace.domain.Content
import es.uplace.uplace.domain.Property

import es.uplace.uplace.fragments.ListSearchFragment
import es.uplace.uplace.fragments.MapSearchFragment
import es.uplace.uplace.retrofit.PropertyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {

    lateinit var searchPagerAdapter: SearchPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setSupportActionBar(toolbarTop)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        findPropertiesByCriteria()
    }

    private fun findPropertiesByCriteria() {

        val propertyService = PropertyService.create()
        val params: MutableMap<String, String>? = HashMap()
        params?.put("city.equals", "Barcelona")

        val paramMap: Map<String, String> = HashMap(params)
        paramMap.forEach { p -> Log.d("ncs", "$p") }

        val call = propertyService.findAllProperties(paramMap)
        Log.d("ncs", "Call: " + call.toString())

        call.enqueue(object : Callback<Content> {
            override fun onFailure(call: Call<Content>?, t: Throwable?) {
                Toast.makeText(this@SearchActivity, "Error en Callback", Toast.LENGTH_SHORT).show()
                Log.d("ncs", "Error en Callback: ${t.toString()}")
                pg_bar.visibility = ProgressBar.GONE
            }

            override fun onResponse(call: Call<Content>?, response: Response<Content>?) {
                if (response != null) {
                    val content: Content? = response.body()
                    Log.d("ncs", content.toString())

                    val properties = content?.content!!/*.filter { property -> property.visible }*/
//                    properties.forEach { p -> Log.d("ncs", p.toString()) }

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
        val search: MenuItem = menu!!.findItem(R.id.action_search)
        val searchView: SearchView = search.actionView as SearchView
        searchView.setQueryHint("Search");
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(p0: String?): Boolean {
                Toast.makeText(applicationContext, "Query text submit", Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextChange(p0: String?) = false

        })

        return super.onCreateOptionsMenu(menu)
    }

    private fun setupViewPager(properties: List<Property>?) {

        searchPagerAdapter = SearchPageAdapter(supportFragmentManager)

        val listSearchFragment = ListSearchFragment.instance(properties = ArrayList(properties))
        val mapFragment = MapSearchFragment()

        searchPagerAdapter.fragments = listOf(listSearchFragment, mapFragment)

        container.adapter = searchPagerAdapter

        tabs.setupWithViewPager(container)

        tabs.getTabAt(0)!!.setIcon(R.drawable.ic_view_list_black)
        tabs.getTabAt(1)!!.setIcon(R.drawable.ic_map_black)
    }
}