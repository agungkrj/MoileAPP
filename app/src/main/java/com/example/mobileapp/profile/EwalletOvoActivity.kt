package com.example.mobileapp.profile

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobileapp.R
import androidx.compose.ui.platform.LocalContext

class EwalletOvoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EWalletOvoScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EWalletOvoScreen() {
    val context = LocalContext.current
    var phoneNumber by remember { mutableStateOf("") } // State untuk nomor HP

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)) // Background putih terang
    ) {
        // Header dengan warna hijau
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF4CAF50)) // Warna hijau
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Tombol kembali
            Image(
                painter = painterResource(R.drawable.panah), // Ikon panah
                contentDescription = "Back",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        // Navigasi ke EwalletActivity saat ikon panah ditekan
                        val intent = Intent(context, EwalletActivity::class.java)
                        context.startActivity(intent)
                    }
            )
            Spacer(modifier = Modifier.weight(1f))

            // Teks E-Wallet
            Text(
                text = "E - Wallet",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(5f)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Konten utama
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo OVO
            Image(
                painter = painterResource(R.drawable.logoovo),
                contentDescription = "Logo OVO",
                modifier = Modifier.size(80.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Teks Hubungkan Akun
            Text(
                text = "Hubungkan Akun",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4CAF50) // Warna hijau
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Deskripsi
            Text(
                text = "Masukkan Nomor HP anda untuk \n" +
                        "menghubungkan akun OVO anda",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Input Nomor HP dengan border bulat
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it }, // Memperbarui state saat teks berubah
                placeholder = {
                    Text(
                        text = "Masukkan Nomor HP",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(50.dp)), // Desain bulat
                shape = RoundedCornerShape(50.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.White,
                    focusedBorderColor = Color(0xFF4CAF50), // Warna border saat fokus
                    unfocusedBorderColor = Color(0xFFBDBDBD), // Warna border saat tidak fokus
                    focusedTextColor = Color.Black, // Warna teks saat fokus
                    unfocusedTextColor = Color.Black // Warna teks saat tidak fokus
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Tombol Konfirmasi
            Button(
                onClick = { /* TODO: Tambahkan aksi konfirmasi */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)), // Warna hijau
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(
                    text = "Konfirmasi",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEWalletOvoScreen() {
    EWalletOvoScreen()
}
