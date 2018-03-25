package es.uplace.uplace.fragments

import kotlinx.android.synthetic.main.fragment_list.*

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import es.uplace.uplace.PropertyActivity
import es.uplace.uplace.R
import es.uplace.uplace.adapters.ListSearchAdapter
import es.uplace.uplace.domain.Property

class ListSearchFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_list, container, false)

        var properties = ArrayList<Property>()
        properties.add(Property(title = "Property 1"))

        val adapter = ListSearchAdapter()
        adapter.properties = properties
        val recyclerProperty = v.findViewById<RecyclerView>(R.id.recyclerProperty)
        recyclerProperty.adapter = adapter
        recyclerProperty.layoutManager = LinearLayoutManager(context)

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
