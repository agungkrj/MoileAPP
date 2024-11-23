package com.example.mobileapp.yukbuang

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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobileapp.sampah.AluminiumActivity
import com.example.mobileapp.sampah.BesiActivity
import com.example.mobileapp.sampah.BotolKacaActivity
import com.example.mobileapp.beranda.DashboardActivity
import com.example.mobileapp.sampah.ElektronikActivity
import com.example.mobileapp.sampah.JenisSampahActivity
import com.example.mobileapp.sampah.PlastikActivity
import com.example.mobileapp.R
import com.example.mobileapp.ui.theme.MobileAPPTheme

class BuangActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileAPPTheme {
                BuangScreen()
            }
        }
    }
}

@Composable
fun BuangScreen() {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    // State untuk pesan tambahan
    val additionalMessageState = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
    ) {
        BuangHeaderSection()

        Spacer(modifier = Modifier.height(16.dp))

        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                text = "Jenis Sampah",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Pilih jenis dan masukkan perkiraan berat sampah.",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            BuangSampahOptions()

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Informasi Tempat Tinggal",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            BuangTempatTinggalForm()

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Informasi Pengantaran",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            BuangPengantaranForm()

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Pesan Tambahan",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            BuangCustomTextField(label = "Masukkan pesan tambahan di sini", textState = additionalMessageState, isMultiline = true)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Foto Sampah",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            BuangFotoSampahSection()

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val intent = Intent(context, PembayaranYukBuangActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF55B3A4)
                )
            ) {
                Text(text = "Selanjutnya", color = Color.White)
            }
        }
    }
}

@Composable
fun BuangHeaderSection() {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(88.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFF79D7C7), Color(0xFF52AC9D))
                )
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
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
            Column {
                Text(
                    text = "Yuk Buang!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Kamu bisa antar sampah mu ke titik terdekat",
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun BuangSampahOptions() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            BuangKertasCard()
            BuangPlastikCard()
            BuangBotolKacaCard()
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            BuangElektronikCard()
            BuangBesiCard()
            BuangAluminiumCard()
        }
    }
}

@Composable
fun BuangKertasCard() {
    val context = LocalContext.current
    BuangSampahActionCard("Kertas", R.drawable.kertas, Color(0xFF4CAF50)) {
        val intent = Intent(context, JenisSampahActivity::class.java)
        context.startActivity(intent)
    }
}

@Composable
fun BuangPlastikCard() {
    val context = LocalContext.current
    BuangSampahActionCard("Plastik", R.drawable.plastik, Color(0xFFFF9800)) {
        val intent = Intent(context, PlastikActivity::class.java)
        context.startActivity(intent)

    }
}

@Composable
fun BuangBotolKacaCard() {
    val context = LocalContext.current
    BuangSampahActionCard("Botol Kaca", R.drawable.kaca, Color(0xFF2196F3)) {
        val intent = Intent(context, BotolKacaActivity::class.java)
        context.startActivity(intent)
    }
}

@Composable
fun BuangElektronikCard() {
    val context = LocalContext.current
    BuangSampahActionCard("Elektronik", R.drawable.elektronik, Color(0xFFF44336)) {
        val intent = Intent(context, ElektronikActivity::class.java)
        context.startActivity(intent)

    }
}

@Composable
fun BuangBesiCard() {
    val context = LocalContext.current
    BuangSampahActionCard("Besi", R.drawable.besi, Color(0xFF607D8B)) {
        val intent = Intent(context, BesiActivity::class.java)
        context.startActivity(intent)
    }
}

@Composable
fun BuangAluminiumCard() {
    val context = LocalContext.current
    BuangSampahActionCard("Aluminium", R.drawable.alumanium1, Color(0xFF9C27B0)) {
        val intent = Intent(context, AluminiumActivity::class.java)
        context.startActivity(intent)

    }
}

@Composable
fun BuangSampahActionCard(name: String, iconRes: Int, color: Color, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(100.dp)
            .height(120.dp)
            .padding(4.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        // Menyatukan konten ke tengah
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp), // Padding internal untuk menyesuaikan konten
            horizontalAlignment = Alignment.CenterHorizontally, // Konten horisontal di tengah
            verticalArrangement = Arrangement.Center // Konten vertikal di tengah
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = name,
                modifier = Modifier.size(40.dp), // Ukuran ikon sedikit lebih besar
                colorFilter = ColorFilter.tint(color) // Menambahkan warna ikon
            )
            Spacer(modifier = Modifier.height(8.dp)) // Spasi antara ikon dan teks
            Text(
                text = name,
                fontSize = 14.sp, // Ukuran font teks sedikit lebih besar
                fontWeight = FontWeight.Bold, // Teks dibuat lebih tebal
                color = Color.Black,
                textAlign = TextAlign.Center, // Teks berada di tengah
                maxLines = 1 // Membatasi teks hanya satu baris
            )
        }
    }
}

@Composable
fun BuangTempatTinggalForm() {
    val phoneState = remember { mutableStateOf("") }
    val addressState = remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth()) {
        BuangCustomTextField(label = "No. Ponsel :", textState = phoneState)
        Spacer(modifier = Modifier.height(8.dp))
        BuangCustomTextField(label = "Alamat :", textState = addressState, isMultiline = true)
    }
}

@Composable
fun BuangPengantaranForm() {
    val dateState = remember { mutableStateOf("") }
    val timeState = remember { mutableStateOf("") }
    val deliveryAddressState = remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth()) {
        BuangCustomTextField(label = "Tanggal :", textState = dateState)
        Spacer(modifier = Modifier.height(8.dp))
        BuangCustomTextField(label = "Waktu :", textState = timeState)
        Spacer(modifier = Modifier.height(8.dp))
        BuangCustomTextField(label = "Alamat Pengantaran :", textState = deliveryAddressState)
    }
}

@Composable
fun BuangFotoSampahSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        BuangFotoSampahCard()
        BuangFotoSampahCard()
    }
}

@Composable
fun BuangFotoSampahCard() {
    Card(
        modifier = Modifier
            .width(118.dp)
            .height(117.dp)
            .padding(4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F7F7))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.kamera),
                contentDescription = "Foto Sampah",
                tint = Color.Gray,
                modifier = Modifier.size(36.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuangCustomTextField(label: String, textState: MutableState<String>, isMultiline: Boolean = false) {
    OutlinedTextField(
        value = textState.value,
        onValueChange = { textState.value = it },
        label = { Text(text = label) },
        modifier = Modifier
            .fillMaxWidth()
            .height(if (isMultiline) 100.dp else 56.dp),
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF55B3A4),
            unfocusedBorderColor = Color.Gray
        ),
        maxLines = if (isMultiline) Int.MAX_VALUE else 1
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewBuangScreen() {
    MobileAPPTheme {
        BuangScreen()
    }
}
