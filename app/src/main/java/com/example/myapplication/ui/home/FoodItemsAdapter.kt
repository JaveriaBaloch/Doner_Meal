
package com.example.myapplication.ui.home
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import java.io.InputStream
import java.net.URL


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

