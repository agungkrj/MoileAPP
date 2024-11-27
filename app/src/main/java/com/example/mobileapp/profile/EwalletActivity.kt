package com.example.mobileapp.profile

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
            EWalletScreen(
                onDanaClick = {
                    val intent = Intent(this, EwalletDanaActivity::class.java)
                    startActivity(intent)
                },
                onShopeePayClick = {
                    val intent = Intent(this, EwalletShopeeActivity::class.java)
                    startActivity(intent)
                },
                onOvoClick = {
                    val intent = Intent(this, EwalletOvoActivity::class.java)
                    startActivity(intent)
                },
                onBackClick = {
                    // Kembali ke ProfileActivity
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    finish() // Menutup EwalletActivity agar tidak ada tumpukan di back stack
                }
            )
        }
    }
}

@Composable
fun EWalletScreen(
    onDanaClick: () -> Unit,
    onShopeePayClick: () -> Unit,
    onOvoClick: () -> Unit,
    onBackClick: () -> Unit // Tambahkan parameter untuk aksi tombol kembali
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE1F5E9)) // Background hijau muda
    ) {
        // Custom Topbar dengan aksi kembali
        CustomTopBar(onBackClick = onBackClick)

        Spacer(modifier = Modifier.height(16.dp))

        // Avatar dan username
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Daftar E-Wallet
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            EWalletItem("DANA", R.drawable.logodana, onDanaClick)
            Spacer(modifier = Modifier.height(16.dp))
            EWalletItem("ShopeePay", R.drawable.logoshopie, onShopeePayClick)
            Spacer(modifier = Modifier.height(16.dp))
            EWalletItem("OVO", R.drawable.logoovo, onOvoClick)
        }
    }
}

@Composable
fun CustomTopBar(onBackClick: () -> Unit) { // Tambahkan parameter untuk navigasi
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF99E7D1)) // Warna hijau muda dari gambar
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Tombol kembali (ikon panah)
        Image(
            painter = painterResource(R.drawable.panah), // Ikon panah
            contentDescription = "Back",
            modifier = Modifier
                .size(24.dp)
                .clickable { onBackClick() } // Memanggil aksi kembali
        )
        Spacer(modifier = Modifier.weight(1f))

        // Teks E-Wallet
        Text(
            text = "E - Wallet",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun EWalletItem(walletName: String, walletIconRes: Int, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp),
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
            onClick = onClick, // Aksi saat tombol diklik
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
    EWalletScreen(
        onDanaClick = {},
        onShopeePayClick = {},
        onOvoClick = {},
        onBackClick = {} // Tambahkan preview aksi tombol kembali
    )
}
