package com.example.mobileapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.example.mobileapp.ui.theme.MobileAPPTheme
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.platform.LocalContext

class BatalOrderYukBuangActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileAPPTheme {
                BatalOrderYukBuangScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BatalOrderYukBuangHeader() {
    val context = LocalContext.current
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    "Detail Order",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = {
                val intent = Intent(context, OrderActivity::class.java)
                context.startActivity(intent)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.keluar),
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF55B3A4))
    )
}

@Composable
fun BatalOrderYukBuangScreen() {
    Scaffold(
        topBar = { BatalOrderYukBuangHeader() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            BatalOrderYukBuangStatusSection()

            Spacer(modifier = Modifier.height(8.dp))

            // Teks di luar Card status
            Text(
                text = "Transaksi Yuk Buang! Kamu telah gagal.",
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            BatalOrderYukBuangDetailSection()

            Spacer(modifier = Modifier.height(16.dp))

            BatalOrderYukBuangCancelledMessage()
        }
    }
}

@Composable
fun BatalOrderYukBuangStatusSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F7F7))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            StatusItem(
                title = "Sedang Diproses",
                color = Color(0xFF4CAF50),
                iconId = R.drawable.proses
            )
            DottedLine()
            StatusItem(
                title = "Telah Dibatalkan",
                color = Color.Red,
                iconId = R.drawable.cancel
            )
        }
    }
}

@Composable
fun BatalOrderYukBuangDetailSection() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        OrderInfoCard(title = "Informasi Tempat Tinggal", info = listOf(
            "No. Ponsel : 081234567810",
            "Alamat : Jln. Melati Blok C3 No. 28, Batam"
        ))

        Spacer(modifier = Modifier.height(8.dp))

        OrderInfoCard(title = "Informasi Pengantaran", info = listOf(
            "Tanggal : 09 Maret 2024",
            "Waktu : 10.40 WIB",
            "Alamat : Simpang Polsek Kecamatan Nongsa, Batam"
        ))

        Spacer(modifier = Modifier.height(8.dp))

        OrderInfoCard(title = "Informasi Penjualan", info = listOf(
            "Kertas 1 kg Rp 1500 s.d Rp 2000",
            "Estimasi harga  Rp 1500 s.d Rp 2000",
            "Biaya Layanan Rp 150 s.d Rp 200",
            "Estimasi Penerimaan Rp1350 s.d Rp1800"
        ))
    }
}

@Composable
fun BatalOrderYukBuangCancelledMessage() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Transaksi Yuk Buang! Kamu telah dibatalkan",
            fontSize = 14.sp,
            color = Color(0xFF55B3A4),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBatalOrderYukBuangScreen() {
    MobileAPPTheme {
        BatalOrderYukBuangScreen()
    }
}
