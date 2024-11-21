@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mobileapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.example.mobileapp.ui.theme.MobileAPPTheme
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable

class PembayaranYukBuangActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileAPPTheme {
                PembayaranYukBuangScreen()
            }
        }
    }
}

@Composable
fun PembayaranYukBuangScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        PembayaranYukBuangHeader()

        Spacer(modifier = Modifier.height(20.dp))

        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            InformasiYukBuangSection("Informasi Tempat Tinggal", listOf("No. Ponsel :" to "081234567890", "Alamat :" to "Jl. Contoh Alamat No.123"))
            Spacer(modifier = Modifier.height(8.dp))

            InformasiPengantaranSection()
            Spacer(modifier = Modifier.height(8.dp))

            InformasiYukBuangPenjualan()

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Metode Pembayaran",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            MetodePembayaranYukBuang()

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* Handle send action */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF55B3A4))
            ) {
                Text(text = "Kirim", color = Color.White)
            }
        }
    }
}

@Composable
fun PembayaranYukBuangHeader() {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFF79D7C7), Color(0xFF52AC9D))
                ),
                shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
            )
            .padding(vertical = 24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = {
                val intent = Intent(context, BuangActivity::class.java)
                context.startActivity(intent)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.panah),
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Pembayaran",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Selesaikan transaksi mu sekarang.",
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun InformasiYukBuangSection(title: String, fields: List<Pair<String, String>>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFD3D3D3), shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
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
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                fields.forEach { (label, value) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = label,
                            fontSize = 14.sp,
                            color = Color.Black,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = value,
                            fontSize = 14.sp,
                            color = Color.Black
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun InformasiPengantaranSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFD3D3D3), shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                    .padding(16.dp)
            ) {
                Text(
                    text = "Informasi Pengantaran",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Tanggal :", fontSize = 14.sp, color = Color.Black)
                    Text(text = "12-12-2023", fontSize = 14.sp, color = Color.Black)
                }
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Waktu :", fontSize = 14.sp, color = Color.Black)
                    Text(text = "10:00 AM", fontSize = 14.sp, color = Color.Black)
                }
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Alamat :", fontSize = 14.sp, color = Color.Black)
                    Text(text = "Jl. Contoh Alamat No.123", fontSize = 14.sp, color = Color.Black)
                }
            }
        }
    }
}

@Composable
fun InformasiYukBuangPenjualan() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFD3D3D3), shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                    .padding(16.dp)
            ) {
                Text(
                    text = "Informasi Penjualan",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Jenis", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
                    Text(text = "Berat", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
                    Text(text = "Harga", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
                }

                Divider(color = Color.LightGray, thickness = 1.dp)
                Spacer(modifier = Modifier.height(8.dp))

                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Estimasi harga", fontSize = 14.sp, color = Color.Black)
                        Text(text = "Rp.----.--", fontSize = 14.sp, color = Color.Black)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Ongkos Kirim", fontSize = 14.sp, color = Color.Black)
                        Text(text = "Rp.----.--", fontSize = 14.sp, color = Color.Black)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Estimasi penerimaan", fontSize = 14.sp, color = Color.Black)
                        Text(text = "Rp.----.--", fontSize = 14.sp, color = Color.Black)
                    }
                }
            }
        }
    }
}

@Composable
fun MetodePembayaranYukBuang() {
    val selectedOption = remember { mutableStateOf<String?>(null) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        PembayaranYukBuangOption(
            text = "Tunai",
            iconResId = R.drawable.tunai,
            isSelected = selectedOption.value == "Tunai",
            onClick = { selectedOption.value = "Tunai" }
        )
        PembayaranYukBuangOption(
            text = "Kuy Point",
            iconResId = R.drawable.koin,
            isSelected = selectedOption.value == "Kuy Point",
            onClick = { selectedOption.value = "Kuy Point" }
        )
    }
}

@Composable
fun PembayaranYukBuangOption(
    text: String,
    iconResId: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .padding(4.dp)
            .clickable { onClick() }
            .border(
                width = if (isSelected) 0.dp else 1.dp,
                color = if (isSelected) Color.Transparent else Color(0xFF55B3A4),
                shape = RoundedCornerShape(16.dp)
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Color(0xFF55B3A4) else Color.Transparent
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = text,
                modifier = Modifier.size(30.dp)
            )
            Text(
                text = text,
                fontSize = 16.sp,
                color = if (isSelected) Color.White else Color.Black,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPembayaranYukBuangScreen() {
    MobileAPPTheme {
        PembayaranYukBuangScreen()
    }
}
