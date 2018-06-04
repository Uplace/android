package es.uplace.uplace

import android.annotation.SuppressLint

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.text.Editable
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.squareup.picasso.Picasso

import es.uplace.uplace.adapters.PropertyCharAdapter
import es.uplace.uplace.adapters.PropertyExtraAdapter
import es.uplace.uplace.domain.Property
import es.uplace.uplace.domain.Request
import es.uplace.uplace.retrofit.PropertyService
import kotlinx.android.synthetic.main.activity_property_v2.*
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PropertyActivity : AppCompatActivity() {

    lateinit var property: Property
    lateinit var propertyJson: String

    private val propertyService = PropertyService.create()

    var propertyCharAdapter = PropertyCharAdapter(this)
    var propertyExtraAdapter = PropertyExtraAdapter(this)

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_v2)

        setSupportActionBar(property_activity_toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        findProperty()

        property_activity_send_button.setOnClickListener {

            if (!isNotEmpty(property_activity_firstname_edit_text.text)) {
                property_activity_firstname_text_input.error = getString(R.string.error_empty)
            } else if (!isNotEmpty(property_activity_lastname_edit_text.text)) {
                property_activity_lastname_text_input.error = getString(R.string.error_empty)
            } else if (!isValidEmail(property_activity_email_edit_text.text)) {
                property_activity_email_text_input.error = getString(R.string.error_email)
            } else if (!isValidPhone(property_activity_phone_edit_text.text)) {
                property_activity_phone_text_input.error = getString(R.string.error_phone)
            } else {
                property_activity_firstname_text_input.error = null
                property_activity_lastname_text_input.error = null
                property_activity_email_text_input.error = null
                property_activity_phone_text_input.error = null

                sendRequest()
            }
        }
    }

    private fun isNotEmpty(text: Editable?): Boolean = text != null && text.isNotEmpty()

    private fun isValidEmail(text: Editable?): Boolean =
            text != null && Patterns.EMAIL_ADDRESS.matcher(text.toString()).matches()

    private fun isValidPhone(text: Editable?): Boolean = text != null && text.length == 9

    private fun findProperty() {

        val reference = intent.getStringExtra("reference")
        val call = propertyService.findPropertyByReference(reference)

        call.enqueue(object : Callback<Property> {
            override fun onFailure(call: Call<Property>?, t: Throwable?) {
                Toast.makeText(this@PropertyActivity, "Error en la petición", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Property>?, response: Response<Property>?) {
                if (response != null) {
                    property = response.body()!!
                    setViews(property)
                } else {
                    Toast.makeText(this@PropertyActivity, "No result", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    private fun setViews(property: Property) {
        supportActionBar?.title = property.title
        Picasso.with(this).load(property.photos[0].photoUrl).fit().centerCrop().into(property_activity_image)
        property_activity_location.text = property.location.fullAddress
        Log.d("ncs", property.transaction)
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
            "RENT_BUY" -> {
                property_activity_txt_category_transaction.text = "${property.propertyType.toLowerCase().capitalize()} for Buy or Rent"
                property_activity_price.text = "${property.priceSell}€ - ${property.priceRent}€"
            }
        }
        property_activity_description.text = property.description
    }

    private fun sendRequest() {
        val firstname: String = property_activity_firstname_edit_text.text.toString()
        val lastname: String = property_activity_lastname_edit_text.text.toString()
        val email: String = property_activity_email_edit_text.text.toString()
        val phone: String = property_activity_phone_edit_text.text.toString()
        val message: String = property_activity_comment_edit_text.text.toString()

        val request = Request(
                firstName = firstname,
                lastName = lastname,
                email = email,
                message = message,
                phone = phone
        )

        val requestBody: RequestBody = RequestBody.create(MediaType.parse("application/json"), request.toString())

        val call = propertyService.sendRequestInformation(property.reference, requestBody)

        call.enqueue(object : Callback<Void> {
            override fun onFailure(call: Call<Void>?, t: Throwable?) {
                Toast.makeText(this@PropertyActivity, "Error en callback", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                val responseCode = response?.code()

                val builder = AlertDialog.Builder(this@PropertyActivity)

                if (responseCode == 200) {
                    builder.setTitle("Message sent")
                    builder.setMessage("Wait until the agent sends you an email.")
                    builder.setPositiveButton("OK"){ _, _ ->
                        property_activity_firstname_edit_text.text = null
                        property_activity_lastname_edit_text.text = null
                        property_activity_email_edit_text.text = null
                        property_activity_phone_edit_text.text = null
                        property_activity_comment_edit_text.text = null

                        property_activity_firstname_edit_text.clearFocus()
                        property_activity_lastname_edit_text.clearFocus()
                        property_activity_email_edit_text.clearFocus()
                        property_activity_phone_edit_text.clearFocus()
                        property_activity_comment_edit_text.clearFocus()
                    }
                } else {
                    builder.setTitle("It looks like an error occurred")
                    builder.setMessage("Try again")
                    builder.setPositiveButton("OK"){ _, _ ->}
                }

                val dialog: AlertDialog = builder.create()
                dialog.show()
            }

        })
    }
}
