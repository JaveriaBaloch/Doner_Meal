
package com.example.myapplication.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.myapplication.R
import com.example.myapplication.itemClass
import kotlinx.android.synthetic.main.fragment_home.*

import android.content.Context

import com.example.myapplication.ui.orders.OrdersViewModel

class FoodItemsAdapter(private val context: HomeFragment, private val itemClass: ArrayList<itemClass>):
    RecyclerView.Adapter<FoodItemsAdapter.FoodItemsViewHolder>() {

    var list: ArrayList<itemClass> = itemClass
    var cart: ArrayList<itemClass> = ArrayList()

    var ordersViewModel: OrdersViewModel = OrdersViewModel()
    private val sharedPref = context.activity?.getPreferences(Context.MODE_PRIVATE)




    var selectionTracker: Array<Long>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cards, parent, false)

        return FoodItemsViewHolder(itemView)
    }

    override fun getItemCount() = list.size
    override fun onBindViewHolder(holder: FoodItemsViewHolder, position: Int) {
        val item = list[position]
        holder.item_name.text = list.get(position).title
        holder.price.text = "£" + list.get(position).price.toString()
        val image = list[position].imageResource
        holder.item_image.load(image) {
            crossfade(true)
            placeholder(R.drawable.loading_background)

        }
        val itemQuantity = sharedPref?.getInt("${item.title}_quantity", 0) ?: 0
        holder.quantity.text = itemQuantity.toString()
        holder.munisBtn.setOnClickListener {
            if (itemQuantity > 0) {
                val count: Int = itemQuantity - 1
                holder.quantity.setText(count.toString())
                sharedPref?.edit()?.putInt("${item.title}_quantity", count)?.apply()
                if (count == 0) {
                    removeItemFromCart(list[position])
                }
                ordersViewModel.updateCart(cart)
            }
        }
        holder.plus.setOnClickListener {
            val count: Int = itemQuantity + 1
            holder.quantity.setText(count.toString())
            sharedPref?.edit()?.putInt("${item.title}_quantity", count)?.apply()
            if (count > 0) {
                addItemToCart(list[position])
            }
            ordersViewModel.updateCart(cart)
        }

    }
    private fun addItemToCart(item: itemClass) {
        if (!cart.contains(item)) {
            cart.add(item)
            ordersViewModel.updateCart(cart)
            notifyDataSetChanged()
        }
    }

    private fun removeItemFromCart(item: itemClass) {
        if (cart.contains(item)) {
            cart.remove(item)
            ordersViewModel.updateCart(cart)
            notifyDataSetChanged()
        }
    }




    class FoodItemsViewHolder(itemView: View) : ViewHolder(itemView) {
        var item_name = itemView.findViewById<TextView>(R.id.itemTitle);
        var price = itemView.findViewById<TextView>(R.id.price);
        var munisBtn = itemView.findViewById<Button>(R.id.minus);
        var quantity = itemView.findViewById<TextView>(R.id.quantity);
        var plus = itemView.findViewById<Button>(R.id.plus);
        var item_image = itemView.findViewById<ImageView>(R.id.item_image)



    }

}


