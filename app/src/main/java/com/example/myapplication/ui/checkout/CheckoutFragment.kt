package com.example.myapplication.ui.checkout

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.myapplication.R
import com.example.myapplication.ui.home.cartItems
import com.example.myapplication.ui.home.itemClass
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class CheckoutFragment: Fragment() {
    lateinit var fname : EditText
    lateinit var phone_ : EditText
    lateinit var email: EditText
    lateinit var address : EditText
    lateinit var notes : EditText
    lateinit var confirmbtn : Button
    lateinit var backbtn : ImageButton

    lateinit var firestore: FirebaseFirestore
    var userID: String = ""
    lateinit var fAuth : FirebaseAuth
    var category:Array<String> = arrayOf("All", "Döner Gerichte", "Omlette", "Pizza", "Vegetarische Gerichte", "Salate",
        "Finger Food","Heisse Getränke","Alkoholfrei Getränke")
    var sharedPreference: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var set: cartItems? = null
    var sets: ArrayList<JSONObject>? = null




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val userinfo =this.activity?.getSharedPreferences("userInfo", Context.MODE_PRIVATE)

        val view = inflater.inflate(R.layout.fragment_checkout, container, false)
        sharedPreference= this.activity?.getSharedPreferences("cart", Context.MODE_PRIVATE)
        editor = sharedPreference?.edit()
        val items = itemClass.getMenuItems("menu.json", requireContext())
        firestore = FirebaseFirestore.getInstance()
        fname = view.findViewById<EditText>(R.id.fullnametxtbox)
        phone_ = view.findViewById<EditText>(R.id.phonenotxtbox)
        address = view.findViewById<EditText>(R.id.addresstxtbox)
        email = view.findViewById<EditText>(R.id.emailtxtbox)
        notes = view.findViewById<EditText>(R.id.notestxtbox)
        confirmbtn = view.findViewById(R.id.confirmbtn1)

        fname.setText(userinfo?.getString("name","none"))
        phone_.setText(userinfo?.getString("phone","none"))
        email.setText(userinfo?.getString("email","none"))
        address.setText(userinfo?.getString("address","none"))
        var price = 0
        val list = arrayListOf<HashMap<Any,Any>>()
        for (menuItem in items) {
            if((sharedPreference?.getInt(menuItem.title,0))!=0){
                val item = hashMapOf<Any,Any>(
                    "quantity" to sharedPreference?.getInt(menuItem.title,0).toString(),
                    "ordered_Items" to menuItem
                )
                list.add(item)
                if(price>0){
                    price *= menuItem.price.toInt()
                }else
                    price = sharedPreference?.getInt(menuItem.title,0)!!
            }
        }



        confirmbtn.setOnClickListener {
            // Initialize total value to 0
            var totalValue = 0.0
            val fullname = fname.text.toString()
            val phone = phone_.text.toString()
            val email = email.text.toString()
            val address = address.text.toString()
            val additionalnotes = notes.text.toString()
            val currentDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())

            val orderDate = hashMapOf(
                "date" to currentDate,
                "cartItems" to list,
                "price" to price.toString(),
                "fullname" to fullname,
                "phone" to phone,
                "email" to email,
                "address" to address,
                "additionalnotes" to additionalnotes
            )
            firestore.collection("orders").document().set(orderDate, SetOptions.merge()).addOnSuccessListener {
                editor?.clear()
                editor?.apply()
            }


            Navigation.findNavController(view).navigate(R.id.action_to_home)

        }





        return view
    }
}


