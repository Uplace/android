package es.uplace.uplace.adapters.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import es.uplace.uplace.R

class PropertyCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var propertyImage: ImageView = itemView.findViewById(R.id.property_card_image)
    var propertyPrice: TextView = itemView.findViewById(R.id.property_card_price)
    var propertyLocation: TextView = itemView.findViewById(R.id.property_card_location)
    var propertyCategoryTransaction: TextView = itemView.findViewById(R.id.property_card_category_transaction)

//    var propertyName: TextView = itemView.findViewById(R.id.txtPropertyName)
//    var propertyLocation: TextView = itemView.findViewById(R.id.txtLocation)
//    var propertyType: TextView = itemView.findViewById(R.id.txtType)
//    var propertyYearOfConst: TextView = itemView.findViewById(R.id.txtYearOfConst)
//    var propertyTransaction: TextView = itemView.findViewById(R.id.txtTransactionType)
//    var propertyPrice: TextView = itemView.findViewById(R.id.txtPrice)
//    var propertySurface: TextView = itemView.findViewById(R.id.txtSurface)
//    var propertyImage: ImageView = itemView.findViewById(R.id.imgProperty)
}