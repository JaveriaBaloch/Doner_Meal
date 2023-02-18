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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.ui.cart.CartItemsAdapter
import com.example.myapplication.ui.home.cartItems
import com.example.myapplication.ui.home.itemClass
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class CheckoutFragment: Fragment() {
    lateinit var fname : EditText
    lateinit var phone_ : EditText
    lateinit var email: EditText
    lateinit var address : EditText
    lateinit var notes : EditText
    lateinit var confirmbtn : Button
    lateinit var backbtn : ImageButton

    var category:Array<String> = arrayOf("All", "Döner Gerichte", "Omlette", "Pizza", "Vegetarische Gerichte", "Salate",
        "Finger Food","Heisse Getränke","Alkoholfrei Getränke")
    var sharedPreference: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var set: cartItems? = null
    var sets: ArrayList<JSONObject>? = null




    private lateinit var database : DatabaseReference






    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_checkout, container, false)
        sharedPreference= this.activity?.getSharedPreferences("cart", Context.MODE_PRIVATE)
        editor = sharedPreference?.edit()
        val items = itemClass.getMenuItems("menu.json", requireContext())

        fname = view.findViewById<EditText>(R.id.fullnametxtbox)
        phone_ = view.findViewById<EditText>(R.id.phonenotxtbox)
        address = view.findViewById<EditText>(R.id.addresstxtbox)
        email = view.findViewById<EditText>(R.id.emailtxtbox)
        notes = view.findViewById<EditText>(R.id.notestxtbox)

        confirmbtn = view.findViewById(R.id.confirmbtn1)
        database = FirebaseDatabase.getInstance().getReference("Checkout")

        val list = arrayListOf<itemClass>()
        for (menuItem in items) {
            if((sharedPreference?.getInt(menuItem.title,0))!=0){
                list.add(menuItem)
            }
        }



        confirmbtn.setOnClickListener {
            // Initialize total value to 0
            var totalValue = 0.0

            // Loop through the selected items to calculate the total value
            for (item in list) {
                val quantity = sharedPreference?.getInt(item.title, 0) ?: 0
                val itemValue = item.price * quantity
                totalValue += itemValue
            }

            val fullname = fname.text.toString()
            val phone = phone_.text.toString()
            val email = email.text.toString()
            val address = address.text.toString()
            val additionalnotes = notes.text.toString()
            val currentDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())

            val checkoutMap = hashMapOf(
                "cartItems" to list,
                "currentDate" to currentDate,
                "totalValue" to totalValue,
                "fullname" to fullname,
                "phone" to phone,
                "email" to email,
                "address" to address,
                "additionalnotes" to additionalnotes
            )




            val checkoutuser = UserCheckout(fullname, phone, email, address, additionalnotes, list, currentDate, totalValue)

            Log.d("CheckoutFragment", "fullname: $fullname")
            Log.d("CheckoutFragment", "phone: $phone")
            Log.d("CheckoutFragment", "email: $email")
            Log.d("CheckoutFragment", "address: $address")
            Log.d("CheckoutFragment", "additionalnotes: $additionalnotes")
            Log.d("CheckoutFragment", "cartItems: ${list}")
            Log.d("CheckoutFragment", "currentDate: ${currentDate}")
            Log.d("CheckoutFragment", "totalValue: ${totalValue}")



            database.child(fullname).setValue(checkoutMap).addOnSuccessListener {
                Toast.makeText(requireContext(), "Checkout Successful", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(requireContext(), "Checkout Fail", Toast.LENGTH_SHORT).show()
            }



            Navigation.findNavController(view).navigate(R.id.action_to_home)

        }





        return view
    }
}


