package com.example.mobileapp.profile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobileapp.R

class EwalletActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EWalletScreen()
        }
    }
}

@Composable
fun EWalletScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE1F5E9)) // Background hijau muda
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Header
        Text(
            text = "E - Wallet",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        // User Avatar dan Username
        Box(
            modifier = Modifier
                .size(90.dp)
                .background(Color(0xFFD9C2E9), shape = CircleShape), // Warna background avatar
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.profile), // Ganti dengan drawable avatar
                contentDescription = "User Avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(90.dp)
            )
        }

        Text(
            text = "Username",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Daftar E-Wallet
        EWalletItem("DANA", R.drawable.logodana)
        Spacer(modifier = Modifier.height(16.dp))
        EWalletItem("ShopeePay", R.drawable.logoshopie)
        Spacer(modifier = Modifier.height(16.dp))
        EWalletItem("OVO", R.drawable.logoovo)
    }
}

@Composable
fun EWalletItem(walletName: String, walletIconRes: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Ikon e-wallet
        Image(
            painter = painterResource(walletIconRes),
            contentDescription = "$walletName Icon",
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = walletName,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )
        Button(
            onClick = { /* TODO: Tambahkan aksi untuk menghubungkan akun */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)), // Hijau untuk tombol
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Hubungkan Akun", color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEWalletScreen() {
    EWalletScreen()
}
