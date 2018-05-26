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
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.card_list_property, parent, false)
        return PropertyCardViewHolder(layoutView)
    }

    override fun getItemCount(): Int = propertyList.size

    override fun onBindViewHolder(holder: PropertyCardViewHolder, position: Int) {
        if (position < propertyList.size) {
            val property = propertyList[position]
            holder.propertyName.text = property.title
            holder.propertyLocation.text = property.location.fullAddress
            holder.propertyType.text = property.propertyType
            holder.propertyYearOfConst.text = "${property.yearConstruction}"
            holder.propertyTransaction.text = if (property.transaction == "RENT_BUY") "Buy - Rent" else property.transaction.capitalize()
            holder.propertyPrice.text = when (property.transaction) {
                "RENT" -> "${property.priceRent}€"
                "BUY" -> "${property.priceSell}€"
                "TRANSFER" -> "${property.priceTransfer}€"
                "RENT_BUY" -> "${property.priceSell}€ - ${property.priceRent}€"
                else -> "undefined"
            }
            holder.propertySurface.text = "${property.surface}m\u00B2"
            if (property.photos.isNotEmpty())
                Picasso.with(holder.itemView.context)
                        .load(property.photos[0].photoUrl)
                        .into(holder.propertyImage)

            holder.itemView.setOnClickListener {
                val intent = Intent(holder.itemView.context, PropertyActivity::class.java)
                intent.putExtra("reference", property.reference)
                holder.itemView.context.startActivity(intent)
            }
        }
    }
}
