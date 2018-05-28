package es.uplace.uplace.adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import com.squareup.picasso.Picasso
import es.uplace.uplace.PropertyActivity

import es.uplace.uplace.R
import es.uplace.uplace.adapters.viewholders.PropertyCardViewHolder
import es.uplace.uplace.domain.Property

class PropertyCardRecyclerViewAdapter internal constructor(
        private val propertyList: List<Property>) :
        RecyclerView.Adapter<PropertyCardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyCardViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.property_card, parent, false)
        return PropertyCardViewHolder(layoutView)
    }

    override fun getItemCount(): Int = propertyList.size

    override fun onBindViewHolder(holder: PropertyCardViewHolder, position: Int) {
        val property = propertyList[position]
        Log.d("ncs", property.toString())
        holder.propertyLocation.text = property.location.fullAddress
        holder.propertyCategoryTransaction.text =
                "${property.propertyType} for ${if (property.transaction == "RENT_BUY") "Buy or Rent" else property.transaction.toLowerCase().capitalize()}"
        holder.propertyPrice.text = when (property.transaction) {
            "RENT" -> "${property.priceRent.toInt()}€"
            "BUY" -> "${property.priceSell.toInt()}€"
            "TRANSFER" -> "${property.priceTransfer.toInt()}€"
            "RENT_BUY" -> "${property.priceSell.toInt()}€ - ${property.priceRent.toInt()}€"
            else -> "undefined"
        }
        if (property.photos.isNotEmpty()) {
            Picasso.with(holder.itemView.context)
                    .load(property.photos[0].photoUrl)
                    .fit()
                    .centerCrop()
                    .into(holder.propertyImage)
        } else {
            holder.propertyImage.setImageBitmap(null)
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, PropertyActivity::class.java)
            intent.putExtra("reference", property.reference)
            holder.itemView.context.startActivity(intent)
        }

    }
}
