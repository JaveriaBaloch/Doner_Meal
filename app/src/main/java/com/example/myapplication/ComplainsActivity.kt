package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ComplainsActivity : AppCompatActivity() {
    lateinit var firestore: FirebaseFirestore
    var userEmail: String = ""
    lateinit var fAuth : FirebaseAuth
    var sharedPreference: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complains)

        val submitBtn:Button = findViewById(R.id.registerComplain)

        submitBtn.setOnClickListener {
            val complainText: EditText = findViewById(R.id.complainTextArea)

            if(complainText.text.isEmpty()){
                Toast.makeText(applicationContext, "Please write some text, form is empty!",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else{
                sharedPreference = getSharedPreferences("userInfo", Context.MODE_PRIVATE)
                val email: String? = sharedPreference?.getString("email","noEmail")
                val id:String? =sharedPreference?.getString("email","empty")
                if(email.equals("noEmail")||id.equals("empty")){
                    Toast.makeText(applicationContext, "Please Login!",Toast.LENGTH_SHORT).show()
                }
                else{
                    if (id != null) {
                        val complain:MutableMap<String,Any> = HashMap()
                        firestore = FirebaseFirestore.getInstance()
                        complain["id"] = id
                        complain["email"] = email.toString()
                        complain["complain"] = complainText.text.toString()
                       firestore.collection("complains").document().set(complain).addOnSuccessListener {
                            Toast.makeText(applicationContext, "Your complain is registered!",Toast.LENGTH_SHORT).show()
                           val mainActivity = Intent(this, MainActivity::class.java)
                           startActivity(mainActivity)
                           finish()
                        }.addOnFailureListener {
                            Toast.makeText(applicationContext, "Please submit again!",Toast.LENGTH_SHORT).show()
                        }
                    }
                }


            }
        }
    }
}