package com.example.mobileapp.network.com.example.mobileapp.profile

data class UserProfileResponse(
    val user: UserData
)

data class UserData(
    val name: String,
    val email: String,
    val phone: String,
    val gender: String,
    val birthDate: String,
    val address: String
)
