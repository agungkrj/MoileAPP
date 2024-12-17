package com.example.mobileapp.network.com.example.mobileapp.login

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val confirmPassword: String
)

data class ApiResponse(
    val status: String,
    val message: String
)
