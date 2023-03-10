package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.SignInMethodQueryResult
import com.google.firebase.firestore.FirebaseFirestore

class Register : AppCompatActivity() {
    lateinit var username:EditText
    lateinit var useremail:EditText
    lateinit var userpassword:EditText
    lateinit var userConfirmPassword:EditText
    lateinit var useraddress:EditText
    lateinit var userphone:EditText
    lateinit var btnRegister:Button
    lateinit var btnLogin: TextView
    lateinit var firestore: FirebaseFirestore
    var userID: String = ""
    lateinit var fAuth :FirebaseAuth
    var sharedPreference: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        sharedPreference = getSharedPreferences("userInfo", Context.MODE_PRIVATE)
        editor = sharedPreference?.edit()
        username = findViewById(R.id.usernameRegister)

        useremail =findViewById(R.id.useremailRegister)
        userpassword = findViewById(R.id.userPasswordRegister)
        userConfirmPassword = findViewById(R.id.confirmPasswordRegister)
        useraddress = findViewById(R.id.userAddressRegister)
        userphone = findViewById(R.id.userPhoneRegister)
        btnRegister = findViewById(R.id.registerbutton)
        btnLogin = findViewById(R.id.loginLink)
        fAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        if (sharedPreference?.getString("name","empty") !="empty"){
            val mainActivity = Intent(this, MainActivity::class.java)
            startActivity(mainActivity)
            finish()
        }


        btnLogin.setOnClickListener {
            val login = Intent(this, Login_Page::class.java)
            startActivity(login)

        }
        btnRegister.setOnClickListener {
            val name = username.text.toString()
            val email = useremail.text.toString()
            val password = userpassword.text.toString()
            val address = useraddress.text.toString()
            val phone = userphone.text.toString()
            val confirmPassword = userConfirmPassword.text.toString()
            if(TextUtils.isEmpty(email)){
                useremail.error = "Required"
                return@setOnClickListener
                }
            if(TextUtils.isEmpty(name)){
                username.error = "Required"
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(password)){
                userpassword.error = "Required"
                return@setOnClickListener
            }
            if(password.length<8){
                userpassword.error = "Password should be 8 characters"
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(address)){
                useraddress.error = "Required"
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(phone)){
                userphone.error = "Required"
                return@setOnClickListener
            }
            if((confirmPassword != password)){
                Toast.makeText(applicationContext,"Passwords are not match",Toast.LENGTH_SHORT).show()
            }

            if(!userExists(email)){
                createNewUser(email,password,name,address,phone)
            }else{
                userExists(email)
            }

        }
    }
       fun createNewUser(email:String,password:String,name:String,address:String,phone:String){
           fAuth.createUserWithEmailAndPassword(email, password)
               .addOnCompleteListener(this) { task ->
                   if (task.isSuccessful) {
                       val fuser: FirebaseUser? = fAuth.currentUser
                       fuser?.sendEmailVerification()?.addOnCompleteListener (this){
                           Toast.makeText(applicationContext,"Register Successfully",Toast.LENGTH_SHORT).show()
                       }
                       userID = fAuth.currentUser?.uid.toString()
                       val user: MutableMap<String, Any> = HashMap()
                       user["id"] = userID
                       user["name"] = name
                       user["email"] = email
                       user["password"] = password
                       user["address"] = address
                       user["phone"] = phone
                       editor?.putString("id",userID)
                       editor?.putString("name",name)
                       editor?.putString("address",address)
                       editor?.putString("phone",phone)
                       editor?.putString("email",email)
                       editor?.apply()
                       FirebaseFirestore.getInstance().collection("users").document(userID).set(user).addOnSuccessListener  {
                       }.addOnFailureListener {
                           Log.d("TAG","Failed")
                       }

                       val mainActivity = Intent(this, MainActivity::class.java)
                       startActivity(mainActivity)
                       finish()
                   } else{
                       Toast.makeText(applicationContext,"Error: "+ (task.exception?.message.toString()),Toast.LENGTH_SHORT).show()
                   }
               }.addOnFailureListener {
                   Log.d("onFailure","Email not sent")
               }

       }
        fun userExists(email: String):Boolean{
            var exists:Boolean = false
            fAuth.fetchSignInMethodsForEmail(email).addOnCompleteListener { task: Task<SignInMethodQueryResult>->
                val check: Boolean = task.result.signInMethods?.isEmpty() == true
                if(check)
                {
                   exists = false
                }
                else
                {
                    Toast.makeText(applicationContext, "This email has been registered.", Toast.LENGTH_SHORT).show()
                    exists = true
                }

            }
            return exists
        }
    }
