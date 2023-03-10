package com.example.myapplication.ui.cart

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.ui.home.cartItems
import com.example.myapplication.ui.home.itemClass
import org.json.JSONObject


class CartFragment : Fragment() {
    var category:Array<String> = arrayOf("All", "Döner Gerichte", "Omlette", "Pizza", "Vegetarische Gerichte", "Salate",
    "Finger Food","Heisse Getränke","Alkoholfrei Getränke")
    var sharedPreference: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var set: cartItems? = null
    var sets: ArrayList<JSONObject>? = null



    @SuppressLint("MissingInflatedId")
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
        var price = 0.00
        val list = arrayListOf<itemClass>()
        for (menuItem in items) {
            if((sharedPreference?.getInt(menuItem.title,0))!=0){
                    list.add(menuItem)
                if(price>0){
                    price += menuItem.price
                }else
                    price += menuItem.price*sharedPreference?.getInt(menuItem.title,0)!!
                    editor?.putFloat("price",price.toFloat())
                    editor?.apply()
                }
        }
        val priceText = view.findViewById<TextView>(R.id.total_price)
        sharedPreference?.registerOnSharedPreferenceChangeListener { sharedPreferences, key ->
            priceText.text = "Total: € "+sharedPreference?.getFloat("price", 0.0F).toString()
        }
        priceText.text = "Total: € "+sharedPreference?.getFloat("price", 0.0F).toString()
        toString()
        val adapter = CartItemsAdapter(list)
        recyclerView.adapter = adapter
        val goToOrdersButton = view.findViewById<Button>(R.id.got_to_orders)
        goToOrdersButton.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_to_order)

        }




        return view
    }

}