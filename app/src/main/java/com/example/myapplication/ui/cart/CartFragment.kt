package com.example.myapplication.ui.cart

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.ui.checkout.CheckoutViewModel
import com.example.myapplication.ui.home.cartItems
import com.example.myapplication.ui.home.itemClass
import org.json.JSONArray
import org.json.JSONObject

/*class SharedViewModel : ViewModel() {
    val cartItems: MutableLiveData<JSONArray> by lazy {
        MutableLiveData<JSONArray>()
    }
}*/

class CartFragment : Fragment() {
    var category:Array<String> = arrayOf("All", "Döner Gerichte", "Omlette", "Pizza", "Vegetarische Gerichte", "Salate",
    "Finger Food","Heisse Getränke","Alkoholfrei Getränke")
    var sharedPreference: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var set: cartItems? = null
    var sets: ArrayList<JSONObject>? = null


    /*private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Retrieve the ViewModel instance
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }*/



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
        val goToOrdersButton = view.findViewById<Button>(R.id.got_to_orders)
        goToOrdersButton.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_to_order)

        }



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