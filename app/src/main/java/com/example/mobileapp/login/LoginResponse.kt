package com.example.mobileapp.login

data class LoginResponse(
    val token: String,
    val user: User
)

data class User(
    val iponsed: Int,
    val name: String,
    val email: String,
    val number: String,
    val address: String,
    val gender: String,
    val role: String
)
