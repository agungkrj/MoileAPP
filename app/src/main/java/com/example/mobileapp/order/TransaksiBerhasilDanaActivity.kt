package com.example.mobileapp.order

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.example.mobileapp.R
import com.example.mobileapp.ui.theme.MobileAPPTheme

class TransaksiBerhasilDanaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MobileAPPTheme {
                TransaksiBerhasilScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransaksiBerhasilHeader() {
    val context = LocalContext.current
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Detail Order",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = {
                (context as? ComponentActivity)?.finish()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.keluar),
                    contentDescription = "Back",
                    tint = Color.Black,
                    modifier = Modifier.size(28.dp)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF009688)) // Hijau sesuai gambar
    )
}

@Composable
fun TransaksiBerhasilScreen() {
    Scaffold(
        topBar = { TransaksiBerhasilHeader() },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.time_ismoney), // Gambar ikon sukses
                    contentDescription = "Ilustrasi Berhasil",
                    modifier = Modifier
                        .fillMaxWidth() // Menjaga gambar memenuhi lebar layar
                        .height(250.dp) // Tinggi gambar disesuaikan
                        .padding(top = 16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Judul
                Text(
                    text = "Tukar Kuy Point ke saldo Dana",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Pesan Detail
                Text(
                    text = "Kami sedang memproses penukaran Kuy Point Kamu. " +
                            "Silakan menunggu maksimal 1x24 jam untuk proses selanjutnya",
                    fontSize = 14.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewTransaksiBerhasilScreen() {
    MobileAPPTheme {
        TransaksiBerhasilScreen()
    }
}