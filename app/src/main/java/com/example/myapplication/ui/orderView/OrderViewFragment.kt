package com.example.myapplication.ui.orderView

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


class OrderViewFragment : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_orders_view, container, false)
        sharedPreference= this.activity?.getSharedPreferences("show", Context.MODE_PRIVATE)
        editor = sharedPreference?.edit(
        recyclerView.layoutManager = LinearLayoutManager(context)
        var price = 0
        val list = arrayListOf<HashMap<Any,Any>>()


//        for (menuItem in items) {
//            if((sharedPreference?.getInt(menuItem.title,0))!=0){
//                    list.add(menuItem)
//                if(price>0.00){
//                    price *= menuItem.price.toInt()
//                }else
//                    price = sharedPreference?.getInt(menuItem.title,0)!!
//                }
//        }



        /*val goToOrdersButton = view.findViewById<Button>(R.id.got_to_orders)
        goToOrdersButton.setOnClickListener{
            val cartItems = JSONArray()
            for (item in list) {
                val itemJson = JSONObject()
                itemJson.put("title", item.title)
                itemJson.put("quantity", sharedPreference?.getInt(item.title, 0))
                itemJson.put("price", item.price)
                cartItems.put(itemJson)
            }
            Log.d("CartFragment", "Saved cartItems: $cartItems")

            sharedViewModel.cartItems.value = cartItems

           /* // Save the cart items in SharedPreferences
            val bundle = Bundle()
            bundle.putString("cartItems", cartItems.toString())*/

            Navigation.findNavController(view).navigate(R.id.action_to_order)

        }*/


        return view
    }

}