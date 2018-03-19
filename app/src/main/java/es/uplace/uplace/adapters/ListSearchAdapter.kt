package es.uplace.uplace.adapters

import kotlinx.android.synthetic.main.card_list_property.*
import kotlinx.android.synthetic.main.card_list_property.view.*

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import es.uplace.uplace.R

class ListSearchAdapter : RecyclerView.Adapter<ListSearchAdapter.ViewHolder>() {

//    class ListSearchAdapter(val items: List<Item>, val listener: (Item) -> Unit)

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Item, listener: (Item) -> Unit) = with(itemView) {
//            txtPropertyName.text = item.name
//                    ...
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =
//            ViewHolder(parent.inflate(R.layout.card_list_property))
            ViewHolder(parent.inflate)
//    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): {
//        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.card_list_property, parent, false)
//        return ViewHolder(view)
//    }

    override fun getItemCount() = item.size
//    override fun getItemCount(): Int {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }


}
