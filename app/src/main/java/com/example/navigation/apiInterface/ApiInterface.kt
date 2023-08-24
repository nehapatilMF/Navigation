package com.example.navigation.apiInterface

import com.example.navigation.model.LoginResponse
import com.example.navigation.model.LogoutResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {
    @FormUrlEncoded
    @POST("mobile_test/login.php")
   suspend fun login(
        @Field("email") username: String,
        @Field("password") password: String
    ): Response<LoginResponse>

    @POST("mobile_test/logout.php?")
    fun logout():Response<LogoutResponse>

}
