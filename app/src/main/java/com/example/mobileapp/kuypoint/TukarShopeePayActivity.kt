package com.example.mobileapp.kuypoint

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobileapp.R
import com.example.mobileapp.ui.theme.MobileAPPTheme

@OptIn(ExperimentalMaterial3Api::class)
class TukarShopeePayActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileAPPTheme {
                TukarShopeePayMainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TukarShopeePayMainScreen() {
    val shopeePayNumber = remember { mutableStateOf(TextFieldValue("")) }
    val selectedNominal = remember { mutableStateOf("") }

    // Validasi jika semua input sudah diisi
    val isFormComplete = remember(shopeePayNumber.value.text, selectedNominal.value) {
        shopeePayNumber.value.text.isNotEmpty() && selectedNominal.value.isNotEmpty()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header
        TukarShopeePayHeader()

        Spacer(modifier = Modifier.height(16.dp))

        // Header Text
        Text(
            text = "Tukar Kuy Point dengan saldo ShopeePay",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Text(
            text = "Sekarang kamu bisa menukarkan KuyPoint yang kamu miliki dengan saldo ShopeePay! Ayo lakukan lebih banyak Yuk Angkut! dan Yuk Buang!",
            fontSize = 15.sp,
            color = Color.Gray,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Input Nomor ShopeePay
        Text(
            text = "Masukkan Nomor ShopeePay Kamu",
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        TextField(
            value = shopeePayNumber.value,
            onValueChange = { shopeePayNumber.value = it },
            placeholder = { Text(text = "Masukkan nomor ShopeePay") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp)),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFF5F5F5)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Nominal Options
        Text(
            text = "Nominal",
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 16.dp, end = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TukarShopeePayNominalButton("Rp 20.000", Modifier.weight(1f), selectedNominal)
                TukarShopeePayNominalButton("Rp 30.000", Modifier.weight(1f), selectedNominal)
                TukarShopeePayNominalButton("Rp 50.000", Modifier.weight(1f), selectedNominal)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TukarShopeePayNominalButton("Rp 70.000", Modifier.weight(1f), selectedNominal)
                TukarShopeePayNominalButton("Rp 80.000", Modifier.weight(1f), selectedNominal)
                TukarShopeePayNominalButton("Rp 100.000", Modifier.weight(1f), selectedNominal)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Custom amount input
        Text(
            text = "Masukkan nominal",
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp))
                .height(50.dp)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = selectedNominal.value.ifEmpty { "Minimal top up Rp 15.000" },
                fontSize = 14.sp,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(100.dp))

        // Exchange button
        Button(
            onClick = { /* Handle exchange click */ },
            enabled = isFormComplete,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isFormComplete) Color(0xFF55B3A4) else Color.Gray
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Tukar Sekarang", fontSize = 16.sp, color = Color.White)
        }
    }
}

@Composable
fun TukarShopeePayHeader() {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF55B3A4))
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        Text(
            text = "Tukar Kuy Point",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.Center)
        )
        Icon(
            painter = painterResource(id = R.drawable.keluar),
            contentDescription = "Back",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .size(24.dp)
                .clickable {
                    (context as? ComponentActivity)?.finish()
                },
            tint = Color.Black
        )
    }
}

@Composable
fun TukarShopeePayNominalButton(text: String, modifier: Modifier = Modifier, selectedNominal: MutableState<String>) {
    val isSelected = selectedNominal.value == text

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(if (isSelected) Color(0xFF55B3A4) else Color(0xFFF5F5F5), RoundedCornerShape(8.dp))
            .clickable { selectedNominal.value = text }
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            color = if (isSelected) Color.White else Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTukarShopeePayMainScreen() {
    MobileAPPTheme {
        TukarShopeePayMainScreen()
    }
}
