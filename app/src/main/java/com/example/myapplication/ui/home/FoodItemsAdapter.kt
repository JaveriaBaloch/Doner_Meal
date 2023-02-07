
package com.example.myapplication.ui.home
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.icu.number.IntegerWidth
import android.os.ParcelFileDescriptor.open
import android.system.Os.open
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication.R
import org.json.JSONArray

import java.net.URL
import java.io.File
import android.content.Context

import androidx.appcompat.app.AppCompatActivity
import java.io.IOException
import java.io.InputStream
import android.content.res.AssetManager
import org.json.JSONException
import org.json.JSONObject

class FoodItemsAdapter():
    RecyclerView.Adapter<FoodItemsAdapter.FoodItemsViewHolder>() {

    var list: List<String> = arrayListOf()
    var selectionTracker: Array<Long>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cards, parent, false)

        return FoodItemsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FoodItemsViewHolder, position: Int) {

        val name = list[position]
        holder.item_name.setText(name)
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

    override fun getItemCount() = list.size


    class FoodItemsViewHolder(itemView: View) : ViewHolder(itemView) {
        var item_name = itemView.findViewById<TextView>(R.id.itemTitle);
        var price = itemView.findViewById<TextView>(R.id.price);
        var munisBtn = itemView.findViewById<Button>(R.id.minus);
        var quantity = itemView.findViewById<TextView>(R.id.quantity);
        var plus = itemView.findViewById<Button>(R.id.plus);


    }

}

