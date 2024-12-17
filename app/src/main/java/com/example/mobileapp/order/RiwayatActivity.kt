package com.example.mobileapp.order

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobileapp.R
import com.example.mobileapp.ui.theme.MobileAPPTheme
import androidx.compose.foundation.lazy.items
import com.example.mobileapp.cancellation.PembatalanYukAngkutActivity


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
            TopAppBar(
                modifier = Modifier.background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFF79D7C7), Color(0xFF52AC9D))
                    )
                ),
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Riwayat",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { (context as? ComponentActivity)?.finish() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color.White)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(getTransactions()) { transaction ->
                RiwayatCard(transaction) {
                    // Periksa status transaksi
                    when {
                        transaction.status == "Yuk Angkut!" && transaction.isSuccessful -> {
                            val intent = Intent(context, RiwayatYukAngkutBerhasilActivity::class.java)
                            context.startActivity(intent)
                        }
                        transaction.status == "Yuk Buang!" && transaction.isSuccessful -> {
                            val intent = Intent(context, RiwayatYukBuangBerhasilActivity::class.java)
                            context.startActivity(intent)
                        }
                        transaction.status == "Penukaran Kuy Point! Bank" -> {
                            val intent = Intent(context, RiwayatBankActivity::class.java)
                            context.startActivity(intent)
                        }
                        transaction.status == "Penukaran Kuy Point! Ewallet" -> {
                            val intent = Intent(context, RiwayatDanaActivity::class.java)
                            context.startActivity(intent)
                        }
                        transaction.status == "Yuk Angkut! Gagal" -> {
                            val intent = Intent(context, PembatalanYukAngkutActivity::class.java)
                            context.startActivity(intent)
                        }
                        transaction.status == "Yuk Buang! Gagal" -> {
                            val intent = Intent(context, PembatalanYukBuangActivity::class.java)
                            context.startActivity(intent)
                        }
                    }


                }
            }
        }
    }
}




@Composable
fun RiwayatCard(transaction: TransactionData, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick), // Add the clickable modifier
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon on the left side
            Icon(
                painter = painterResource(id = transaction.iconResId),
                contentDescription = null,
                modifier = Modifier.size(48.dp),
                tint = Color.Unspecified
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Transaction details
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = transaction.status,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = transaction.color
                    )
                    if (transaction.isSuccessful) {
                        Text(
                            text = " Berhasil",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF00796B)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = transaction.description,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(4.dp))
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
    val iconResId: Int,
    val isSuccessful: Boolean = false
)

fun getTransactions(): List<TransactionData> {
    return listOf(
        TransactionData(
            status = "Yuk Angkut! Gagal",
            description = "Kamu telah membatalkan transaksi jual sampahmu",
            time = "23 April 14:40",
            color = Color.Red,
            iconResId = R.drawable.yukangkut
        ),
        TransactionData(
            status = "Penukaran Kuy Point! Bank",
            description = "Penukaran Kuy Point ke saldo Bank Rp 15.000",
            time = "28 Maret 13:40",
            color = Color.Black,
            iconResId = R.drawable.dompet
        ),
        TransactionData(
            status = "Yuk Angkut!",
            description = "Jln. Melati Blok C3 No. 28 Batam",
            time = "19 April 09:40",
            color = Color.Black,
            iconResId = R.drawable.yukangkut,
            isSuccessful = true
        ),
        TransactionData(
            status = "Penukaran Kuy Point! Ewallet",
            description = "Penukaran Kuy Point ke saldo E-Wallet Rp 15.000",
            time = "01 Maret 12:30",
            color = Color.Black,
            iconResId = R.drawable.transfer
        ),
        TransactionData(
            status = "Yuk Buang!",
            description = "Jln. Melati Blok C3 No. 28 Batam",
            time = "17 Maret 13:40",
            color = Color.Black,
            iconResId = R.drawable.yukbuang,
            isSuccessful = true
        ),
        TransactionData(
            status = "Yuk Buang! Gagal",
            description = "Kamu telah membatalkan transaksi jual sampahmu",
            time = "29 Maret 10:40",
            color = Color.Red,
            iconResId = R.drawable.yukbuang
        )
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewRiwayatScreen() {
    MobileAPPTheme {
        RiwayatScreen()
    }
}

// Helper function to convert Brush to Color
fun Brush.toColor(): Color {
    return Color.Transparent
}
