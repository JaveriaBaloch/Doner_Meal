
package com.example.myapplication.ui.home

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
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
import java.io.File


class FoodItemsAdapter(private val context: HomeFragment, private val itemClass: ArrayList<itemClass>):
    RecyclerView.Adapter<FoodItemsAdapter.FoodItemsViewHolder>() {
    var list: ArrayList<itemClass> = itemClass
    var selectionTracker: Array<Long>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cards, parent, false)
        return FoodItemsViewHolder(itemView)
    }

    override fun getItemCount() = list.size
    override fun onBindViewHolder(holder: FoodItemsViewHolder, position: Int) {

        /*val name = list[position]
        holder.item_name.setText(name as CharSequence)*/
        holder.item_name.text = list.get(position).title
        holder.price.text = "£" + list.get(position).price.toString()
        val image = list[position].imageResource
        holder.item_image.load(image) {
            crossfade(true)
            placeholder(R.drawable.loading_background)

        }
            holder.munisBtn.setOnClickListener {
            if(Integer.parseInt(holder.quantity.text.toString())>0) {
                val count: Int = Integer.parseInt(holder.quantity.text.toString()) - 1;
                holder.quantity.setText(count.toString())
            }
        }
        holder.plus.setOnClickListener {

                val count: Int = Integer.parseInt(holder.quantity.text.toString()) + 1;
                holder.quantity.setText(count.toString())

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



