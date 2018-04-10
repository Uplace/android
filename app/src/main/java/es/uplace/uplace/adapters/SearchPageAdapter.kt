package es.uplace.uplace.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class SearchPageAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    lateinit var fragments: List<Fragment>

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size
}