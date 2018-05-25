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
import es.uplace.uplace.domain.Content
import kotlinx.android.synthetic.main.activity_search.view.*
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListSearchFragment : Fragment(), ListSearchAdapter.OnItemClickListener {

    companion object {
        fun instance(properties: ArrayList<Property>): ListSearchFragment {
            val fragment = ListSearchFragment()
            val args = Bundle()
            args.putParcelableArrayList("properties", properties)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_list, container, false)

        val properties: List<Property> = arguments!!.getParcelableArrayList("properties")

        val adapter = ListSearchAdapter(properties)
        val recyclerProperty = v.recycler_property
        recyclerProperty.adapter = adapter
        recyclerProperty.layoutManager = LinearLayoutManager(context)

        return v
    }

    private fun findAllProperties(view: View) {
        val pgBar = view.pg_bar
        val propertyService = PropertyService.create()
        val params: MutableMap<String, String>? = HashMap<String, String>()
        params?.put("city.equals", "Barcelona")

        val paramMap: Map<String, String> = HashMap(params)
        paramMap.forEach { p -> Log.d("ncs", "$p")}

        val call = propertyService.findAllProperties(paramMap)
        Log.d("ncs", "Call: " + call.toString())

        call.enqueue(object : Callback<Content> {
            override fun onFailure(call: Call<Content>?, t: Throwable?) {
                Toast.makeText(context, "Error en Callback", Toast.LENGTH_SHORT).show()
                Log.d("ncs", "Error en Callback: ${t.toString()}")
                pgBar.visibility = ProgressBar.GONE
            }

            override fun onResponse(call: Call<Content>?, response: Response<Content>?) {
                if (response != null) {
                    val content: Content? = response.body()
                    val properties: List<Property> = content?.content!!

                    val adapter = ListSearchAdapter(properties)
                    val recyclerProperty = view.recycler_property
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