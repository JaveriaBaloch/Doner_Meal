
package com.example.myapplication.ui.home

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
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
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.protobuf.Parser

class FoodItemsAdapter(itemClass: ArrayList<itemClass>):
    RecyclerView.Adapter<FoodItemsAdapter.FoodItemsViewHolder>() {
    private var list: ArrayList<itemClass> = itemClass
    var selectionTracker: Array<Long>? = null
    var sharedPreference: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var set: String? = null

    var sets: MutableSet<String>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cards, parent, false)
        sharedPreference = parent.getContext().getSharedPreferences("cart", Context.MODE_PRIVATE)
        editor = sharedPreference?.edit()
        return FoodItemsViewHolder(itemView)
    }

    override fun getItemCount() = list.size
    override fun onBindViewHolder(holder: FoodItemsViewHolder, position: Int) {
       holder.itemname.text = list.get(position).title
        holder.price.text = "€" + list.get(position).price.toString()
        var  count=sharedPreference?.getInt(list.get(position).title,0)
        val image = list[position].imageResource
        sharedPreference?.registerOnSharedPreferenceChangeListener { sharedPreferences, key ->
              count =sharedPreference?.getInt(list.get(position).title,0)

        }

        holder.quantity.text = count.toString()
        sharedPreference?.registerOnSharedPreferenceChangeListener { sharedPreferences, key ->
            val set =sharedPreference?.getInt(list.get(position).title,0)
            holder.quantity.text = set.toString()
        }
        holder.item_image.load(image) {
            crossfade(true)
            placeholder(R.drawable.loading_background)

        }
            holder.munisBtn.setOnClickListener {

            if(Integer.parseInt(holder.quantity.text.toString())>0) {
                    val count: Int = Integer.parseInt(holder.quantity.text.toString()) - 1
                    holder.quantity.text = count.toString()
                    editor?.putInt(list[position].title,count)
                    editor?.apply()

            }
        }
        holder.plus.setOnClickListener {

            val count: Int = Integer.parseInt(holder.quantity.text.toString()) + 1;
            holder.quantity.text = count.toString()
                editor?.putInt(list[position].title.toString(), count)
                editor?.apply()

        }
    }
    class FoodItemsViewHolder(itemView: View) : ViewHolder(itemView) {
        var itemname = itemView.findViewById<TextView>(R.id.itemTitle);
        var price = itemView.findViewById<TextView>(R.id.price);
        var munisBtn = itemView.findViewById<Button>(R.id.minus);
        var quantity = itemView.findViewById<TextView>(R.id.quantity);
        var plus = itemView.findViewById<Button>(R.id.plus);
        var item_image = itemView.findViewById<ImageView>(R.id.item_image)

    }

}



