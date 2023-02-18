package com.example.myapplication.ui.orders

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myapplication.R
import com.example.myapplication.ui.checkout.UserCheckout
import com.example.myapplication.ui.home.itemClass

class OrdersAdapter(orderItem: ArrayList<itemClass>, orderHeading: ArrayList<UserCheckout>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val ITEM_TYPE_HEADING = 0
    private val ITEM_TYPE_ITEM = 1


    private var list: ArrayList<itemClass> = orderItem
    private var list_heading: ArrayList<UserCheckout> = orderHeading
    var selectionTracker: Array<Long>? = null
    var sharedPreference: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var set: String? = null

    var sets: MutableSet<String>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //val itemView = LayoutInflater.from(parent.context).inflate(R.layout.order_item, parent, false)
        sharedPreference = parent.getContext().getSharedPreferences("cart", Context.MODE_PRIVATE)
        editor = sharedPreference?.edit()

        return when (viewType) {
            ITEM_TYPE_HEADING -> {
                val itemView =
                    LayoutInflater.from(parent.context).inflate(R.layout.order_heading, parent, false)
                OrdersHeadingViewHolder(itemView)
            }
            ITEM_TYPE_ITEM -> {
                val itemView =
                    LayoutInflater.from(parent.context).inflate(R.layout.order_item, parent, false)
                OrdersItemViewHolder(itemView)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount() = list.size
    //override fun getItemHeadingCount() = list_heading.size


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //val item =list.get(position)
        //val item_heading = list_heading.get(position)
        when (holder) {
            is OrdersHeadingViewHolder -> {
                // Cast the holder to the appropriate view holder class
                // and set the heading text
                val orderHeading = list_heading[position]
                holder.order_date.text = orderHeading.currentDate
                holder.tota_value.text = orderHeading.totalValue.toString()
            }
            is OrdersItemViewHolder -> {
                // Cast the holder to the appropriate view holder class
                // and set the item name, price, and image
                val item = list[position]
                holder.itemname.text = item.title
                holder.price.text = "£  " + item.price.toString()
                val image = item.imageResource
                val  count=sharedPreference?.getInt(item.title,0)
                holder.quantity.text = count.toString()
                holder.item_image.load(image) {
                    crossfade(true)
                    placeholder(R.drawable.loading_background)
                }
            }
        }
    }
    override fun getItemViewType(position: Int): Int {
        return if (position < list_heading.size) ITEM_TYPE_HEADING else ITEM_TYPE_ITEM
    }


    }

    /*override fun getItemViewType(position: Int): Int {
        val item = list[position]
        return if (item is UserCheckout) ITEM_TYPE_HEADING else ITEM_TYPE_ITEM
    }*/


    class OrdersHeadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var order_date: TextView = itemView.findViewById(R.id.order_date)
            var tota_value: TextView = itemView.findViewById(R.id.total_value)
        }






     class OrdersItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemname: TextView = itemView.findViewById(R.id.title2)
        var price: TextView = itemView.findViewById(R.id.price2)
        //var munisBtn: Button = itemView.findViewById(R.id.minus1)
        var quantity: TextView = itemView.findViewById(R.id.quantity2)
        // var plus: Button = itemView.findViewById(R.id.plus1)
        var item_image: ImageView = itemView.findViewById(R.id.item_image2)




    }



