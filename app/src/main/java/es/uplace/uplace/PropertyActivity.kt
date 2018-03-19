package es.uplace.uplace

import kotlinx.android.synthetic.main.activity_property.*

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager

import es.uplace.uplace.adapters.PropertyCharAdapter
import es.uplace.uplace.adapters.PropertyExtraAdapter

class PropertyActivity : AppCompatActivity() {

    internal var propertyCharAdapter = PropertyCharAdapter(this)
    internal var propertyExtraAdapter = PropertyExtraAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property)

        txtName.text = "Test Nombre Largo, 1, 2, 3"
        txtLocation.text = "Test Location: c/Lorem ipsum 123, Prpl. 4 08001, Barcelona, Spain"
        txtPrice.text = "Venta: 999999.99€"
        recyclerChars.layoutManager = GridLayoutManager(this, 2)
        recyclerExtras.layoutManager = GridLayoutManager(this, 2)
        recyclerChars.adapter = propertyCharAdapter
        recyclerExtras.adapter = propertyExtraAdapter
    }
}
