package com.example.mobileapp.network.com.example.mobileapp.yukangkut

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileapp.network.RetrofitInstance
import kotlinx.coroutines.launch

class YukAngkutViewModel : ViewModel() {
    fun createYukAngkut(
        token: String,
        request: YukAngkutRequest,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.createYukAngkut("Bearer $token", request)
                onSuccess(response.message)
            } catch (e: Exception) {
                onError(e.message ?: "Gagal menghubungkan ke server")
            }
        }
    }
}
