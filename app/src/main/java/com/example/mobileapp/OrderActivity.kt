package com.example.mobileapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobileapp.components.BottomNavigationBar
import com.example.mobileapp.ui.theme.MobileAPPTheme

class OrderActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileAPPTheme {
                OrderScreen()
            }
        }
    }
}

@Composable
fun OrderScreen() {
    val selectedItem = remember { mutableStateOf("Order") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header with linear gradient and centered title
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFF55B3A4), Color(0xFF00796B))
                    )
                )
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Order",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            // Icon button aligned to the right
            IconButton(
                onClick = { /* TODO: add action */ },
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.home), // Replace with your history icon
                    contentDescription = "History",
                    tint = Color.White
                )
            }
        }

        // Main content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // List of Order Cards with specific icons
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    OrderCard(
                        title = "Yuk Buang!",
                        description = "Sedang meninjau permintaan Yuk Buang! Kamu. Silahkan menunggu maksimal 1x24 jam untuk proses selanjutnya",
                        time = "10:40 WIB",
                        iconResId = R.drawable.yukbuang,
                        onClick = { /* Aksi ketika kartu 'Yuk Buang!' ditekan */ }
                    )
                }
                item {
                    OrderCard(
                        title = "Yuk Angkut!",
                        description = "Sedang meninjau permintaan Yuk Angkut! Kamu. Silahkan menunggu maksimal 1x24 jam untuk proses selanjutnya",
                        time = "14:40 WIB",
                        iconResId = R.drawable.yukangkut,
                        onClick = { /* Aksi ketika kartu 'Yuk Angkut!' ditekan */ }
                    )
                }
                item {
                    OrderCard(
                        title = "Tukar Kuy Point!",
                        description = "Sedang meninjau penukaran Kuy Point Kamu. Silahkan menunggu maksimal 1x24 jam untuk proses selanjutnya",
                        time = "13:40 WIB",
                        iconResId = R.drawable.transfer,
                        onClick = { /* Aksi ketika kartu 'Tukar Kuy Point!' ditekan */ }
                    )
                }
                item {
                    OrderCard(
                        title = "Tukar Kuy Point!",
                        description = "Sedang meninjau penukaran Kuy Point Kamu. Silahkan menunggu maksimal 1x24 jam untuk proses selanjutnya",
                        time = "12:30 WIB",
                        iconResId = R.drawable.dompet,
                        onClick = { /* Aksi ketika kartu 'Tukar Kuy Point!' ditekan */ }
                    )
                }
            }

            // Lihat Riwayat
            Text(
                text = "Lihat Riwayat",
                fontSize = 14.sp,
                color = Color(0xFF00796B),
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .clickable { /* TODO: add action */ }
            )

            // BottomNavigationBar
            BottomNavigationBar(
                selectedItem = selectedItem,
                onItemSelected = { selectedItem.value = it }
            )
        }
    }
}

@Composable
fun OrderCard(title: String, description: String, time: String, iconResId: Int, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(361.58.dp)
            .height(170.dp)
            .padding(vertical = 4.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = null,
                modifier = Modifier.size(48.dp),
                tint = Color.Unspecified
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = "Sedang Diproses",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color(0xFF00796B)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    fontSize = 10.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = time,
                    fontSize = 12.sp,
                    color = Color.DarkGray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOrderScreen() {
    MobileAPPTheme {
        OrderScreen()
    }
}
