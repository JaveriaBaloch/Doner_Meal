
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

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.myapplication.ui.orders.CartItem

import com.example.myapplication.ui.orders.OrdersViewModel


interface ItemQuantityChangeListener {
    fun onQuantityChanged(position: Int, quantity: Int)
}

class FoodItemsAdapter(private val context: HomeFragment, private val itemClass: ArrayList<itemClass>):
    RecyclerView.Adapter<FoodItemsAdapter.FoodItemsViewHolder>() {


    var list: ArrayList<itemClass> = itemClass
    var cart: ArrayList<CartItem> = ArrayList()
    lateinit var cart_Button: Button


    var ordersViewModel: OrdersViewModel = OrdersViewModel()
    /*public val sharedPref = context.activity?.getPreferences(Context.MODE_PRIVATE)
    if (sharedPref != null) {
        sharedPref.edit().clear().commit()
    }*/
    var listener: ItemQuantityChangeListener? = null
    public val sharedPref = context.activity?.getPreferences(Context.MODE_PRIVATE)




    var selectionTracker: Array<Long>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cards, parent, false)

        return FoodItemsViewHolder(itemView)
    }

    override fun getItemCount() = list.size
    override fun onBindViewHolder(holder: FoodItemsViewHolder, position: Int) {
        //holder.quantity.text = itemQuantity.toString()
        val item = list[position]
        val itemQuantity = sharedPref?.getInt("${item.title}_quantity", 0) ?: 0
        holder.quantity.text = itemQuantity.toString()


        holder.item_name.text = list.get(position).title
        holder.price.text = "£" + list.get(position).price.toString()

        val image = list[position].imageResource

        holder.item_image.load(image) {
            crossfade(true)
            placeholder(R.drawable.loading_background)

        }
       // val itemQuantity = sharedPref?.getInt("${item.title}_quantity", 0) ?: 0
       // holder.quantity.text = itemQuantity.toString()
        holder.munisBtn.setOnClickListener {
            if(Integer.parseInt(holder.quantity.text.toString())>0) {
                val count: Int = Integer.parseInt(holder.quantity.text.toString()) - 1;
                holder.quantity.setText(count.toString())
                listener?.onQuantityChanged(position, count)
                sharedPref?.edit()?.putInt("${item.title}_quantity", count)?.apply()

            }
        }
        holder.plus.setOnClickListener {
            val count: Int = Integer.parseInt(holder.quantity.text.toString()) + 1;
            holder.quantity.setText(count.toString())
            listener?.onQuantityChanged(position, count)
            sharedPref?.edit()?.putInt("${item.title}_quantity", count)?.apply()

            //ordersViewModel.updateCart(cart)
        }



    }
    /*private fun updateCart() {
        cart.clear()
        for (item in list) {
            val itemQuantity = sharedPref?.getInt("${item.title}_quantity", 0) ?: 0
            if (itemQuantity > 0) {
                val cartItem = CartItem(item.title, item.price, itemQuantity, item.imageResource)
                cart.add(cartItem)
            }
        }
    }*/





    class FoodItemsViewHolder(itemView: View) : ViewHolder(itemView) {
        var item_name = itemView.findViewById<TextView>(R.id.itemTitle);
        var price = itemView.findViewById<TextView>(R.id.price);
        var munisBtn = itemView.findViewById<Button>(R.id.minus);
        var quantity = itemView.findViewById<TextView>(R.id.quantity);
        var plus = itemView.findViewById<Button>(R.id.plus);
        var item_image = itemView.findViewById<ImageView>(R.id.item_image)
        var addToCartButton = itemView.findViewById<Button>(R.id.add_to_cart_button);



    }

}


