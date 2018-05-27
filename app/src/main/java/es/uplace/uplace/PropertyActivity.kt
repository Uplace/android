package es.uplace.uplace

import android.annotation.SuppressLint
import android.content.Intent
import kotlinx.android.synthetic.main.activity_property.*

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager

import es.uplace.uplace.adapters.PropertyCharAdapter
import es.uplace.uplace.adapters.PropertyExtraAdapter

class PropertyActivity : AppCompatActivity() {

    var propertyCharAdapter = PropertyCharAdapter(this)
    var propertyExtraAdapter = PropertyExtraAdapter(this)

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_v2)
    }
}
