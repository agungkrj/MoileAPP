// File: TukarOvoActivity.kt
package com.example.mobileapp

import android.content.Intent
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobileapp.ui.theme.MobileAPPTheme
import androidx.compose.ui.geometry.Offset

class TukarOvoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileAPPTheme {
                TukarOvoScreen()
            }
        }
    }
}

@Composable
fun TukarOvoScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header
        HeaderTukarOvoPoint()

        Spacer(modifier = Modifier.height(16.dp))

        // Header Text
        Text(
            text = "Tukar Kuy Point dengan saldo OVO",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Text(
            text = "Sekarang kamu bisa menukarkan KuyPoint yang kamu miliki dengan saldo OVO! Ayo lakukan lebih banyak Yuk Angkut! dan Yuk Buang!",
            fontSize = 15.sp,
            color = Color.Gray,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Input for OVO number
        Text(
            text = "Masukkan Nomor OVO Kamu",
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp))
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "081234567810",
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )
            Icon(
                painter = painterResource(id = R.drawable.pensil), // replace with your edit icon
                contentDescription = "Edit",
                modifier = Modifier
                    .size(20.dp)
                    .clickable { /* Handle edit click */ }
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
                NominalOvoButton("Rp 20.000", Modifier.weight(1f))
                NominalOvoButton("Rp 30.000", Modifier.weight(1f))
                NominalOvoButton("Rp 50.000", Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                NominalOvoButton("Rp 70.000", Modifier.weight(1f))
                NominalOvoButton("Rp 80.000", Modifier.weight(1f))
                NominalOvoButton("Rp 100.000", Modifier.weight(1f))
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
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Minimal top up Rp 15.000",
                    fontSize = 12.sp,
                    color = Color.Black
                )
            }
        }
        Spacer(modifier = Modifier.height(60.dp))

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
fun HeaderTukarOvoPoint() {
    val context = LocalContext.current // Mendapatkan context

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
                .clickable {
                    val intent = Intent(context, TukarKuyPointActivity::class.java)
                    context.startActivity(intent)
                },
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
fun NominalOvoButton(text: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp))
            .clickable { /* Handle nominal selection */ }
            .padding(vertical = 8.dp)
    ) {
        Text(text = text, fontSize = 14.sp, color = Color.Black)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTukarOvoScreen() {
    MobileAPPTheme {
        TukarOvoScreen()
    }
}
