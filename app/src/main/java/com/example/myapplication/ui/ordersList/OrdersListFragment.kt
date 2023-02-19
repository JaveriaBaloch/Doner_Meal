package com.example.myapplication.ui.orders

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class OrdersListFragment : Fragment() {
    var category:Array<String> = arrayOf("All", "Döner Gerichte", "Omlette", "Pizza", "Vegetarische Gerichte", "Salate",
        "Finger Food","Heisse Getränke","Alkoholfrei Getränke")
    var sharedPreference: SharedPreferences? = null
    lateinit var firestore: FirebaseFirestore
    var userID: String = ""
    lateinit var fAuth : FirebaseAuth
    val orders = arrayListOf<HashMap<Any, Any>>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View {
        sharedPreference = this.activity?.getSharedPreferences("userInfo", Context.MODE_PRIVATE)
       val email = sharedPreference?.getString("email","0")

        val view = inflater.inflate(R.layout.fragment_orders_list, container, false)
        fAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        firestore.collection("orders").whereEqualTo("email",email).get()
            .addOnSuccessListener { documents ->
            documents.forEach{info->
                var order =  hashMapOf<Any,Any>(
                    "date" to info.getString("date").toString(),
                )
                orders.add(order)
                val p = orders[0]["date"].toString()
                Log.d("order", p)
            }

        }
        val recyclerView: RecyclerView = view.findViewById(R.id.order_list_recycler)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = OrdersListAdapter(orders)
        recyclerView.adapter = adapter

        return view
    }

}