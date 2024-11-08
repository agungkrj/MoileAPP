package com.example.mobileapp

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobileapp.ui.theme.MobileAPPTheme

class AngkutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileAPPTheme {
                AngkutScreen()
            }
        }
    }
}

@Composable
fun AngkutScreen() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp)
    ) {
        HeaderSection()

        Spacer(modifier = Modifier.height(16.dp))

        // Jenis Sampah Section
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

        SampahOptions()

        Spacer(modifier = Modifier.height(16.dp))

        // Informasi Penjemputan Section
        Text(
            text = "Informasi Penjemputan",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        PenjemputanForm()

        Spacer(modifier = Modifier.height(16.dp))

        // Pesan Tambahan Section
        Text(
            text = "Pesan Tambahan",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        CustomTextField(label = "Masukkan pesan tambahan di sini", isMultiline = true)

        Spacer(modifier = Modifier.height(16.dp))

        // Foto Sampah Section
        Text(
            text = "Foto Sampah",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        FotoSampahSection()

        Spacer(modifier = Modifier.height(24.dp))

        // Button Selanjutnya
        Button(
            onClick = { /* Handle button click */ },
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

@Composable
fun HeaderSection() {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF55B3A4), shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
            .padding(vertical = 20.dp, horizontal = 8.dp),
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
                text = "Yuk Angkut!",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = "Pesan sekarang, sampah mu langsung diangkut!",
                fontSize = 14.sp,
                color = Color.White,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Composable
fun SampahOptions() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            KertasCard()
            PlastikCard()
            BotolKacaCard()
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ElektronikCard()
            BesiCard()
            AluminiumCard()
        }
    }
}

@Composable
fun KertasCard() {
    val context = LocalContext.current
    SampahActionCard(
        name = "Kertas",
        iconRes = R.drawable.kertas,
        color = Color(0xFF4CAF50)
    ) {
        val intent = Intent(context, JenisSampahActivity::class.java)
        context.startActivity(intent)
    }
}

@Composable
fun PlastikCard() {
    SampahActionCard(
        name = "Plastik",
        iconRes = R.drawable.plastik,
        color = Color(0xFFFF9800)
    ) {
        println("Plastik clicked")
    }
}

@Composable
fun BotolKacaCard() {
    SampahActionCard(
        name = "Botol Kaca",
        iconRes = R.drawable.kaca,
        color = Color(0xFF2196F3)
    ) {
        println("Botol Kaca clicked")
    }
}

@Composable
fun ElektronikCard() {
    SampahActionCard(
        name = "Elektronik",
        iconRes = R.drawable.elektronik,
        color = Color(0xFFF44336)
    ) {
        println("Elektronik clicked")
    }
}

@Composable
fun BesiCard() {
    SampahActionCard(
        name = "Besi",
        iconRes = R.drawable.besi,
        color = Color(0xFF607D8B)
    ) {
        println("Besi clicked")
    }
}

@Composable
fun AluminiumCard() {
    SampahActionCard(
        name = "Aluminium",
        iconRes = R.drawable.alumanium1,
        color = Color(0xFF9C27B0)
    ) {
        println("Aluminium clicked")
    }
}

@Composable
fun SampahActionCard(name: String, iconRes: Int, color: Color, onClick: () -> Unit) {
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
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = name,
                modifier = Modifier.size(36.dp),
                colorFilter = ColorFilter.tint(color)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = name,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }
    }
}

@Composable
fun PenjemputanForm() {
    Column(modifier = Modifier.fillMaxWidth()) {
        CustomTextField(label = "No. Ponsel :")
        Spacer(modifier = Modifier.height(8.dp))
        CustomTextField(label = "Alamat :", isMultiline = true)
        Spacer(modifier = Modifier.height(8.dp))
        CustomTextField(label = "Tanggal :")
        Spacer(modifier = Modifier.height(8.dp))
        CustomTextField(label = "Waktu :")
    }
}

@Composable
fun FotoSampahSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        FotoSampahCard()
        FotoSampahCard()
    }
}

@Composable
fun FotoSampahCard() {
    Card(
        modifier = Modifier
            .size(100.dp)
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
fun CustomTextField(label: String, isMultiline: Boolean = false) {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        label = { Text(text = label) },
        modifier = Modifier
            .fillMaxWidth()
            .height(if (isMultiline) 100.dp else 56.dp),
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF55B3A4),
            unfocusedBorderColor = Color.Gray
        )
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewAngkutScreen() {
    MobileAPPTheme {
        AngkutScreen()
    }
}
