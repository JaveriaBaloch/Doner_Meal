package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({ /* Create an Intent that will start the Menu-Activity. */
            val register = Intent(this, Register::class.java)
            startActivity(register)
            finish()
        }, 3000)
    }
}