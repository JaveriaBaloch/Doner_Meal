package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_register.*

class Login_Page : AppCompatActivity() {
    lateinit var useremail: EditText
    lateinit var userpassword: EditText
    lateinit var loginButton: Button
    lateinit var register: TextView

    lateinit var firestore: FirebaseFirestore
    var userID: String = ""
    lateinit var fAuth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)


        useremail = findViewById(R.id.useremailLogin)
        userpassword = findViewById(R.id.userPasswordLogin)
        loginButton = findViewById(R.id.loginbutton)
        register = findViewById(R.id.register_link)

        fAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        if (fAuth.currentUser!=null){
            val mainActivity = Intent(this, MainActivity::class.java)
            startActivity(mainActivity)
            finish()

        }
        register.setOnClickListener {
            val registerActivity = Intent(this, Register::class.java)
            startActivity(registerActivity)
            finish()
        }

        loginButton.setOnClickListener {
            val email = useremail.text.toString().trim()
            val password = userpassword.text.toString()
            if(TextUtils.isEmpty(email)){
                useremailRegister.error = "Required"
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(password)){
                userPasswordRegister.error = "Required"
                return@setOnClickListener
            }
            fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    val intent= Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(applicationContext,exception.localizedMessage, Toast.LENGTH_LONG).show()
            }

        }
    }
}