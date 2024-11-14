// File: TukarBank.kt
package com.example.mobileapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobileapp.ui.theme.MobileAPPTheme

class TukarBankActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileAPPTheme {
                TukarBankScreen()
            }
        }
    }
}

@Composable
fun TukarBankScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header
        ExchangeHeader()

        Spacer(modifier = Modifier.height(16.dp))

        // Header Text
        Text(
            text = "Tukar Kuy Point ke saldo Bank",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Dropdown Nama Bank
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 4.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.iconbank), // Pastikan nama sesuai dengan file di drawable
                    contentDescription = "Bank Icon",
                    tint = Color.Black,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Nama Bank",
                    fontSize = 12.sp,
                    color = Color.Black
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp))
                    .padding(horizontal = 12.dp, vertical = 12.dp)
                    .clickable { /* Handle dropdown click */ }
            ) {
                Text(
                    text = "Bank BRI",
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    painter = painterResource(id = R.drawable.drobdown), // Ganti dengan ikon dropdown Anda
                    contentDescription = "Dropdown Icon",
                    tint = Color.Black,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Input Nomor Rekening
        Text(
            text = "Masukkan Nomor Rekening Kamu",
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp))
                .height(50.dp)
                .padding(horizontal = 12.dp, vertical = 8.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "081234567810",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Nominal options
        Text(
            text = "Nominal",
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 16.dp, end = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AmountButton("Rp 20.000", Modifier.weight(1f))
                AmountButton("Rp 30.000", Modifier.weight(1f))
                AmountButton("Rp 50.000", Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AmountButton("Rp 70.000", Modifier.weight(1f))
                AmountButton("Rp 80.000", Modifier.weight(1f))
                AmountButton("Rp 100.000", Modifier.weight(1f))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Custom amount input
        Text(
            text = "Masukkan nominal",
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Box(
            modifier = Modifier
                .width(370.dp) // Lebar sesuai dimensi pada gambar
                .height(71.dp) // Tinggi sesuai dimensi pada gambar
                .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp))
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Rp",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Minimal top up Rp 15.000",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(100.dp))

        // Exchange button
        Button(
            onClick = { /* Handle exchange click */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Tukar Sekarang", fontSize = 16.sp, color = Color.White)
        }
    }
}

@Composable
fun ExchangeHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF79D7C7), // Warna pertama pada posisi 0%
                        Color(0xFF52AC9D)  // Warna kedua pada posisi 71%
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(0f, 710f) // Mengatur titik akhir gradasi
                )
            )
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        // Ikon Kembali
        Icon(
            painter = painterResource(id = R.drawable.keluar), // Ganti dengan ikon back Anda
            contentDescription = "Back",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .size(24.dp)
                .clickable { /* Handle back click */ },
            tint = Color.Black
        )

        // Teks Judul di Tengah
        Text(
            text = "Tukar Kuy Point",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun AmountButton(text: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp))
            .clickable { /* Handle amount selection */ }
            .padding(vertical = 8.dp)
    ) {
        Text(text = text, fontSize = 14.sp, color = Color.Black)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTukarBankScreen() {
    MobileAPPTheme {
        TukarBankScreen()
    }
}
