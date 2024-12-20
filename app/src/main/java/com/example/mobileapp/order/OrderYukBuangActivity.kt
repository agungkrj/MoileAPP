package com.example.mobileapp.order

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
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

class OrderYukBuangActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileAPPTheme {
                OrderYukBuangScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YukBuangHeader(title: String) {
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
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF55B3A4))
    )
}

@Composable
fun OrderYukBuangScreen() {
    var isCancelled by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { YukBuangHeader(title = "Detail Order") }
    ) { innerPadding ->
        if (isCancelled) {
            // Tampilan Batal Order
            BatalOrderYukBuangScreen()
        } else {
            // Tampilan Detail Order
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(Color.White)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                YukBuangStatusSection()

                Spacer(modifier = Modifier.height(8.dp))

                YukBuangDescriptionText()

                Spacer(modifier = Modifier.height(16.dp))

                YukBuangDetailSection()

                Spacer(modifier = Modifier.height(16.dp))

                YukBuangCancelButton { showDialog = true }
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    TextButton(onClick = {
                        isCancelled = true // Mengubah status transaksi menjadi dibatalkan
                        showDialog = false
                    }) {
                        Text("Ya", color = Color.Red)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Tidak", color = Color.Gray)
                    }
                },
                title = { Text("Batalkan Transaksi") },
                text = { Text("Anda yakin ingin membatalkan transaksi?") }
            )
        }
    }
}

@Composable
fun BatalOrderYukBuangScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        BatalYukBuangStatusSection()

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Transaksi Yuk Buang! Kamu telah dibatalkan.",
            fontSize = 14.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        YukBuangDetailSection()

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Transaksi Yuk Buang! Kamu telah dibatalkan.",
            fontSize = 14.sp,
            color = Color(0xFF55B3A4),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
    }
}

@Composable
fun YukBuangStatusSection() {
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
            YukBuangDottedLine()
            YukBuangStatusItem("Telah Diterima", Color.Gray, R.drawable.terima)
            YukBuangDottedLine()
            YukBuangStatusItem("Transaksi Berhasil", Color.Gray, R.drawable.berhasil)
        }
    }
}

@Composable
fun BatalYukBuangStatusSection() {
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
            YukBuangDottedLine()
            YukBuangStatusItem("Dibatalkan", Color.Red, R.drawable.cancel)
        }
    }
}

@Composable
fun YukBuangStatusItem(title: String, color: Color, iconId: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(80.dp)
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = title,
            tint = color,
            modifier = Modifier.size(32.dp)
        )
        Text(
            text = title,
            fontSize = 12.sp,
            color = color,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun YukBuangDottedLine() {
    Row(
        modifier = Modifier
            .width(40.dp)
            .padding(horizontal = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        repeat(6) {
            Box(
                modifier = Modifier
                    .size(4.dp)
                    .background(Color.Gray, shape = CircleShape)
            )
        }
    }
}

@Composable
fun YukBuangDescriptionText() {
    Text(
        text = "Kami sedang meninjau permintaan Yuk Buang! Kamu. Silakan menunggu maksimal 1x24 jam untuk proses selanjutnya.",
        fontSize = 14.sp,
        color = Color.Black,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
}

@Composable
fun YukBuangDetailSection() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        YukBuangInfoCard(
            title = "Informasi Tempat Tinggal",
            info = listOf(
                "No. Ponsel : 081234567810",
                "Alamat : Jln. Melati Blok C3 No. 28, Batam"
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        YukBuangInfoCard(
            title = "Informasi Pengantaran",
            info = listOf(
                "Tanggal : 09 Maret 2024",
                "Waktu : 10.40 WIB",
                "Alamat : Simpang Polsek Kecamatan Nongsa, Batam"
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        YukBuangInfoCard(
            title = "Informasi Penjualan",
            info = listOf(
                "Kertas 1 kg Rp 1500 s.d Rp 2000",
                "Estimasi harga  Rp 1500 s.d Rp 2000",
                "Biaya Layanan Rp 150 s.d Rp 200",
                "Estimasi Penerimaan Rp1350 s.d Rp1800"
            )
        )
    }
}

@Composable
fun YukBuangInfoCard(title: String, info: List<String>) {
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
fun YukBuangCancelButton(onCancel: () -> Unit) {
    Button(
        onClick = onCancel,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Batalkan Transaksi", color = Color.White, fontWeight = FontWeight.Bold)
    }
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = "Anda hanya memiliki waktu 5 menit untuk membatalkan transaksi",
        fontSize = 12.sp,
        color = Color.Red,
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewOrderYukBuangScreen() {
    MobileAPPTheme {
        OrderYukBuangScreen()
    }
}
