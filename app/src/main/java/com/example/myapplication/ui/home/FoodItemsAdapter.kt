
package com.example.myapplication

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.LayoutInflaterCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import org.json.JSONArray
import java.io.InputStream
import java.net.URL


class FoodItemsAdapter(private val items: JSONArray):RecyclerView.Adapter<FoodItemsAdapter.FoodItemsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(parent,R.layout.cards,false)
        return FoodItemsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FoodItemsViewHolder, position: Int) {
        var bmp: Bitmap;
        val imageUrl = items.getJSONObject(position).get("img").toString()
        val `in`: InputStream = URL(imageUrl).openStream()
        bmp = BitmapFactory.decodeStream(`in`)


        holder.item_name.setText(items.getJSONObject(position).get("name").toString())
        holder.price.setText(items.getJSONObject(position).get("price").toString())
        holder.img.setImageBitmap(bmp);
    }

    override fun getItemCount() = items.length()


    class FoodItemsViewHolder(itemView: View) : ViewHolder(itemView) {
        var item_name = itemView.findViewById<TextView>(R.id.itemTitle);
        var price = itemView.findViewById<TextView>(R.id.price);
        var img = itemView.findViewById<ImageView>(R.id.item_image);
        var munisBtn = itemView.findViewById<Button>(R.id.minus);
        var quantity = itemView.findViewById<TextView>(R.id.quantity);
        var plus = itemView.findViewById<Button>(R.id.plus);


    }
}