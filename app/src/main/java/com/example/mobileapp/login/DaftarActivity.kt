package com.example.mobileapp.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobileapp.network.RetrofitInstance
import com.example.mobileapp.network.com.example.mobileapp.login.ApiResponse
import com.example.mobileapp.network.com.example.mobileapp.login.RegisterRequest
import com.example.mobileapp.ui.theme.MobileAPPTheme
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DaftarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileAPPTheme {
                DaftarScreen()
            }
        }
    }
}

@Composable
fun DaftarScreen() {
    val context = LocalContext.current
    var isVisible by remember { mutableStateOf(false) }
    var namaLengkap by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    // Animasi muncul dari bawah
    LaunchedEffect(Unit) {
        delay(100)
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

        // Judul dan Deskripsi
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
                text = "Silahkan daftar untuk dapat menggunakan seluruh fitur aplikasi kami.",
                fontSize = 14.sp,
                color = Color.Black,
                textAlign = TextAlign.Start
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Form Pendaftaran dengan animasi
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
                // Input Nama Lengkap
                OutlinedTextField(
                    value = namaLengkap,
                    onValueChange = { namaLengkap = it },
                    label = { Text("Nama Lengkap") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    shape = RoundedCornerShape(16.dp),
                    singleLine = true
                )

                // Input Email
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

                // Input Password
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = "Toggle Password Visibility"
                            )
                        }
                    },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    shape = RoundedCornerShape(16.dp),
                    singleLine = true
                )

                // Input Konfirmasi Password
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Konfirmasi Password") },
                    trailingIcon = {
                        IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                            Icon(
                                imageVector = if (confirmPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = "Toggle Password Visibility"
                            )
                        }
                    },
                    visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    shape = RoundedCornerShape(16.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Tombol Daftar
                Button(
                    onClick = {
                        if (namaLengkap.isBlank() || email.isBlank() || password.isBlank()) {
                            Toast.makeText(context, "Semua field harus diisi!", Toast.LENGTH_SHORT).show()
                        } else if (password == confirmPassword) {
                            val request = RegisterRequest(
                                name = namaLengkap,
                                email = email,
                                password = password,
                                confirmPassword = confirmPassword
                            )

                            val apiService = RetrofitInstance.api
                            apiService.registerUser(request).enqueue(object : Callback<ApiResponse> {
                                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                                    if (response.isSuccessful) {
                                        Toast.makeText(context, "Registrasi berhasil: ${response.body()?.message}", Toast.LENGTH_SHORT).show()
                                        // Navigasi ke halaman login
                                        context.startActivity(Intent(context, MasukActivity::class.java))
                                    } else {
                                        Toast.makeText(context, "Registrasi gagal: ${response.errorBody()?.string()}", Toast.LENGTH_SHORT).show()
                                    }
                                }

                                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                                    Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                                }
                            })
                        } else {
                            Toast.makeText(context, "Password dan Konfirmasi Password tidak cocok", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF55B3A4),
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Daftar", fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Teks "Masuk" dengan Klik
                val annotatedText = buildAnnotatedString {
                    append("Sudah punya akun? ")
                    pushStringAnnotation(tag = "login", annotation = "login")
                    withStyle(
                        style = SpanStyle(
                            color = Color(0xFF55B3A4),
                            textDecoration = TextDecoration.Underline
                        )
                    ) {
                        append("Masuk di sini")
                    }
                    pop()
                }

                ClickableText(
                    text = annotatedText,
                    style = LocalTextStyle.current.copy(fontSize = 14.sp, color = Color.Gray),
                    onClick = { offset ->
                        annotatedText.getStringAnnotations(tag = "login", start = offset, end = offset)
                            .firstOrNull()?.let {
                                val intent = Intent(context, MasukActivity::class.java)
                                context.startActivity(intent)
                            }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDaftarScreen() {
    MobileAPPTheme {
        DaftarScreen()
    }
}
