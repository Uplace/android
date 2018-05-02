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
        setContentView(R.layout.activity_property)

        val intent: Intent = getIntent()
        val name: String = intent.getStringExtra("name")
        val location: String = intent.getStringExtra("location")
        val price: String = intent.getStringExtra("price")
        val transaction: String = intent.getStringExtra("transaction")

        txtName.text = name
        txtLocation.text = location
        txtPrice.text = "$transaction: $price€"
        recyclerChars.layoutManager = GridLayoutManager(this, 2)
        recyclerExtras.layoutManager = GridLayoutManager(this, 2)
        recyclerChars.adapter = propertyCharAdapter
        recyclerExtras.adapter = propertyExtraAdapter
    }
}
