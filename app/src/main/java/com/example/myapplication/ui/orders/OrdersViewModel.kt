package com.example.myapplication.ui.orders

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.itemClass

class OrdersViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Orders Fragment"
    }
    val text: LiveData<String> = _text

    val _cart = MutableLiveData<ArrayList<itemClass>>()
    val cart: LiveData<ArrayList<itemClass>>
        get() = _cart


    fun updateCart(cart: ArrayList<itemClass>) {
        /*val orderList = ArrayList<itemClass>()
        for (item in cart) {
            val orderItem = OrderItem(item.title, item.price.toString(), item.quantity.toDouble())
            orderList.add(orderItem)
        }*/
        Log.d("OrdersViewModel", "cart size: ${cart.size}")

        _cart.value = cart
    }

}