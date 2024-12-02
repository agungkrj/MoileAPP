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

class EwalletDanaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EWalletDanaScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EWalletDanaScreen() {
    val context = LocalContext.current
    var phoneNumber by remember { mutableStateOf("") } // State untuk input nomor HP

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // Top Bar dengan warna hijau
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF4CAF50)) // Warna hijau
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.panah),
                contentDescription = "Back",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        val intent = Intent(context, EwalletActivity::class.java)
                        context.startActivity(intent)
                    }
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "E - Wallet",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White, // Warna teks putih
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(5f)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.logodana),
                contentDescription = "Logo DANA",
                modifier = Modifier.size(80.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Hubungkan Akun",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4CAF50)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Masukkan Nomor HP anda untuk \n" +
                        "menghubungkan akun DANA anda",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Input Nomor HP dengan border dan lekukan
            OutlinedTextField(
                value = phoneNumber, // Mendukung input pengguna
                onValueChange = { phoneNumber = it },
                placeholder = {
                    Text(
                        text = "Masukkan Nomor HP",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(50.dp)), // Lekukan bulat
                shape = RoundedCornerShape(50.dp), // Membuat sudut lebih bulat
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.White,
                    focusedBorderColor = Color(0xFF4CAF50), // Warna border saat fokus
                    unfocusedBorderColor = Color(0xFFBDBDBD), // Warna border saat tidak fokus
                    focusedTextColor = Color.Black, // Warna teks saat fokus
                    unfocusedTextColor = Color.Black // Warna teks saat tidak fokus
                )
            )


            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* TODO: Tambahkan aksi konfirmasi */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
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
fun PreviewEWalletDanaScreen() {
    EWalletDanaScreen()
}
