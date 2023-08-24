package com.example.navigation.model

data class LoginResponse(
    var status: Int,
    var success : Boolean,
    var message: String,
    var data : User
)