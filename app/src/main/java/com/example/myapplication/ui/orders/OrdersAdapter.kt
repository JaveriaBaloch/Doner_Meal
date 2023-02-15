package com.example.myapplication.ui.orders

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myapplication.R
import com.example.myapplication.ui.checkout.UserCheckout
import com.example.myapplication.ui.home.itemClass
import com.google.firebase.firestore.auth.User


class OrdersAdapter(orderItem: ArrayList<itemClass>):
    RecyclerView.Adapter<OrdersAdapter.OrdersItemViewHolder>() {
    var onCartItemRemovedListener: OnCartItemRemovedListener? = null

    interface OnCartItemRemovedListener{
        fun onRemoved()
    }
    private var list: ArrayList<itemClass> = orderItem
    var selectionTracker: Array<Long>? = null
    var sharedPreference: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var set: String? = null

    var sets: MutableSet<String>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.order_item, parent, false)
        sharedPreference = parent.getContext().getSharedPreferences("cart", Context.MODE_PRIVATE)
        editor = sharedPreference?.edit()

        return OrdersItemViewHolder(itemView)
    }

    override fun getItemCount() = list.size
    override fun onBindViewHolder(holder: OrdersItemViewHolder, position: Int) {
        val item =list.get(position)
        holder.itemname.text = list[position].title
        holder.price.text = "£  " + list.get(position).price.toString()
        val image = list[position].imageResource
        val  count=sharedPreference?.getInt(item.title,0)
        holder.quantity.text = count.toString()
        holder.item_image.load(image) {
            crossfade(true)
            placeholder(R.drawable.loading_background)

        }

    }




    class OrdersItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemname: TextView = itemView.findViewById(R.id.title2)
        var price: TextView = itemView.findViewById(R.id.price2)
        //var munisBtn: Button = itemView.findViewById(R.id.minus1)
        var quantity: TextView = itemView.findViewById(R.id.quantity2)
       // var plus: Button = itemView.findViewById(R.id.plus1)
        var item_image: ImageView = itemView.findViewById(R.id.item_image2)



    }

}



