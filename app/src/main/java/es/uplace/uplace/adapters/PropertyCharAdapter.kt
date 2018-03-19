package es.uplace.uplace.adapters

import kotlinx.android.synthetic.main.activity_main.*

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import es.uplace.uplace.R

class PropertyCharAdapter(private val context: Context) : RecyclerView.Adapter<PropertyCharAdapter.ViewHolder>() {

    internal inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var imgX: ImageView
        var txtX: TextView

        init {
            imgX = view.findViewById(R.id.imgX)
            txtX = view.findViewById(R.id.txtX)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.property_x, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtX.text = "Characteristic"
    }

    override fun getItemCount(): Int {
        return 5
    }
}
