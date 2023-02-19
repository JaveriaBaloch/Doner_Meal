
package com.example.myapplication.ui.cart

import android.content.Context
import android.content.SharedPreferences
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
import com.example.myapplication.ui.home.itemClass

class CartItemsAdapter(itemClass: ArrayList<itemClass>):
    RecyclerView.Adapter<CartItemsAdapter.CartItemsViewHolder>() {
    var onCartItemRemovedListener: OnCartItemRemovedListener? = null

    interface OnCartItemRemovedListener{
        fun onRemoved()
    }
    private var list: ArrayList<itemClass> = itemClass
    var selectionTracker: Array<Long>? = null
    var sharedPreference: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var set: String? = null

    var sets: MutableSet<String>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        sharedPreference = parent.getContext().getSharedPreferences("cart", Context.MODE_PRIVATE)
        editor = sharedPreference?.edit()

        return CartItemsViewHolder(itemView)
    }

    override fun getItemCount() = list.size
    override fun onBindViewHolder(holder: CartItemsViewHolder, position: Int) {
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
            holder.munisBtn.setOnClickListener {
            if(Integer.parseInt(holder.quantity.text.toString())>0) {
                val count: Int = Integer.parseInt(holder.quantity.text.toString()) - 1
                holder.quantity.text = count.toString()
                val price = list[position].price
                val final = sharedPreference?.getFloat("price",00F)?.minus(price.toFloat())
                if (final != null) {
                    editor?.putFloat("price",final.toFloat())
                }
                editor?.apply()
            }
        }
        holder.plus.setOnClickListener {
            val count: Int = Integer.parseInt(holder.quantity.text.toString()) + 1
            holder.quantity.text = count.toString()
            val price = list[position].price
            val final = sharedPreference?.getFloat("price",00F)?.plus(price.toFloat())
            if (final != null) {
                editor?.putFloat("price",final.toFloat())
            }
            editor?.apply()
        }
    }




    class CartItemsViewHolder(itemView: View) : ViewHolder(itemView) {
        var itemname: TextView = itemView.findViewById(R.id.title1)
        var price: TextView = itemView.findViewById(R.id.price1)
        var munisBtn: Button = itemView.findViewById(R.id.minus1)
        var quantity: TextView = itemView.findViewById(R.id.quantity1)
        var plus: Button = itemView.findViewById(R.id.plus1)
        var item_image: ImageView = itemView.findViewById(R.id.item_image1)



    }

}



