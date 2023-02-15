package com.example.myapplication.ui.orders

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentOrdersBinding
import com.example.myapplication.ui.checkout.UserCheckout
import com.example.myapplication.ui.home.itemClass
import com.google.firebase.database.*

class OrdersFragment : Fragment() {

    private lateinit var database: DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_orders, container, false)

        database = FirebaseDatabase.getInstance().getReference("Checkout")

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler2)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val query: Query = database.orderByKey()
        val ordersList = ArrayList<itemClass>()
        val adapter = OrdersAdapter(ordersList)
        recyclerView.adapter = adapter
        val listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (orderSnapshot in dataSnapshot.children) {
                    val order = orderSnapshot.getValue(UserCheckout::class.java)
                    order?.cartItems?.let { cartItems ->
                        ordersList.addAll(cartItems)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("OrdersFragment", "Failed to read value.", databaseError.toException())
            }
        }
        query.addValueEventListener(listener)




        return view
    }


}