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
import androidx.compose.ui.platform.LocalContext


class RiwayatYukBuangBerhasilActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                RiwayatYukBuangBerhasilScreen()
            }
        }
    }
}

@Composable
fun RiwayatYukBuangBerhasilScreen() {
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
            StatusProgressBar()

            Spacer(modifier = Modifier.height(8.dp))

            // Informasi Transaksi
            Text(
                text = "Transaksi Yuk Buang! Kamu telah berhasil.",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Informasi Detail menggunakan RiwayatYukBuangCard dengan padding horizontal kecil
            Column(modifier = Modifier.padding(horizontal = 12.dp)) {
                RiwayatYukBuangCard(
                    title = "Informasi Tempat Tinggal",
                    info = listOf("No. Ponsel: 081234567810", "Alamat: Jln. Melati Blok C3 No. 28, Batam")
                )

                RiwayatYukBuangCard(
                    title = "Informasi Pengantaran",
                    info = listOf("Tanggal: 17 Maret 2024", "Waktu: 13:40 WIB", "Alamat: Simpang Polsek Kecamatan Nongsa, Batam")
                )

                RiwayatYukBuangCard(
                    title = "Informasi Penjualan",
                    info = listOf(
                        "Kertas 1 kg: Rp 1.500 s.d Rp 2.000",
                        "Estimasi Harga: Rp 1.500 s.d Rp 2.000",
                        "Biaya Layanan: Rp 150 s.d Rp 200",
                        "Estimasi Penerimaan: Rp 1.350 s.d Rp 1.800"
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Tambahkan informasi tambahan (Hasil Transaksi)
                TransactionResultMessage(
                    message = "Hasil transaksi Yuk Buang! Kamu telah diberikan dalam bentuk kuy point."
                )
            }
        }
    }
}

@Composable
fun TransactionResultMessage(message: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .background(Color(0xFFC3EAE3), shape = RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {
        Text(
            text = message,
            fontSize = 14.sp,
            color = Color.Black,
            textAlign = TextAlign.Start
        )
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarHeader(title: String) {
    val activity = (LocalContext.current as? ComponentActivity) // Ambil referensi ke Activity saat ini

    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = {
                activity?.finish() // Menutup halaman saat ini dan kembali ke halaman sebelumnya
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.keluar),
                    contentDescription = "Back",
                    tint = Color.Black,
                    modifier = Modifier.size(28.dp)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF55B3A4))
    )
}



@Composable
fun StatusProgressBar() {
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
            YukBuangStatusItem("Sedang Diproses", Color(0xFF4CAF50), R.drawable.proses)
            DottedLinetest()
            YukBuangStatusItem("Telah  Diterima", Color(0xFF4CAF50), R.drawable.terima)
            DottedLinetest()
            YukBuangStatusItem("Transaksi Berhasil", Color(0xFF4CAF50), R.drawable.berhasil)
        }
    }
}

@Composable
fun RiwayatYukBuangCard(title: String, info: List<String>) {
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
fun StatusItem(title: String, iconId: Int, isActive: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = iconId),
            contentDescription = title,
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = if (isActive) Color(0xFF4CAF50) else Color.Gray,
                    shape = CircleShape
                ),
            contentScale = ContentScale.Crop
        )
        Text(
            text = title,
            fontSize = 12.sp,
            color = if (isActive) Color(0xFF4CAF50) else Color.Gray,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun DottedLinetest() {
    Row(
        modifier = Modifier
            .width(50.dp)
            .padding(horizontal = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        repeat(6) {
            Box(
                modifier = Modifier
                    .size(4.dp)
                    .background(Color(0xFF4CAF50), CircleShape) // Warna diubah menjadi hijau
            )
        }
    }
}


@Composable
fun TransactionInfoCard(title: String, details: List<String>) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            details.forEach { detail ->
                Text(
                    text = detail,
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRiwayatYukBuangBerhasilScreen() {
    RiwayatYukBuangBerhasilScreen()
}
