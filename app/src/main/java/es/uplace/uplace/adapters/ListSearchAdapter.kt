package es.uplace.uplace.adapters

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import es.uplace.uplace.R
import es.uplace.uplace.domain.Property

class ListSearchAdapter(var properties: List<Property>?) : RecyclerView.Adapter<ListSearchAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val imgProperty = view.findViewById<ImageView>(R.id.imgProperty)
        private val imgFav = view.findViewById<ImageView>(R.id.imgFav)
        private val imgChar1 = view.findViewById<ImageView>(R.id.imgChar1)
        private val imgChar2 = view.findViewById<ImageView>(R.id.imgChar2)
        private val imgChar3 = view.findViewById<ImageView>(R.id.imgChar3)
        private val txtPropertyName = view.findViewById<TextView>(R.id.txtPropertyName)
        private val txtLocation = view.findViewById<TextView>(R.id.txtLocation)
        private val txtPrice = view.findViewById<TextView>(R.id.txtPrice)
        private val txtType = view.findViewById<TextView>(R.id.txtType)
        private val txtYearOfConst = view.findViewById<TextView>(R.id.txtYearOfConst)
        private val txtSurface = view.findViewById<TextView>(R.id.txtSurface)
        private val txtChar2 = view.findViewById<TextView>(R.id.txtChar2)
        private val txtChar3 = view.findViewById<TextView>(R.id.txtChar3)

        @SuppressLint("SetTextI18n")
        fun bind(property: Property) {
            txtPropertyName.text = property.title
            txtLocation.text = property.location.fullAddress
            txtType.text = property.propertyType
            txtYearOfConst.text = "${property.yearConstruction}"

            when (property.transaction) {
                "RENT" -> txtPrice.text = "${property.priceRent}€"
                "BUY" -> txtPrice.text = "${property.priceSell}€"
                "TRANSFER" -> txtPrice.text = "${property.priceTransfer}€"
                "RENT_BUY" -> txtPrice.text = "${property.priceSell}€ - ${property.priceRent}€"
                else -> txtPrice.text = "undefined"
            }

            txtSurface.text = "${property.surface}m2"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val cardProperty = LayoutInflater.from(parent?.context).inflate(R.layout.card_list_property, parent, false)
        return ViewHolder(cardProperty)
    }

    override fun getItemCount() = properties!!.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val property = properties!!.get(position)
        holder.bind(property)
    }


}
