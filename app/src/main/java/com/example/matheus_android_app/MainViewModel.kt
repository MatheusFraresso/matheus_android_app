package com.example.matheus_android_app

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matheus_android_app.model.Activity
import com.example.matheus_android_app.repository.Repository
import kotlinx.coroutines.launch


class MainViewModel(private val repository: Repository) : ViewModel() {
    val myResponse: MutableLiveData<Activity> = MutableLiveData()
    fun getActivity() {
        viewModelScope.launch {
            val response: Activity = repository.getActivity();
            myResponse.value = response;
        }
    }

    fun getActivityByType(type: String) {
        viewModelScope.launch {
            val response: Activity = repository.getActivityByType(type);
            myResponse.value = response;
        }
    }

}