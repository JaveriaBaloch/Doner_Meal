package com.example.myapplication.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.itemClass


class HomeFragment : Fragment() {
    var category:Array<String> = arrayOf("All", "Döner Gerichte", "Omlette", "Pizza", "Vegetarische Gerichte", "Salate",
    "Finger Food","Heisse Getränke","Alkoholfrei Getränke")


    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View {

        val items = itemClass.getMenuItems("menu.json", requireContext())
        println(itemClass)
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val list = arrayListOf<itemClass>()
        for (menuItem in items) {
            list.add(menuItem)
        }
        val t = this
        val adapter = FoodItemsAdapter(this, list)
        recyclerView.adapter = adapter
        val spinner: Spinner = view.findViewById(R.id.mySpinner)
        val aa = context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_dropdown_item,category) }
        spinner.adapter =aa
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                val check = arrayListOf<itemClass>()
                if(category[p2].equals("All", ignoreCase = true)){
                    val adapter = FoodItemsAdapter(t, list)
                    recyclerView.adapter = adapter
                }
                else {
                    for (menuItem in items) {
                        if (menuItem.category.contains(category[p2], ignoreCase = true)) {
                            check.add(menuItem)

                        }

                    }
                    val adapterShow = FoodItemsAdapter(t, check)
                    recyclerView.adapter = adapterShow
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                val adapter = FoodItemsAdapter(t, list)
                recyclerView.adapter = adapter
            }

        }
        val editText: EditText = view.findViewById(R.id.search_text)
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