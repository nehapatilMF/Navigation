package com.example.navigation.model

data class LogoutResponse (
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: User
)