package com.example.mobileapp

import android.content.Intent
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
import com.example.mobileapp.ui.theme.MobileAPPTheme

class BotolKacaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileAPPTheme {
                BotolKacaScreen()
            }
        }
    }
}

@Composable
fun BotolKacaScreen() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp)
    ) {
        BotolKacaHeaderSection()

        Spacer(modifier = Modifier.height(8.dp))

        // Centered Card for Botol Kaca Icon and Name
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            BotolKacaIconCard()
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sub Jenis Botol Kaca Section
        Text(
            text = "Sub Jenis Botol Kaca",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        val subJenisBotolKaca = listOf(
            "Botol Bekas Minuman", "Botol Bekas Parfum", "Botol Kaca Warna",
            "Gelas Kaca", "Piring Kaca", "Kaca Pecah"
        )

        // State for each item checkbox
        val checkedStates = remember { mutableStateListOf(*Array(subJenisBotolKaca.size) { false }) }

        subJenisBotolKaca.forEachIndexed { index, item ->
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

        // Footer with Estimated Price and Next Button
        FooterSection()
    }
}

@Composable
fun BotolKacaHeaderSection() {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        IconButton(onClick = {
            val intent = Intent(context, AngkutActivity::class.java)
            context.startActivity(intent)
        }) {
            Icon(
                painter = painterResource(id = R.drawable.panah),
                contentDescription = "Back",
                tint = Color.Black
            )
        }
        Text(
            text = "Botol Kaca",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Composable
fun BotolKacaIconCard() {
    // State for weight value
    var weight by remember { mutableIntStateOf(0) }

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
                painter = painterResource(id = R.drawable.kaca), // Ganti dengan ikon botol kaca jika tersedia
                contentDescription = "Botol Kaca",
                modifier = Modifier.size(60.dp)
            )
            Text(
                text = "Botol Kaca",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                IconButton(onClick = { if (weight > 0) weight-- }) {
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
                IconButton(onClick = { weight++ }) {
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

@Preview(showBackground = true)
@Composable
fun PreviewBotolKacaScreen() {
    MobileAPPTheme {
        BotolKacaScreen()
    }
}
