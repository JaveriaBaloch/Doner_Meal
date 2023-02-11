package com.example.myapplication.ui.cart

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myapplication.R
import com.example.myapplication.ui.orders.CartItem
import com.example.myapplication.ui.orders.OrdersFragment

class CartAdapter(private val context: CartFragment, private val orderItem: ArrayList<CartItem>):
    RecyclerView.Adapter<CartAdapter.OrdersViewHolder>() {

    private val orders = mutableListOf<CartItem>()
    var list: ArrayList<CartItem> = orderItem

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cart, parent, false)

        return OrdersViewHolder(itemView)
    }

    override fun getItemCount() = list.size
    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        val item = list[position]
        holder.item_name1.text = list.get(position).itemName
        Log.d("OrdersViewModel", "cart e3: ${holder.item_name1.textSize}")

        holder.price1.text = "£" + list.get(position).itemPrice.toString()
        holder.quantity1.text = list.get(position).quantityItem.toString()

        Log.d("OrdersViewModel", "cart e34: ${ holder.price1.textSize}")

        //holder.quantity1.text = list.get(position).quantity.toString()
        Log.d("OrdersViewModel", "cart eew: ${ holder.quantity1.textSize}")
        Log.d("OrdersViewModel", "item: $item")
        Log.d("OrdersViewModel", "list size: ${list.size}")

        val image = list[position].itemImage
        holder.item_image1.load(image) {
            crossfade(true)
            placeholder(R.drawable.loading_background)

        }
    }
    fun setData(orders: List<CartItem>) {
        this.orders.clear()
        this.orders.addAll(orders)
        this.list = ArrayList(orders.filter { it.quantityItem > 0 })
        notifyDataSetChanged()
    }

    fun submitList(list: List<CartItem>) {
        orders.clear()
        orders.addAll(list)
        notifyDataSetChanged()
        /*list = cartNewItems.filter { it.quantityItem > 0 }.toList() as ArrayList<CartItem>
        notifyDataSetChanged()*/
    }



    class OrdersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var item_name1 = itemView.findViewById<TextView>(R.id.itemTitle1);
        var price1 = itemView.findViewById<TextView>(R.id.price1);

        var quantity1 = itemView.findViewById<TextView>(R.id.quantity1);

        var item_image1 = itemView.findViewById<ImageView>(R.id.item_image1)
    }

}