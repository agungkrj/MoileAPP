package com.example.mobileapp.kuypoint

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobileapp.beranda.DashboardActivity
import com.example.mobileapp.R
import com.example.mobileapp.ui.theme.MobileAPPTheme

class TukarKuyPointActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileAPPTheme {
                TukarKuyPointScreen()
            }
        }
    }
}

@Composable
fun TukarKuyPointScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEFF7F6))
            .verticalScroll(rememberScrollState())
    ) {
        // Header with Kuy Point
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(bottom = 8.dp),
            shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF55B3A4)),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header Row with Icon and Title
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        val intent = Intent(context, DashboardActivity::class.java)
                        context.startActivity(intent)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.panah),
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Tukar Kuy Point",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Kuy Point Section Inside Header with White Background
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(80.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.koin),
                                contentDescription = "Kuy Point Icon",
                                modifier = Modifier.size(32.dp),
                                tint = Color.Unspecified
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Kuy Point",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }
                        Text(
                            text = "15.000",
                            fontWeight = FontWeight.Bold,
                            fontSize = 28.sp,
                            color = Color(0xFFCC9B4B)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Action Card for Bank
        ActionCard(
            imageResId = R.drawable.bank2,
            description = "Tukar Kuy Point Kamu ke saldo Bank Anda",
            onClick = {
                val intent = Intent(context, TukarBankActivity::class.java)
                context.startActivity(intent)
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Action Card for Dana
        ActionCard(
            imageResId = R.drawable.dana2,
            description = "Tukar Kuy Point Kamu menjadi saldo Dana",
            onClick = {
                val intent = Intent(context, TukarDanaActivity::class.java)
                context.startActivity(intent)
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Action Card for OVO
        ActionCard(
            imageResId = R.drawable.ovo2,
            description = "Tukar Kuy Point Kamu menjadi saldo OVO",
            onClick = { val intent = Intent(context, TukarOvoActivity::class.java)
                context.startActivity(intent) }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Action Card for ShopeePay
        ActionCard(
            imageResId = R.drawable.shopee2,
            description = "Tukar Kuy Point Kamu menjadi saldo ShopeePay",
            onClick = { val intent = Intent(context, TukarShopeePayActivity::class.java)
                context.startActivity(intent) }
        )
    }
}

@Composable
fun ActionCard(imageResId: Int, description: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable { onClick() }, // Menjadikan Card dapat diklik
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = "Illustration",
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                fontSize = 16.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTukarKuyPointScreen() {
    MobileAPPTheme {
        TukarKuyPointScreen()
    }
}
