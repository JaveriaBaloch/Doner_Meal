package com.example.myapplication.ui.checkout

import androidx.lifecycle.ViewModel
import com.example.myapplication.ui.home.cartItems

class CheckoutViewModel : ViewModel() {

    var cartItems: List<cartItems> = emptyList()
    var checkoutUser: UserCheckout? = null
}