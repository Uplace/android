package es.uplace.uplace.fragments

import kotlinx.android.synthetic.main.fragment_list.view.*

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import es.uplace.uplace.R
import es.uplace.uplace.adapters.PropertyCardRecyclerViewAdapter
import es.uplace.uplace.domain.Property

class ListSearchFragment : Fragment() {

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

        val adapter = PropertyCardRecyclerViewAdapter(properties)
        val recyclerProperty = v.recycler_property
        recyclerProperty.adapter = adapter
        recyclerProperty.layoutManager = LinearLayoutManager(context)

        return v
    }
}