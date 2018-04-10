package es.uplace.uplace

import kotlinx.android.synthetic.main.activity_search.*

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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

        tabs.setupWithViewPager(container)

        tabs.getTabAt(0)!!.setIcon(R.drawable.ic_view_list_black)
        tabs.getTabAt(1)!!.setIcon(R.drawable.ic_map_black)
    }

    fun setupViewPager() {

        searchPagerAdapter = SearchPageAdapter(supportFragmentManager)

        val listSearchFragment = ListSearchFragment()
        val mapFragment = MapSearchFragment()

        searchPagerAdapter.fragments = listOf(listSearchFragment, mapFragment)

        container.adapter = searchPagerAdapter
    }
}
