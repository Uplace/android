package es.uplace.uplace.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast

import es.uplace.uplace.R
import es.uplace.uplace.adapters.ListSearchAdapter
import es.uplace.uplace.domain.Property
import es.uplace.uplace.retrofit.PropertyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import es.uplace.uplace.PropertyActivity
import es.uplace.uplace.domain.enumeration.TransactionType


class ListSearchFragment : Fragment(), ListSearchAdapter.OnItemClickListener {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_list, container, false)

        findAllProperties(v)

        return v
    }

    fun findAllProperties(view: View) {
        val pgBar = view.findViewById<ProgressBar>(R.id.pgBar)
        val propertyService = PropertyService.create()
        val call = propertyService.findAllProperties()
        Log.d("ncs", "Call: " + call.toString())

        call.enqueue(object : Callback<List<Property>> {
            override fun onFailure(call: Call<List<Property>>?, t: Throwable?) {
                Toast.makeText(context, "Error en Callback", Toast.LENGTH_LONG).show()
                Log.d("ncs", "Error en Callback: " + t.toString())
                pgBar.visibility = ProgressBar.GONE
            }

            override fun onResponse(call: Call<List<Property>>?, response: Response<List<Property>>?) {
                if (response != null) {

                    val properties: List<Property> = response.body()!!.filter { property -> property.visible }

                    val adapter = ListSearchAdapter(properties)
                    adapter.setOnClickListener(this@ListSearchFragment)
                    val recyclerProperty = view.findViewById<RecyclerView>(R.id.recyclerProperty)
                    recyclerProperty.adapter = adapter
                    recyclerProperty.layoutManager = LinearLayoutManager(context)
                } else {
                    Toast.makeText(context, "Response is null", Toast.LENGTH_LONG).show()
                }
                pgBar.visibility = ProgressBar.GONE
            }

        })
    }

    override fun itemClicked(view: View, property: Property) {
        val intent = Intent(this.activity, PropertyActivity::class.java)
        val name: String = property.title
        val location: String = property.location.fullAddress
        val price: Double
        val transaction: String
        when (property.transaction) {
            "RENT" -> {
                price = property.priceRent
                transaction = "Rent"
            }
            "BUY" -> {
                price = property.priceSell
                transaction = "Buy"
            }
            "TRANSFER" -> {
                price = property.priceTransfer
                transaction = "Transfer"
            }
            "RENT_BUY" -> {
                price = property.priceSell
                transaction = "Buy"
            }
            else -> {
                price = 0.0
                transaction = "undefined"
            }
        }

        intent.putExtra("name", name)
        intent.putExtra("location", location)
        intent.putExtra("price", price)
        intent.putExtra("transaction", transaction)
        startActivity(intent)
    }
}
