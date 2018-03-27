package es.uplace.uplace.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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
//        val response = call.execute()
//
//        if (response.isSuccessful) {
//            val properties = response.body()

        var properties: List<Property>

        properties = mutableListOf(
                Property("House1", "987654", "Apartment", TransactionType.BUY, 100, Location(1, 1.0, 1.0, "08001", "Barcelona", "Calle House1")),
                Property("House2", "987654", "Apartment", TransactionType.BUY, 100, Location(1, 1.0, 1.0, "08001", "Barcelona", "Calle House1")),
                Property("House3", "987654", "Apartment", TransactionType.BUY, 100, Location(1, 1.0, 1.0, "08001", "Barcelona", "Calle House1")),
                Property("House4", "987654", "Apartment", TransactionType.BUY, 100, Location(1, 1.0, 1.0, "08001", "Barcelona", "Calle House1")))
                .toList()

//
            val adapter = ListSearchAdapter(properties)
            adapter.properties = properties
            val recyclerProperty = v.findViewById<RecyclerView>(R.id.recyclerProperty)
            recyclerProperty.adapter = adapter
            recyclerProperty.layoutManager = LinearLayoutManager(context)
//        } else {
//            Toast.makeText(context, "Error al recuperar propiedades", Toast.LENGTH_SHORT).show()
//        }

//        val recyclerProperty = v.findViewById(R.id.recyclerProperty) as RecyclerView
//        val layoutManager = LinearLayoutManager(context)
//        recyclerProperty.layoutManager = layoutManager
//        recyclerProperty.adapter = listSearchAdapter
//        listSearchAdapter.setOnClickListener(this)

        return v
    }

//    override fun itemClicked(view: View) {
//        val intent = Intent(this.activity, PropertyActivity::class.java)
//        startActivity(intent)
//    }
}
