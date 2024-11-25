package com.example.mobileapp.sampah

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobileapp.R
import com.example.mobileapp.ui.theme.MobileAPPTheme

class ElektronikActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileAPPTheme {
                ElektronikScreen()
            }
        }
    }
}

@Composable
fun ElektronikScreen() {
    val scrollState = rememberScrollState()
    var totalWeight by remember { mutableIntStateOf(0) } // Total berat elektronik

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp)
    ) {
        ElektronikHeader()

        Spacer(modifier = Modifier.height(8.dp))

        // Centered Card for Elektronik Icon and Name
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            ElektronikIconCard(onWeightChange = { totalWeight += it })
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sub Jenis Elektronik Section
        Text(
            text = "Sub Jenis Elektronik",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        val subJenisElektronik = listOf(
            "Handphone Bekas", "Komputer", "TV",
            "AC", "Kulkas", "Printer", "Elektronik Lainnya"
        )

        val checkedStates = remember { mutableStateListOf(*Array(subJenisElektronik.size) { false }) }

        subJenisElektronik.forEachIndexed { index, item ->
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item,
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )
                    Checkbox(
                        checked = checkedStates[index],
                        onCheckedChange = { checked ->
                            checkedStates[index] = checked
                        },
                        colors = CheckboxDefaults.colors(
                            checkmarkColor = Color.White,
                            checkedColor = Color(0xFF55B3A4)
                        )
                    )
                }
                Divider(color = Color(0xFF55B3A4), thickness = 1.dp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        ElektronikFooter(totalWeight = totalWeight)
    }
}

@Composable
fun ElektronikHeader() {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        IconButton(onClick = {
            (context as? ComponentActivity)?.finish()
        }) {
            Icon(
                painter = painterResource(id = R.drawable.panah),
                contentDescription = "Back",
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
        }
        Text(
            text = "Elektronik",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Composable
fun ElektronikIconCard(onWeightChange: (Int) -> Unit) {
    var weight by remember { mutableIntStateOf(0) }
    val pricePerKgMin = 5000 // Harga minimum per kg
    val pricePerKgMax = 50000 // Harga maksimum per kg

    val estimatedMinPrice = weight * pricePerKgMin
    val estimatedMaxPrice = weight * pricePerKgMax

    Card(
        modifier = Modifier
            .width(150.dp)
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.elektronik), // Ganti dengan ikon elektronik jika tersedia
                contentDescription = "Elektronik",
                modifier = Modifier.size(60.dp)
            )
            Text(
                text = "Elektronik",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                IconButton(onClick = {
                    if (weight > 0) {
                        weight--
                        onWeightChange(-1)
                    }
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.minus),
                        contentDescription = "Decrease",
                        tint = Color(0xFF55B3A4)
                    )
                }
                Text(
                    text = "$weight Kg",
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                IconButton(onClick = {
                    weight++
                    onWeightChange(1)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.plus),
                        contentDescription = "Increase",
                        tint = Color(0xFF55B3A4)
                    )
                }
            }

        }
    }
}

@Composable
fun ElektronikFooter(totalWeight: Int) {
    val pricePerKgMin = 5000
    val pricePerKgMax = 50000

    val estimatedMinPrice = totalWeight * pricePerKgMin
    val estimatedMaxPrice = totalWeight * pricePerKgMax

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .background(Color(0xFF55B3A4), shape = RoundedCornerShape(16.dp))
            .padding(vertical = 12.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Rp$estimatedMinPrice s.d Rp$estimatedMaxPrice",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
            Text(
                text = "Estimasi Harga Total",
                fontSize = 12.sp,
                color = Color.White
            )
        }
        Button(
            onClick = { /* Lanjutkan ke aktivitas berikutnya */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF55B3A4))
        ) {
            Text(text = "Lanjut", color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewElektronikScreen() {
    MobileAPPTheme {
        ElektronikScreen()
    }
}
