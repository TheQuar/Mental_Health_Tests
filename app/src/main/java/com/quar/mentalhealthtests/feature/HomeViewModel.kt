package com.quar.mentalhealthtests.feature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quar.mentalhealthtests.data.HomeRepository
import com.quar.mentalhealthtests.data.room.table.Category
import kotlinx.coroutines.launch

class HomeViewModel(private val mainRepository: HomeRepository) : ViewModel() {
    private val _category = MutableLiveData<List<Category>>()
    val category: LiveData<List<Category>>
        get() = _category


    fun getCategory() = viewModelScope.launch {
        _category.value = mainRepository.getCategory()
    }



}