package com.example.myapplication.ui.signout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignOutViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "SignOut"
    }
    val text: LiveData<String> = _text
}