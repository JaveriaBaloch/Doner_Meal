package com.example.myapplication.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.itemClass


class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View {

        val items = itemClass.getMenuItems("menu.json", requireContext())
        println(itemClass)
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val recyclerView: RecyclerView
        recyclerView = view.findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(context)




        /*val adapter = FoodItemsAdapter()
        recyclerView.adapter = adapter*/
        val list = arrayListOf<itemClass>()
        for (menuItem in items) {
            list.add(menuItem)
        }

        val adapter = FoodItemsAdapter(this, list)
        recyclerView.adapter = adapter

        return view
    }

}