package com.example.myapplication.ui.cart

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.ui.home.cartItems
import com.example.myapplication.ui.home.itemClass
import kotlinx.coroutines.NonDisposableHandle.parent
import org.json.JSONObject


class CartFragment : Fragment() {
    var category:Array<String> = arrayOf("All", "Döner Gerichte", "Omlette", "Pizza", "Vegetarische Gerichte", "Salate",
    "Finger Food","Heisse Getränke","Alkoholfrei Getränke")
    var sharedPreference: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var set: cartItems? = null
    var sets: ArrayList<JSONObject>? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View {
        val view = inflater.inflate(R.layout.fragment_cart, container, false)
        sharedPreference= this.activity?.getSharedPreferences("cart", Context.MODE_PRIVATE)
        editor = sharedPreference?.edit()
        val items = itemClass.getMenuItems("menu.json", requireContext())
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler1)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val list = arrayListOf<itemClass>()
        for (menuItem in items) {
            if((sharedPreference?.getInt(menuItem.title,0))!=0){
                    list.add(menuItem)
                }
        }
        val t = this
        val adapter = CartItemsAdapter(list)
        recyclerView.adapter = adapter
        return view
    }

}