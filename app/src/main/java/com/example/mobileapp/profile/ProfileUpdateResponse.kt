package com.example.mobileapp.network.com.example.mobileapp.profile

data class UserProfileData(
    val name: String,
    val email: String,
    val phone: String,
    val gender: String,
    val birthDate: String,
    val address: String
)

data class ProfileUpdateResponse(
    val success: Boolean,
    val message: String
)
