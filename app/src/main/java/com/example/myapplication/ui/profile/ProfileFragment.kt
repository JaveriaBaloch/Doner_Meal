package com.example.myapplication.ui.profile
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class ProfileFragment : Fragment() {
    var sharedPreference: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    lateinit var firestore: FirebaseFirestore
    var userID: String = ""
    lateinit var fAuth : FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        sharedPreference= this.activity?.getSharedPreferences("userInfo", Context.MODE_PRIVATE)
        editor = sharedPreference?.edit()
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val name = view.findViewById<EditText>(R.id.username)
        val address = view.findViewById<EditText>(R.id.useraddress)
        val phone = view.findViewById<EditText>(R.id.userphone)
        val btnSave = view.findViewById<Button>(R.id.btn_update_profile)

        name.setText(sharedPreference?.getString("name","notSet"))
        address.setText(sharedPreference?.getString("address","notSet"))
        phone.setText(sharedPreference?.getString("phone","notSet"))

        btnSave?.setOnClickListener {
            val id =  sharedPreference?.getString("id","notSet").toString()
            var update: MutableMap<String,Any> = HashMap()
            update["id"] = id
            update["name"] = name.text.toString()
            update["address"] = address.text.toString()
            update["phone"] = phone.text.toString()
            FirebaseFirestore.getInstance().collection("users").document(id).set(update, SetOptions.merge()).addOnCompleteListener {
                Toast.makeText(context,"Updates are saved successfully!",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(context,"Updates are not saved",Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }
}
