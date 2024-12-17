package com.example.mobileapp.order

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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

class RiwayatDanaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Menyediakan fungsi kembali
            RiwayatDanaScreen(onBackPressed = { onBackPressedDispatcher.onBackPressed() })
        }
    }
}

@Composable
fun RiwayatDanaScreen(onBackPressed: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE0F2F1)), // Latar belakang hijau muda
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Bar dengan warna hijau
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF55B3A4)) // Warna hijau
                .padding(16.dp),
            contentAlignment = Alignment.Center // Konten berada di tengah
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Tombol kembali
                Image(
                    painter = painterResource(R.drawable.keluar),
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onBackPressed() } // Tambahkan aksi kembali
                )
                Spacer(modifier = Modifier.weight(1f)) // Spacer agar teks ke tengah
            }

            // Header Riwayat Dana
            Text(
                text = "Detail Order", // Judul Header
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black // Warna teks hitam
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Icon Centang
        Image(
            painter = painterResource(id = R.drawable.cek), // Gambar centang
            contentDescription = "Centang",
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Status Transaksi
        Text(
            text = "Transaksi Berhasil",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF55B3A4)
        )

        Text(
            text = "Tukar Kuy Point ke saldo Bank\nBerikut adalah bukti transfer:",
            fontSize = 14.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Gambar Bukti Transfer
        Image(
            painter = painterResource(id = R.drawable.riwayatdana), // Gambar bukti transfer
            contentDescription = "Bukti Transfer",
            modifier = Modifier
                .size(600.dp, 780.dp) // Lebar 600dp, tinggi 780dp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRiwayatDanaScreen() {
    RiwayatDanaScreen(onBackPressed = {})
}
