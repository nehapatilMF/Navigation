package com.example.navigation.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigation.Client.RetrofitClient
import com.example.navigation.apiInterface.ApiInterface
import com.example.navigation.model.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private val apiInterface = RetrofitClient.getInstance()?.create(ApiInterface::class.java)!!

    private val loginResponseLiveData: MutableLiveData<LoginResponse?> = MutableLiveData()
    val loginErrorLiveData: MutableLiveData<String> = MutableLiveData()

    val loginSuccessLiveData: MutableLiveData<Boolean> = MutableLiveData()


    @SuppressLint("SuspiciousIndentation")
    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response: Response<LoginResponse> = apiInterface.login(email, password)

                if (response.isSuccessful && response.body() != null) {
                    val apiResponse = response.body()!!
                    loginResponseLiveData.value = apiResponse
                    loginSuccessLiveData.value = apiResponse.message == "Login Successfully"
                } else {

                    loginErrorLiveData.value = "Login failed"
                }

            } catch (t: Throwable) {
                withContext(Dispatchers.Main) {
                    loginErrorLiveData.value = t.message ?: "Login request failed"
                }
            }
        }
    }
}








