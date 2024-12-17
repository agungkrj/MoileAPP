package com.example.mobileapp.order

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobileapp.R

class RiwayatYukAngkutBerhasilActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                RiwayatYukAngkutBerhasilScreen()
            }
        }
    }
}

@Composable
fun RiwayatYukAngkutBerhasilScreen() {
    Scaffold(
        topBar = { TopBarHeader("Detail Order") }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .background(Color(0xFFF7F7F7))
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // Status Progress Bar
            StatusProgressBarAngkut()

            Spacer(modifier = Modifier.height(8.dp))

            // Informasi Transaksi
            Text(
                text = "Transaksi Yuk Angkut! Kamu telah berhasil.",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Informasi Detail Transaksi
            Column(modifier = Modifier.padding(horizontal = 12.dp)) {
                RiwayatYukAngkutCard(
                    title = "Informasi Pengirim",
                    info = listOf("No.Ponsel:   081234567810", "Alamat      :   Jln. Melati Blok C3 No. 28 Batam")
                )

                RiwayatYukAngkutCard(
                    title = "Informasi Penjemputan",
                    info = listOf("Tanggal: 19 April 2024", "Waktu   :  09:40 WIB")
                )

                RiwayatYukAngkutCard(
                    title = "Informasi Pembayaran",
                    info = listOf(
                        "Kertas 1 kg  Rp 1.500 s.d Rp 2.000",
                        "Estimasi Harga: Rp 1.500 s.d Rp 2.000",
                        "Biaya Layanan: Rp 150 s.d Rp 200",
                        "Estimasi Penerimaan: Rp 1.350 s.d Rp 1.800"
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                TransactionResultMessage(
                    message = "Hasil transaksi Yuk Angkut! Kamu telah diberikan dalam bentuk kuy point."
                )
            }
        }
    }
}

@Composable
fun RiwayatYukAngkutCard(title: String, info: List<String>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFD3D3D3), shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                    .padding(16.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                info.forEach {
                    Text(
                        text = it,
                        fontSize = 14.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun StatusProgressBarAngkut() {
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
            YukBuangStatusItem("Sedang Dijemput", Color(0xFF4CAF50), R.drawable.proses)
            DottedLinetest()
            YukBuangStatusItem("Barang Diangkut", Color(0xFF4CAF50), R.drawable.terima)
            DottedLinetest()
            YukBuangStatusItem("Transaksi Berhasil", Color(0xFF4CAF50), R.drawable.berhasil)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRiwayatYukAngkutBerhasilScreen() {
    RiwayatYukAngkutBerhasilScreen()
}
