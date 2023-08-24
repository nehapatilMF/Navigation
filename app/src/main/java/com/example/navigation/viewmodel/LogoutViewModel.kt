package com.example.navigation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigation.Client.RetrofitClient
import com.example.navigation.apiInterface.ApiInterface
import kotlinx.coroutines.launch

class LogoutViewModel<LogoutResponse> : ViewModel() {
    private val apiInterface = RetrofitClient.getInstance()?.create(ApiInterface::class.java)!!

    private val logoutResponseLiveData: MutableLiveData<LogoutResponse?> = MutableLiveData()

    val logoutSuccessLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun logout() {
        viewModelScope.launch {
            try {
                val response = apiInterface.logout()
                if (response.code()==200) {
                    val apiResponse = response.body()!!
                    apiResponse.also { this@LogoutViewModel.logoutResponseLiveData.value}
                    logoutSuccessLiveData.value = apiResponse.message == "Logout Successfully"
                }
            } catch (e: Exception) {
               Log.e("LogoutViewModel", "Error during logout: ${e.message}")
                }
            }
        }
    }




