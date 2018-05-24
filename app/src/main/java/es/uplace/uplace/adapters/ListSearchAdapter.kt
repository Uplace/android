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
import com.squareup.picasso.Picasso
import es.uplace.uplace.R
import es.uplace.uplace.domain.Photo
import es.uplace.uplace.domain.Property
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.card_list_property.*

class ListSearchAdapter(private val properties: List<Property>/*,
                        private val itemClick: (Property) -> Unit*/) :
        RecyclerView.Adapter<ListSearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cardProperty = LayoutInflater.from(parent.context).inflate(R.layout.card_list_property, parent, false)
        return ViewHolder(cardProperty/*, itemClick*/)
    }

    override fun getItemCount() = properties.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(properties[position])
    }

    interface OnItemClickListener {
        fun itemClicked(view: View, property: Property)
    }

    class ViewHolder(override val containerView: View/*, private val itemClick: (Property) -> Unit*/)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {

        @SuppressLint("SetTextI18n")
        fun bind(property: Property) {
            with(property) {
                txtPropertyName.text = property.title
                txtLocation.text = property.location.fullAddress
                txtType.text = property.propertyType
                txtYearOfConst.text = "${property.yearConstruction}"
                txtTransactionType.text = property.transaction.toLowerCase().capitalize()

                when (property.transaction) {
                    "RENT" -> txtPrice.text = "${property.priceRent}€"
                    "BUY" -> txtPrice.text = "${property.priceSell}€"
                    "TRANSFER" -> txtPrice.text = "${property.priceTransfer}€"
                    "RENT_BUY" -> {
                        txtPrice.text = "${property.priceSell}€ - ${property.priceRent}€"
                        txtTransactionType.text = "Buy or rent"
                    }
                    else -> txtPrice.text = "undefined"
                }

                txtSurface.text = "${property.surface}m2"

                if (property.photos.isNotEmpty())
//                    imgProperty.setImageBitmap(byteArrayToBitmap(property.photos[0]))
                    Picasso.with(containerView.context).load(property.photos[0].photoUrl).into(imgProperty)

//                itemView.setOnClickListener {
//                    itemClick(this)
//                }
            }
        }
    }
}
