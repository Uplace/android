package es.uplace.uplace.fragments

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
import es.uplace.uplace.domain.Location
import es.uplace.uplace.domain.Property
import es.uplace.uplace.domain.enumeration.TransactionType
import es.uplace.uplace.retrofit.PropertyService
import es.uplace.uplace.retrofit.Service
import kotlinx.android.synthetic.main.fragment_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListSearchFragment : Fragment() {

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
                pgBar.visibility = ProgressBar.GONE
            }

            override fun onResponse(call: Call<List<Property>>?, response: Response<List<Property>>?) {
                if (response != null) {

                    val properties: List<Property> = response.body()!!.filter { property -> property.visible }

                    val adapter = ListSearchAdapter(properties)
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
}
