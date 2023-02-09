package com.example.myapplication.ui.orders

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentOrdersBinding
import com.example.myapplication.itemClass
import com.example.myapplication.ui.home.FoodItemsAdapter
import kotlinx.android.synthetic.main.cards.*

class OrdersFragment : Fragment() {

    private lateinit var ordersViewModel: OrdersViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: OrdersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ordersViewModel = ViewModelProvider(this).get(OrdersViewModel::class.java)

        val view = inflater.inflate(R.layout.fragment_orders, container, false)
        setupRecyclerView(view)
        observeCartData()

        return view
    }

    private fun setupRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.recycler1)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = OrdersAdapter(this, ArrayList())
        recyclerView.adapter = adapter
    }

    private fun observeCartData() {
        ordersViewModel.cart.observe(viewLifecycleOwner, Observer {
            Log.d("OrdersFragment", "cart size: ${it.size}")
            adapter.setData(it)
        })
    }
}