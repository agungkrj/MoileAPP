package com.example.mobileapp.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobileapp.beranda.DashboardActivity
import com.example.mobileapp.network.RetrofitInstance
import com.example.mobileapp.ui.theme.MobileAPPTheme
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MasukActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileAPPTheme {
                MasukScreen()
            }
        }
    }
}

@Composable
fun MasukScreen() {
    val context = LocalContext.current
    var isVisible by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        delay(100) // Delay untuk animasi
        isVisible = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB2DFDB)),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        // Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Selamat Datang!",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF004D40),
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Masukkan email dan password yang telah kamu daftarkan sebelumnya!",
                fontSize = 14.sp,
                color = Color.Black,
                textAlign = TextAlign.Start
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Form Login
        AnimatedVisibility(
            visible = isVisible,
            enter = slideInVertically(initialOffsetY = { it })
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color.White, shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .padding(horizontal = 24.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    shape = RoundedCornerShape(16.dp),
                    singleLine = true
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    shape = RoundedCornerShape(16.dp),
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        isLoading = true
                        performLogin(context, email, password) { success, message ->
                            isLoading = false
                            if (success) {
                                val intent = Intent(context, DashboardActivity::class.java)
                                context.startActivity(intent)
                            } else {
                                errorMessage = message
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(16.dp),
                    enabled = !isLoading,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF55B3A4),
                        contentColor = Color.White
                    )
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        Text(text = "Masuk", fontSize = 16.sp)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (errorMessage != null) {
                    Text(
                        text = errorMessage!!,
                        color = Color.Red,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

                val annotatedText = buildAnnotatedString {
                    append("Belum punya akun? ")

                    pushStringAnnotation(tag = "register", annotation = "register")
                    withStyle(
                        style = SpanStyle(
                            color = Color(0xFF55B3A4),
                            textDecoration = TextDecoration.Underline
                        )
                    ) {
                        append("Daftar di sini")
                    }
                    pop()
                }

                ClickableText(
                    text = annotatedText,
                    style = TextStyle(fontSize = 14.sp, color = Color.Gray),
                    modifier = Modifier.padding(vertical = 8.dp),
                    onClick = { offset ->
                        annotatedText.getStringAnnotations(tag = "register", start = offset, end = offset)
                            .firstOrNull()?.let {
                                val intent = Intent(context, DaftarActivity::class.java)
                                context.startActivity(intent)
                            }
                    }
                )
            }
        }
    }
}

fun performLogin(context: Context, email: String, password: String, onResult: (Boolean, String?) -> Unit) {
    val credentials = mapOf("email" to email, "password" to password)
    RetrofitInstance.api.login(credentials).enqueue(object : Callback<LoginResponse> {
        override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
            if (response.isSuccessful && response.body() != null) {
                val loginResponse = response.body()
                saveAuthToken(context, loginResponse!!.token) // Simpan token
                onResult(true, null) // Login berhasil
            } else {
                onResult(false, "Email atau password salah")
            }
        }

        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
            t.printStackTrace()
            onResult(false, "Gagal terhubung ke server. Coba lagi.")
        }
    })
}

fun saveAuthToken(context: Context, token: String) {
    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString("auth_token", token)
    editor.apply()
}

@Preview(showBackground = true)
@Composable
fun PreviewMasukScreen() {
    MobileAPPTheme {
        MasukScreen()
    }
}
