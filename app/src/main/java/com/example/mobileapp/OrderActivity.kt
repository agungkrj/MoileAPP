package com.example.mobileapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

class OrderActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    OrderScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun OrderScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEFFBF4)) // Background color of the screen
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Order",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            actions = {
                IconButton(onClick = { /* history action */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.history),
                        contentDescription = "History",
                        tint = Color.Black,
                        modifier = Modifier.size(28.dp)
                    )
                }
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = Color(0xFF00B388), // Top bar color
                titleContentColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Column(
            modifier = Modifier
                .padding(16.dp)
                .weight(1f)
        ) {
            OrderItem(
                iconRes = R.drawable.ic_box,
                status = "Sedang Diproses",
                title = "Sedang meninjau permintaan Yuk Buang! Kamu.",
                time = "10:40 WIB"
            )
            Spacer(modifier = Modifier.height(8.dp))
            OrderItem(
                iconRes = R.drawable.ic_truck,
                status = "Sedang Diproses",
                title = "Sedang meninjau permintaan Yuk Angkut! Kamu.",
                time = "14:40 WIB"
            )
            Spacer(modifier = Modifier.height(8.dp))
            OrderItem(
                iconRes = R.drawable.ic_points,
                status = "Sedang Diproses",
                title = "Sedang meninjau penukaran Kuy Point Kamu.",
                time = "13:40 WIB"
            )
            Spacer(modifier = Modifier.height(8.dp))
            OrderItem(
                iconRes = R.drawable.ic_wallet,
                status = "Sedang Diproses",
                title = "Sedang meninjau penukaran Kuy Point Kamu.",
                time = "12:30 WIB"
            )
        }

        Text(
            text = "Lihat Riwayat",
            color = Color(0xFF00B388),
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Center
        )

        val context = LocalContext.current

        BottomNavigationBar(
            selectedScreen = "Order",
            onItemSelected = { selectedScreen ->
                when (selectedScreen) {
                    "Beranda" -> println("Navigasi ke Beranda")
                    "Order" -> println("Navigasi ke Order")
                    "Profil" -> println("Navigasi ke Profil")
                }
            },
            context = context
        )
    }
}

@Composable
fun OrderItem(iconRes: Int, status: String, title: String, time: String) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = status,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF00B388)
                )
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
                Text(
                    text = time,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}
