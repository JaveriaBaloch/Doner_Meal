package com.example.myapplication.ui.ordersList

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication.R

class OrdersListAdapter(itemClass: ArrayList<HashMap<Any, Any>>):
    RecyclerView.Adapter<OrdersListAdapter.OrdersViewHolder>() {
    private var list: ArrayList<HashMap<Any, Any>> = itemClass
    var selectionTracker: Array<Long>? = null
    var sharedPreference: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var set: String? = null
    var sets: MutableSet<String>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.order_list, parent, false)
        sharedPreference = parent.getContext().getSharedPreferences("show", Context.MODE_PRIVATE)
        editor = sharedPreference?.edit()
        return OrdersViewHolder(itemView)

    }

    override fun getItemCount() = list.size
    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        holder.date.text = list[position]["date"].toString()
        holder.show.setOnClickListener{

            editor?.putString("id",list[position]["id"].toString())
            editor?.apply()
        }
    }

    class OrdersViewHolder(itemView: View) : ViewHolder(itemView) {
        val date = itemView.findViewById<TextView>(R.id.ordersDate)
        val show = itemView.findViewById<Button>(R.id.show)
    }

}



