package com.example.myapplication.ui.checkout

import com.example.myapplication.ui.home.itemClass
import org.json.JSONArray


data class UserCheckout(val fullname:String?=null,val phone:String?=null,val email:String?=null,val address:String?=null,val additionalnotes: String?=null,  val cartItems: List<itemClass>? = null ,  val currentDate: String? = null, var totalValue: Double?= null){

}