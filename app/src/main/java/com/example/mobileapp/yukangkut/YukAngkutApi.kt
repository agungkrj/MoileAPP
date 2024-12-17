package com.example.mobileapp.network.com.example.mobileapp.yukangkut

data class YukAngkutRequest(
    val name: String,
    val location: String,
    val date: String,
    val time: String,
    val type: String,
    val amount: Int,
    val photo: String,
    val status: String,
    val transaction_type: String,
    val price_per_kg: Int,
    val email: String
)

data class ApiResponse(
    val message: String,
    val pickup_id: String? = null
)


