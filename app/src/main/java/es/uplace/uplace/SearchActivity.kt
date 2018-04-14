package es.uplace.uplace

import kotlinx.android.synthetic.main.activity_search.*

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.support.v7.widget.SearchView
import android.widget.Toast
import es.uplace.uplace.adapters.SearchPageAdapter

import es.uplace.uplace.fragments.ListSearchFragment
import es.uplace.uplace.fragments.MapSearchFragment

class SearchActivity : AppCompatActivity() {

    lateinit var searchPagerAdapter: SearchPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setSupportActionBar(toolbarTop)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        setupViewPager()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        val search: MenuItem = menu!!.findItem(R.id.action_search)
        val searchView: SearchView = search.actionView as SearchView
        searchView.setQueryHint("Search");
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(p0: String?): Boolean {
                Toast.makeText(applicationContext, "Query text submit", Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextChange(p0: String?) = false

        })

        return super.onCreateOptionsMenu(menu)
    }

    fun setupViewPager() {

        searchPagerAdapter = SearchPageAdapter(supportFragmentManager)

        val listSearchFragment = ListSearchFragment()
        val mapFragment = MapSearchFragment()

        searchPagerAdapter.fragments = listOf(listSearchFragment, mapFragment)

        container.adapter = searchPagerAdapter

        tabs.setupWithViewPager(container)

        tabs.getTabAt(0)!!.setIcon(R.drawable.ic_view_list_black)
        tabs.getTabAt(1)!!.setIcon(R.drawable.ic_map_black)
    }
}
