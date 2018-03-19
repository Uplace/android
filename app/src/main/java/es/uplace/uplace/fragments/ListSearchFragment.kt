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

class ListSearchFragment : Fragment(), ListSearchAdapter.OnItemClickListener {

    internal var listSearchAdapter = ListSearchAdapter()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_list, container, false)

        val recyclerProperty = v.findViewById(R.id.recyclerProperty) as RecyclerView
        val layoutManager = LinearLayoutManager(context)
        recyclerProperty.layoutManager = layoutManager
        recyclerProperty.adapter = listSearchAdapter
        listSearchAdapter.setOnClickListener(this)

        return v
    }

    override fun itemClicked(view: View) {
        val intent = Intent(this.activity, PropertyActivity::class.java)
        startActivity(intent)
    }
}// Required empty public constructor
