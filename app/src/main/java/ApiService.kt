package com.example.mobileapp.network

import com.example.mobileapp.login.LoginResponse
import com.example.mobileapp.network.com.example.mobileapp.login.ApiResponse
import com.example.mobileapp.network.com.example.mobileapp.login.RegisterRequest
import com.example.mobileapp.network.com.example.mobileapp.profile.UserProfileData
import com.example.mobileapp.network.com.example.mobileapp.profile.UserProfileResponse
import com.example.mobileapp.network.com.example.mobileapp.yukangkut.YukAngkutRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiService {
    @POST("api/auth/login")
    fun login(@Body credentials: Map<String, String>): Call<LoginResponse>

    @POST("api/auth/register")
    fun registerUser(@Body request: RegisterRequest): Call<ApiResponse>

    @POST("api/yuk_angkut")
    suspend fun createYukAngkut(
        @Header("Authorization") token: String,
        @Body request: YukAngkutRequest
    ): ApiResponse

    @PUT("api/user/profile")
    suspend fun updateUserProfile(
        @Header("Authorization") token: String, // Header untuk token
        @Body userProfile: UserProfileData // Data body untuk profile
    ): Response<ApiResponse>

    @GET("api/user/profile")
    fun getUserProfile(
        @Header("Authorization") token: String
    ): Call<UserProfileResponse>



}


