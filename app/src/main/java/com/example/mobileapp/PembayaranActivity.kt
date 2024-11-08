@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mobileapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobileapp.ui.theme.MobileAPPTheme

class PembayaranActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileAPPTheme {
                PembayaranScreen()
            }
        }
    }
}

@Composable
fun PembayaranScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp)
    ) {
        PembayaranHeaderSection()

        Spacer(modifier = Modifier.height(16.dp))

        InformasiSection("Informasi Tempat Tinggal", listOf("No. Ponsel :", "Alamat :"))
        Spacer(modifier = Modifier.height(8.dp))
        InformasiSection("Informasi Penjemputan", listOf("Tanggal :", "Waktu :"))
        Spacer(modifier = Modifier.height(8.dp))
        InformasiPenjualanSection()

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Metode Pembayaran",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        MetodePembayaranSection()

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

@Composable
fun PembayaranHeaderSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF55B3A4), shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp))
            .padding(vertical = 20.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /* Handle back action */ }) {
            Icon(
                painter = painterResource(id = R.drawable.panah), // Ganti dengan ikon panah
                contentDescription = "Back",
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = "Pembayaran",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = "Selesaikan transaksi mu sekarang.",
                fontSize = 14.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun InformasiSection(title: String, fields: List<String>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF7F7F7))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            fields.forEach { label ->
                OutlinedTextField(
                    value = "",
                    onValueChange = { /* Handle input */ },
                    label = { Text(label) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Gray,
                        unfocusedBorderColor = Color.Gray
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun InformasiPenjualanSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF7F7F7))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Informasi Penjualan",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Jenis", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color.Gray)
                Text(text = "Berat", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color.Gray)
                Text(text = "Harga", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color.Gray)
            }

            Divider(color = Color.LightGray, thickness = 1.dp)

            Column {
                Text(text = "Estimasi Harga", fontSize = 14.sp, color = Color.Gray)
                Text(text = "Ongkos Kirim", fontSize = 14.sp, color = Color.Gray)
                Text(text = "Estimasi Penerimaan", fontSize = 14.sp, color = Color.Gray)
            }
        }
    }
}

@Composable
fun MetodePembayaranSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        PembayaranOption("Tunai", R.drawable.tunai) // Ganti dengan ikon tunai
        PembayaranOption("Kuy Point", R.drawable.koin) // Ganti dengan ikon Kuy Point
    }
}

@Composable
fun PembayaranOption(text: String, iconResId: Int) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .padding(4.dp)
            .border(1.dp, Color(0xFF55B3A4), RoundedCornerShape(16.dp)), // Border hijau dan bentuk melengkung
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
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
                modifier = Modifier.size(30.dp) // Sesuaikan ukuran ikon
            )
            Text(
                text = text,
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPembayaranScreen() {
    MobileAPPTheme {
        PembayaranScreen()
    }
}
