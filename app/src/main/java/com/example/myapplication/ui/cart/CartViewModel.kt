package com.example.myapplication.ui.cart

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.ui.orders.CartItem


class CartViewModel : ViewModel() {

    val _cart = MutableLiveData<ArrayList<CartItem>>(ArrayList())
    val cart: LiveData<ArrayList<CartItem>>
        get() = _cart



    fun saveCartItems(item: CartItem) {
        val currentCart = _cart.value ?: arrayListOf()
        currentCart.add(item)
        _cart.value = currentCart
        Log.d("OrdersViewModel", "cart jsahfd: ${item}")
    }
    fun addOrder(item: CartItem) {
        val currentCart = _cart.value ?: arrayListOf()

        currentCart.add(item)
        _cart.value = currentCart
        _cart.postValue(_cart.value)
        saveCartItems(item)
        Log.d("OrdersViewModel", "cart dsfdds: ${item}")




    }

    fun updateCart(itemName: String, itemPrice: Double, itemImage: String, quantityItem: Int) {
        val cartItem = CartItem(itemName, itemPrice, itemImage, quantityItem)
        val currentCart = _cart.value ?: arrayListOf()
        currentCart.add(cartItem)
        _cart.value = currentCart
        _cart.postValue(currentCart)

        Log.d("OrdersViewModel", "cart asfjkad: ${_cart.value}")


    }








}