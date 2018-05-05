package es.uplace.uplace.adapters

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import es.uplace.uplace.R
import es.uplace.uplace.R.id.view
import es.uplace.uplace.domain.Photo
import es.uplace.uplace.domain.Property
import android.support.v4.content.ContextCompat.startActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import es.uplace.uplace.PropertyActivity


class ListSearchAdapter(var properties: List<Property>?) : RecyclerView.Adapter<ListSearchAdapter.ViewHolder>() {

    class ViewHolder(view: View, clickListener: (Property) -> Unit) : RecyclerView.ViewHolder(view) {

        private val imgProperty = view.findViewById<ImageView>(R.id.imgProperty)
        private val imgFav = view.findViewById<ImageView>(R.id.imgFav)
        private val txtTransaction = view.findViewById<TextView>(R.id.txtTransactionType)
        private val txtPropertyName = view.findViewById<TextView>(R.id.txtPropertyName)
        private val txtLocation = view.findViewById<TextView>(R.id.txtLocation)
        private val txtPrice = view.findViewById<TextView>(R.id.txtPrice)
        private val txtType = view.findViewById<TextView>(R.id.txtType)
        private val txtYearOfConst = view.findViewById<TextView>(R.id.txtYearOfConst)
        private val txtSurface = view.findViewById<TextView>(R.id.txtSurface)

        @SuppressLint("SetTextI18n")
        fun bind(property: Property) {
            txtPropertyName.text = property.title
            txtLocation.text = property.location.fullAddress
            txtType.text = property.propertyType
            txtYearOfConst.text = "${property.yearConstruction}"
            txtTransaction.text = property.transaction.toLowerCase().capitalize()

            when (property.transaction) {
                "RENT" -> txtPrice.text = "${property.priceRent}€"
                "BUY" -> txtPrice.text = "${property.priceSell}€"
                "TRANSFER" -> txtPrice.text = "${property.priceTransfer}€"
                "RENT_BUY" -> {
                    txtPrice.text = "${property.priceSell}€ - ${property.priceRent}€"
                    txtTransaction.text = "Buy or rent"
                }
                else -> txtPrice.text = "undefined"
            }

            txtSurface.text = "${property.surface}m2"

            if (property.photos.isNotEmpty())
                imgProperty.setImageBitmap(byteArrayToBitmap(property.photos[0]))

            itemView.setOnClickListener {

            }
        }

        private fun byteArrayToBitmap(photo: Photo): Bitmap {
            Log.d("ncs", photo.photo)
            val decodedString: ByteArray = Base64.decode(photo.photo, Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
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

    interface OnItemClickListener {
        fun itemClicked(view: View, property: Property)
    }

    private var listener: OnItemClickListener? = null

    fun setOnClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}
