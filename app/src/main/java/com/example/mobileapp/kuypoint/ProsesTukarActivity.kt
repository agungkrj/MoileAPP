package com.example.mobileapp.kuypoint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
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
import com.example.mobileapp.ui.theme.MobileAPPTheme

class ProsesTukarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileAPPTheme {
                ProsesTukarScreen()
            }
        }
    }
}

@Composable
fun ProsesTukarScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Close Button
        IconButton(
            onClick = { /* Handle close action */ },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.keluar), // Ganti dengan ikon close
                contentDescription = "Close",
                tint = Color.Black
            )
        }

        // Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Title
            Text(
                text = "Penukaran\nKuy Point Diproses!",
                fontSize = 24.sp, // Ukuran font judul
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp) // Jarak antara judul dan ilustrasi
            )

            // Illustration
            Image(
                painter = painterResource(id = R.drawable.tukarkoint), // Ganti dengan ilustrasi
                contentDescription = "Coins Illustration",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(300.dp) // Ukuran lebih besar agar sesuai
                    .padding(bottom = 24.dp) // Jarak antara ilustrasi dan teks subtitle
            )

            // Subtitle
            Text(
                text = "Harap tunggu, proses transaksi sedang berlangsung pada halaman order",
                fontSize = 16.sp, // Ukuran font subtitle
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProsesTukarScreen() {
    MobileAPPTheme {
        ProsesTukarScreen()
    }
}
