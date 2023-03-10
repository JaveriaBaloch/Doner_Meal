package com.example.myapplication


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    lateinit var firestore: FirebaseFirestore
    lateinit var fAuth : FirebaseAuth
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    var sharedPreference: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPreference= getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        super.onCreate(savedInstanceState)
        fAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        if (sharedPreference?.getString("name","empty") !="empty"){
            setContentView(R.layout.activity_splash_screen)
            Handler().postDelayed({
            setDrawer()
            }, 2000)
        }else{
            signup()
        }
    }

    private fun signup(){
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed({
            val register = Intent(this, Register::class.java)
            startActivity(register)
            finish()
        }, 2000)
    }

    private fun setDrawer(){

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.complainButton.setOnClickListener {

            val writeComplain = Intent(this, ComplainsActivity::class.java)
            startActivity(writeComplain)
            finish()

        }

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.content)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.home, R.id.order_list_layout, R.id.profile,R.id.signout
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.content)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}