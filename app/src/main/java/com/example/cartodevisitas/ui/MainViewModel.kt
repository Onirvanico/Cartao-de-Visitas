package com.example.cartodevisitas.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cartodevisitas.data.BusinessCardRepository
import java.lang.IllegalArgumentException

class MainViewModel(val repository: BusinessCardRepository) : ViewModel() {

}

class MainViewModelFactory(private val respository: BusinessCardRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(respository) as T
        }

        throw IllegalArgumentException("unknow viewModel")
    }

}