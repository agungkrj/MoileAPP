package com.example.mobileapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobileapp.ui.theme.MobileAPPTheme
import kotlinx.coroutines.delay

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
    var email by remember { mutableStateOf("") } // State untuk menyimpan input email
    var password by remember { mutableStateOf("") } // State untuk menyimpan input password

    // Menunda animasi agar dimulai setelah komponen selesai diload
    LaunchedEffect(Unit) {
        delay(500) // Delay 500 ms sebelum animasi mulai
        isVisible = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB2DFDB)), // Latar belakang hijau lembut di bagian atas
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(60.dp)) // Spacer untuk memberi jarak dari atas

        // Teks "Selamat Datang!" di bagian atas
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.Start // Rata kiri untuk teks
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
                color = Color.Gray,
                textAlign = TextAlign.Start
            )
        }

        Spacer(modifier = Modifier.height(40.dp)) // Spacer untuk memberi jarak antara teks dan bagian putih

        // Bagian Putih dengan Animasi Muncul dari Bawah
        AnimatedVisibility(
            visible = isVisible,
            enter = slideInVertically(initialOffsetY = { it }) // Muncul dari bawah layar
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color.White, shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)) // Bagian putih di bawah dengan sudut atas melengkung
                    .padding(horizontal = 24.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Email TextField
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

                // Password TextField
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    shape = RoundedCornerShape(16.dp),
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation() // Menyembunyikan teks password
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Lupa Password
                Text(
                    text = "Lupa Password?",
                    fontSize = 14.sp,
                    color = Color(0xFF57B5A8),
                    modifier = Modifier.align(Alignment.End)
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Masuk Button
                Button(
                    onClick = {
                        val intent = Intent(context, DashboardActivity::class.java)
                        context.startActivity(intent)
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
                    Text(text = "Masuk", fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Register Text dengan teks "Daftar di sini" yang bisa diklik
                val annotatedText = buildAnnotatedString {
                    append("Belum punya akun? ")

                    // Bagian "Daftar di sini" diberi warna hijau dan bisa diklik
                    pushStringAnnotation(tag = "register", annotation = "register")
                    withStyle(
                        style = SpanStyle(
                            color = Color(0xFF55B3A4), // Warna hijau
                            textDecoration = TextDecoration.Underline // Menambahkan garis bawah
                        )
                    ) {
                        append("Daftar di sini")
                    }
                    pop()
                }

                ClickableText(
                    text = annotatedText,
                    style = LocalTextStyle.current.copy(fontSize = 14.sp, color = Color.Gray),
                    modifier = Modifier.padding(vertical = 8.dp),
                    onClick = { offset ->
                        annotatedText.getStringAnnotations(tag = "register", start = offset, end = offset)
                            .firstOrNull()?.let {
                                // Tindakan saat "Daftar di sini" diklik
                                val intent = Intent(context, DaftarActivity::class.java)
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
fun PreviewMasukScreen() {
    MobileAPPTheme {
        MasukScreen()
    }
}
