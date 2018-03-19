package es.uplace.uplace

import kotlinx.android.synthetic.main.activity_search.*

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity

import es.uplace.uplace.fragments.ListSearchFragment
import es.uplace.uplace.fragments.MapSearchFragment

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val fragmentManager = supportFragmentManager

        // define your fragments here
        val listFragment = ListSearchFragment()
        val mapFragment = MapSearchFragment()

        var fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.searchFrame, listFragment).commit()

        // handle navigation selection
        navigation.setOnNavigationItemSelectedListener(
                BottomNavigationView.OnNavigationItemSelectedListener { item ->
                    fragmentTransaction = fragmentManager.beginTransaction()
                    when (item.itemId) {
                        R.id.nav_list -> {
                            fragmentTransaction.replace(R.id.searchFrame, listFragment).commit()
                            return@OnNavigationItemSelectedListener true
                        }
                        R.id.nav_map -> {
                            fragmentTransaction.replace(R.id.searchFrame, mapFragment).commit()
                            return@OnNavigationItemSelectedListener true
                        }
                    }
                    true
                }
        )
    }
}
