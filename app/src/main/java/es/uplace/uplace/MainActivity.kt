package es.uplace.uplace

import kotlinx.android.synthetic.main.activity_main.*

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ArrayAdapter

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setClickListeners()

        val adapter = ArrayAdapter.createFromResource(
                this, R.array.dwelling_types, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinDwelling.adapter = adapter
    }

    override fun onClick(v: View) {
        when (v.id) {
//            R.id.btnFilters -> drawer_layout.openDrawer(Gravity.START)
//            R.id.btnSearch -> startActivity(Intent(this, SearchActivity::class.java))
        }
    }

    private fun setClickListeners() {
//        btnFilters.setOnClickListener(this)
//        btnSearch.setOnClickListener(this)
    }
}
