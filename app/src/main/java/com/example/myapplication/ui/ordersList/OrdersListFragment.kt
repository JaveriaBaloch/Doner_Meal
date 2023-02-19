package com.example.myapplication.ui.ordersList

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
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
    var orders = ArrayList<HashMap<Any, Any>>()
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
        val recyclerView: RecyclerView = view.findViewById(R.id.order_list_recycler)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = OrdersListAdapter(orders)
        if (email != null) {
            firestore.collection("orders").whereEqualTo("email","${email.toString()}")
                .get()
                .addOnSuccessListener { documents ->
                    documents.forEach{info->
                        var orders = ArrayList<HashMap<Any, Any>>()
                        var order =  hashMapOf<Any,Any>(
                            "date" to info.getString("date").toString(),
                            "price" to info.getString("price").toString(),
                            "address" to info.getString("address").toString(),
                            "note" to info.getString("additionalnotes").toString(),
                        )
                        orders.add(order)

                        val adapter = OrdersListAdapter(orders)
                        recyclerView.adapter = adapter
                    }

                } .addOnFailureListener { exception ->
                    Log.w("TAG", "Error getting documents: ", exception)
                }
        }
        Log.d("order", orders.size.toString())
        recyclerView.adapter = adapter
        return view
    }

}