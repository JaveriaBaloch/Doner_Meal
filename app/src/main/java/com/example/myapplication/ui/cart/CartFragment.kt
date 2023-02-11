package com.example.myapplication.ui.cart

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentOrdersBinding
import com.example.myapplication.itemClass
import com.example.myapplication.ui.home.FoodItemsAdapter
import com.example.myapplication.ui.orders.CartItem
import com.example.myapplication.ui.orders.OrdersViewModel

class CartFragment() : Fragment() {

    private lateinit var cartViewModel: CartViewModel
    private lateinit var cart: ArrayList<CartItem>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        cart = ArrayList()


        arguments?.let {
            cart = it.getSerializable("cart") as ArrayList<CartItem>
        }
        Log.d("HomeFragment", "cart size before passing to CartFragment: ${cart.size}")

    }





    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {




        val view = inflater.inflate(R.layout.fragment_cart, container, false)


        val recyclerView: RecyclerView = view.findViewById(R.id.recycler1)

        recyclerView.layoutManager = LinearLayoutManager(context)
        val list = arrayListOf<CartItem>()
        val adapter = CartAdapter(this, cart)
        recyclerView.adapter = adapter



        return view

    }


}
