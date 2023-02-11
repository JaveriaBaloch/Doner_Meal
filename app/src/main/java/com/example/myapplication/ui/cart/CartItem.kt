package com.example.myapplication.ui.orders

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class CartItem(val itemName: String?, val itemPrice: Double,val itemImage: String?, val quantityItem: Int) : Serializable