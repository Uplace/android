package es.uplace.uplace.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import es.uplace.uplace.R
import es.uplace.uplace.adapters.ListSearchAdapter
import es.uplace.uplace.domain.Location
import es.uplace.uplace.domain.Property
import es.uplace.uplace.domain.enumeration.TransactionType
import es.uplace.uplace.retrofit.PropertyService
import es.uplace.uplace.retrofit.Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListSearchFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_list, container, false)

//        val retrofit = Retrofit.Builder()
//                .baseUrl(Service.API_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//
//        val propertyService = retrofit.create(PropertyService::class.java)
//
//        val call = propertyService.findAllProperties()
//
//        val properties = call.execute().body()

        findAllProperties(v)

//        val adapter = ListSearchAdapter(properties)
//        adapter.properties = properties
//        val recyclerProperty = v.findViewById<RecyclerView>(R.id.recyclerProperty)
//        recyclerProperty.adapter = adapter
//        recyclerProperty.layoutManager = LinearLayoutManager(context)

        return v
    }

    fun findAllProperties(view: View) {
        val propertyService = PropertyService.create()
        val call = propertyService.findAllProperties()
        Log.d("ncs", "Call: " + call.toString())

        call.enqueue(object : Callback<List<Property>> {
            override fun onFailure(call: Call<List<Property>>?, t: Throwable?) {
                Toast.makeText(context, "Error en Callback", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<Property>>?, response: Response<List<Property>>?) {
                if (response != null) {

                    val properties: List<Property> = response.body()!!

                    val adapter = ListSearchAdapter(properties)
                    adapter.properties = properties
                    val recyclerProperty = view.findViewById<RecyclerView>(R.id.recyclerProperty)
                    recyclerProperty.adapter = adapter
                    recyclerProperty.layoutManager = LinearLayoutManager(context)
                } else {
                    Toast.makeText(context, "Response is null", Toast.LENGTH_LONG).show()
                }
            }

        })
    }

//    override fun itemClicked(view: View) {
//        val intent = Intent(this.activity, PropertyActivity::class.java)
//        startActivity(intent)
//    }
}
