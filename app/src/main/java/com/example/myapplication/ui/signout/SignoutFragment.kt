package com.example.myapplication.ui.signout

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.myapplication.MainActivity
import com.example.myapplication.R


class SignoutFragment : Fragment() {
    var sharedPreference: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sharedPreference= this.activity?.getSharedPreferences("userInfo", Context.MODE_PRIVATE)
        editor = sharedPreference?.edit()
        val view = inflater.inflate(R.layout.fragment_signout, container , false)
        val btn = view.findViewById<Button>(R.id.logoutButton)
        btn.setOnClickListener {
            updateDetail()
        }

        return view
    }
    private fun updateDetail() {
        editor?.clear()
        editor?.apply()
        val intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)
    }

}