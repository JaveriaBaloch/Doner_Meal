package com.example.myapplication.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.itemClass
import com.google.android.material.textfield.TextInputEditText


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
        val list = arrayListOf<itemClass>()
        for (menuItem in items) {
            list.add(menuItem)
        }
        val t = this
        val adapter = FoodItemsAdapter(this, list)
        recyclerView.adapter = adapter

        var editText:TextInputEditText = view.findViewById(R.id.search_text)
          editText.addTextChangedListener(object : TextWatcher {
              val check = arrayListOf<itemClass>()
              override fun afterTextChanged(s: Editable) {


            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
                check.clear()
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                for (menuItem in items) {
                    if (menuItem.title.contains(editText.text.toString(),ignoreCase = true)){
                        check.add(menuItem)
                        val adapter = FoodItemsAdapter(t, check)
                        recyclerView.adapter = adapter
                    }

                }
            }
        })



        return view
    }

}