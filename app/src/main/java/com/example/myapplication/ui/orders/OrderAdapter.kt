package com.example.myapplication.ui.orders

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myapplication.R
import com.example.myapplication.itemClass
import kotlinx.android.synthetic.main.order_cards.view.*
class OrdersAdapter(private val context: OrdersFragment, private val orderItem: ArrayList<itemClass>):
    RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder>() {

    private val orders = mutableListOf<itemClass>()
    var list: ArrayList<itemClass> = orderItem

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.order_cards, parent, false)

        return OrdersViewHolder(itemView)
    }

    override fun getItemCount() = list.size
    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        val item = list[position]
        holder.item_name1.text = list.get(position).title
        Log.d("OrdersViewModel", "cart size: ${holder.item_name1.textSize}")

        holder.price1.text = "£" + list.get(position).price.toString()
        Log.d("OrdersViewModel", "cart size: ${ holder.price1.textSize}")

        //holder.quantity1.text = list.get(position).quantity.toString()
        Log.d("OrdersViewModel", "cart size: ${ holder.quantity1.textSize}")
        Log.d("OrdersViewModel", "item: $item")
        Log.d("OrdersViewModel", "list size: ${list.size}")

        val image = list[position].imageResource
        holder.item_image1.load(image) {
            crossfade(true)
            placeholder(R.drawable.loading_background)

        }
    }
    fun setData(orders: List<itemClass>) {
        this.orders.clear()
        this.orders.addAll(orders)
        this.list = ArrayList(orders)
        notifyDataSetChanged()
    }


    class OrdersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var item_name1 = itemView.findViewById<TextView>(R.id.itemTitle1);
        var price1 = itemView.findViewById<TextView>(R.id.price1);

        var quantity1 = itemView.findViewById<TextView>(R.id.quantity1);

        var item_image1 = itemView.findViewById<ImageView>(R.id.item_image1)
    }

}