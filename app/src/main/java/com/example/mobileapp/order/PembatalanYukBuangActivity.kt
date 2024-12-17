package com.example.mobileapp.order

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import com.example.mobileapp.R
import com.example.mobileapp.ui.theme.MobileAPPTheme
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.unit.Dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row

class PembatalanYukBuangActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileAPPTheme {
                PembatalanScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PembatalanScreen() {
    Scaffold(
        topBar = { PembatalanHeader(title = "Detail Pembatalan") }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
                .verticalScroll(rememberScrollState())
        ) {
            // Status Pembatalan
            PembatalanStatusSection()

            // Informasi Pembatalan
            Text(
                text = "Transaksi Yuk Buang! Mu telah dibatalkan",
                fontSize = 14.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 16.dp)
            )

            // Detail Pembatalan
            PembatalanDetailSection()

            Spacer(modifier = Modifier.height(16.dp))

            // Pesan Akhir
            Text(
                text = "Transaksi Yuk Buang! Kamu telah dibatalkan",
                fontSize = 14.sp,
                color = Color(0xFF55B3A4),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE0F7F5), shape = RoundedCornerShape(50.dp))
                    .padding(12.dp)
                    .padding(horizontal = 32.dp)  // Increased horizontal padding
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PembatalanHeader(title: String) {
    val context = LocalContext.current
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {
                (context as? ComponentActivity)?.finish()
            }) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = Color.Black
                )
            }
        },
        title = {
            Text(
                title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF55B3A4))
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PembatalanStatusSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F7F7))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Status Sedang Diproses
            StatusItem("Sedang Diproses", Color(0xFF4CAF50), R.drawable.proses)

            // Garis Putus-putus
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
                    .height(4.dp)
            ) {
                DottedLine()
            }

            // Status Telah Dibatalkan
            StatusItem("Telah Dibatalkan", Color.Red, R.drawable.cancel)
        }
    }
}

@Composable
fun StatusItem(title: String, color: Color, iconRes: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = title,
            tint = color,
            modifier = Modifier.size(48.dp) // Ukuran ikon
        )
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = color,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun DottedLine() {
    Canvas(modifier = Modifier.fillMaxWidth()) {
        val dotRadius = 5f
        val spaceBetweenDots = 20f
        val totalWidth = size.width

        var currentX = 0f
        while (currentX < totalWidth) {
            drawCircle(
                color = Color.Red,
                radius = dotRadius,
                center = androidx.compose.ui.geometry.Offset(currentX, size.height / 2)
            )
            currentX += spaceBetweenDots
        }
    }
}

@Composable
fun PembatalanDetailSection() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        PembatalanInfoCard(
            title = "Informasi Tempat Tinggal",
            info = listOf(
                "No. Ponsel : 081234567810",
                "Alamat : Jln. Melati Blok C3 No. 28, Batam"
            ),
            isLeftAligned = true
        )
        PembatalanInfoCard(
            title = "Informasi Penjemputan",
            info = listOf(
                "Tanggal : 09 Maret 2024",
                "Waktu : 10.40 WIB",
                "Alamat : Simpang Polsek Kecamatan Nongsa, Batam" // Alamat ditambahkan di sini
            ),
            isLeftAligned = true
        )
        PembatalanInfoCard(
            title = "Informasi Penjualan",
            info = listOf(
                "Kertas 1 kg", "Rp 1500 s.d Rp 2000",
                "Estimasi harga", "Rp 1500 s.d Rp 2000",
                "Biaya Layanan", "Rp 150 s.d Rp 200",
                "Estimasi penerimaan", "Rp 1350 s.d Rp 1800"
            )
        )
    }
}


@Composable
fun PembatalanInfoCard(title: String, info: List<String>, isLeftAligned: Boolean = false) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .border(1.dp, Color(0xFFE0E0E0), shape = RoundedCornerShape(8.dp))
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column {
                // Header Card
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF5F5F5))
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                ) {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                }
                // Isi Card
                Column(modifier = Modifier.padding(16.dp)) {
                    if (isLeftAligned) {
                        info.forEach { text ->
                            Text(
                                text = text,
                                fontSize = 14.sp,
                                color = Color.Black,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                            )
                        }
                    } else {
                        info.chunked(2).forEach { row ->
                            if (row.size == 2) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = row[0],
                                        fontSize = 14.sp,
                                        color = Color.Black,
                                        modifier = Modifier.weight(1f)
                                    )
                                    Text(
                                        text = row[1],
                                        fontSize = 14.sp,
                                        color = Color.Black,
                                        modifier = Modifier.weight(1f),
                                        textAlign = TextAlign.End  // Align price to the right
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPembatalanYukBuang() {
    MobileAPPTheme {
        PembatalanScreen()
    }
}
