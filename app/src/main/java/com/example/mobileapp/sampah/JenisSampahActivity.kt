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

class JenisSampahActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileAPPTheme {
                JenisSampahScreen()
            }
        }
    }
}

@Composable
fun JenisSampahScreen() {
    val scrollState = rememberScrollState() // Menyimpan status scroll
    var totalWeight by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp)
    ) {
        JenisSampahHeaderSection()

        Spacer(modifier = Modifier.height(8.dp))

        // Centered Card for Jenis Sampah Icon and Name
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            JenisSampahIconCard(onWeightChange = { totalWeight += it })
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sub Jenis Sampah Section
        Text(
            text = "Sub Jenis Sampah",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        val subJenisSampah = listOf(
            "Koran", "Buku Bekas", "Kertas Putih / HVS",
            "Kertas Warna / Duplek", "Kertas Buram", "Karton", "Kertas Lainnya"
        )

        val checkedStates = remember { mutableStateListOf(*Array(subJenisSampah.size) { false }) }

        subJenisSampah.forEachIndexed { index, item ->
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

        FooterSection(totalWeight = totalWeight)
    }
}

@Composable
fun JenisSampahHeaderSection() {
    val context = LocalContext.current // Dapatkan context dari LocalContext

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
            text = "Jenis Sampah",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Composable
fun JenisSampahIconCard(onWeightChange: (Int) -> Unit) {
    var weight by remember { mutableIntStateOf(0) }
    val pricePerKgMin = 1000
    val pricePerKgMax = 2500

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
                painter = painterResource(id = R.drawable.kertas),
                contentDescription = "Kertas",
                modifier = Modifier.size(60.dp)
            )
            Text(
                text = "Kertas",
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
fun FooterSection(totalWeight: Int) {
    val context = LocalContext.current
    val pricePerKgMin = 1000
    val pricePerKgMax = 2500

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
            onClick = {
                (context as? ComponentActivity)?.finish()
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF55B3A4))
        ) {
            Text(text = "Lanjut", color = Color.White)
        }
    }
}

@Preview(showBackground = true )
@Composable
fun PreviewJenisSampahScreen() {
    MobileAPPTheme {
        JenisSampahScreen()
    }
}
