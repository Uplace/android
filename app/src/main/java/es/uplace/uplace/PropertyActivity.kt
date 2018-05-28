package es.uplace.uplace

import android.annotation.SuppressLint

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.squareup.picasso.Picasso

import es.uplace.uplace.adapters.PropertyCharAdapter
import es.uplace.uplace.adapters.PropertyExtraAdapter
import es.uplace.uplace.domain.Property
import es.uplace.uplace.retrofit.PropertyService
import kotlinx.android.synthetic.main.activity_property_v2.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PropertyActivity : AppCompatActivity() {

    var propertyCharAdapter = PropertyCharAdapter(this)
    var propertyExtraAdapter = PropertyExtraAdapter(this)

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_v2)

        setSupportActionBar(property_activity_toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        findProperty()
    }

    private fun findProperty() {

        val reference = intent.getStringExtra("reference")

        val propertyService = PropertyService.create()
        val call = propertyService.findPropertyByReference(reference)

        call.enqueue(object : Callback<Property> {
            override fun onFailure(call: Call<Property>?, t: Throwable?) {
                Toast.makeText(this@PropertyActivity, "Error en la petición", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Property>?, response: Response<Property>?) {
                if (response != null) {
                    val property: Property? = response.body()
                    setViews(property)
                } else {
                    Toast.makeText(this@PropertyActivity, "No result", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    private fun setViews(property: Property?) {
        if (property != null) {
            supportActionBar?.title = property.title
            Picasso.with(this).load(property.photos[0].photoUrl).fit().centerCrop().into(property_activity_image)
            property_activity_location.text = property.location.fullAddress
            when (property.transaction) {
                "RENT" -> {
                    property_activity_txt_category_transaction.text = "${property.propertyType.toLowerCase().capitalize()} for Rent"
                    property_activity_price.text = "${property.priceRent}€"
                }
                "BUY" -> {
                    property_activity_txt_category_transaction.text = "${property.propertyType.toLowerCase().capitalize()} for Buy"
                    property_activity_price.text = "${property.priceSell}€"
                }
                "TRANSFER" -> {
                    property_activity_txt_category_transaction.text = "${property.propertyType.toLowerCase().capitalize()} for Transfer"
                    property_activity_price.text = "${property.priceTransfer}€"
                }
                "BUY_RENT" -> {
                    property_activity_txt_category_transaction.text = "${property.propertyType.toLowerCase().capitalize()} for Buy or Rent"
                    property_activity_price.text = "${property.priceSell}€ - ${property.priceRent}€"
                }
            }
            property_activity_description.text = property.description
        }

    }
}
