package com.example.myapplication.ui.signout

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSignoutBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class SignOutFragment : Fragment() {

    private var _binding: FragmentSignoutBinding? = null
    private val binding get() = _binding!!
    lateinit var firestore: FirebaseFirestore
    lateinit var fAuth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_signout, container, false)
        val root: View = binding.root
        fAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        val btn = view.findViewById<Button>(R.id.logoutButton)
        btn.setOnClickListener {

            if(fAuth.currentUser!=null){
               fAuth.signOut()
                requireActivity().run{
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    finish()
                }

            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}