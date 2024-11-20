package com.example.mobileapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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

class RiwayatActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileAPPTheme {
                RiwayatScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RiwayatScreen() {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Riwayat",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        val intent = Intent(context, OrderActivity::class.java)
                        context.startActivity(intent)
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF55B3A4) // Warna gradasi atas
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color.White)
        ) {
            // Konten di bawah AppBar
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Example Data
                items(8) { index ->
                    val transaction = when (index % 4) {
                        0 -> TransactionData(
                            status = "Gagal",
                            description = "Kamu telah membatalkan transaksi just sampahmu",
                            time = "23 April 14:40",
                            color = Color.Red,
                            iconResId = R.drawable.yukangkut
                        )
                        1 -> TransactionData(
                            status = "Penukaran Kuy Point!",
                            description = "Penukaran Kuy Point ke saldo Bank Rp 15.000",
                            time = "28 Maret 13:40",
                            color = Color.Green,
                            iconResId = R.drawable.yukbuang
                        )
                        2 -> TransactionData(
                            status = "Yuk Angkut! Berhasil",
                            description = "Bukit Cangkok Blok D2. No. 09 Batam",
                            time = "19 April 09:40",
                            color = Color.Green,
                            iconResId = R.drawable.yukbuang
                        )
                        else -> TransactionData(
                            status = "Yuk Buang! Berhasil",
                            description = "Jln. Anggrek Blok A2 No. 14 Batam",
                            time = "1 Maret 13:40",
                            color = Color.Green,
                            iconResId = R.drawable.yukangkut
                        )
                    }
                    RiwayatCard(transaction)
                }
            }
        }
    }
}

@Composable
fun RiwayatCard(transaction: TransactionData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Transaction Icon
            Icon(
                painter = painterResource(id = transaction.iconResId),
                contentDescription = null,
                modifier = Modifier.size(48.dp),
                tint = Color.Unspecified
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Transaction Details
            Column {
                Text(
                    text = transaction.status,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color(0xFF00796B)
                )
                Text(
                    text = transaction.description,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = transaction.time,
                    fontSize = 12.sp,
                    color = Color.DarkGray
                )
            }
        }
    }
}

data class TransactionData(
    val status: String,
    val description: String,
    val time: String,
    val color: Color,
    val iconResId: Int
)

@Preview(showBackground = true)
@Composable
fun PreviewRiwayatScreen() {
    MobileAPPTheme {
        RiwayatScreen()
    }
}
