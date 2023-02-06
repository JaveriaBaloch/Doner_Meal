package com.example.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val recyclerView: RecyclerView
        recyclerView = view.findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val adapter = FoodItemsAdapter();
        recyclerView.adapter = adapter
        val list = arrayListOf<String>()
        for (i in 1..10) {
            list.add("Name")
        }
        adapter.list = list

        return view
    }
}