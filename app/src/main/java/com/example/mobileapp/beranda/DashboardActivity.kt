package com.example.mobileapp.beranda

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobileapp.profile.ProfileActivity
import com.example.mobileapp.R
import com.example.mobileapp.kuypoint.TukarKuyPointActivity
import com.example.mobileapp.order.OrderActivity
import com.example.mobileapp.ui.theme.MobileAPPTheme
import com.example.mobileapp.yukangkut.AngkutActivity
import com.example.mobileapp.yukbuang.BuangActivity

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileAPPTheme {
                DashboardScreen()
            }
        }
    }
}

@Composable
fun DashboardScreen() {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val selectedItem = remember { mutableStateOf("Beranda") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        BackgroundOverlay()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Pilah Yuk!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(top = 64.dp, bottom = 16.dp)
            )

            GreetingCard()

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ActionCard(
                    iconRes = R.drawable.yukangkut,
                    title = "Yuk Angkut!",
                    description = "Sampahmu akan dijemput",
                    backgroundColor = Color(0xFFC3EAE3),
                    onClick = {
                        val intent = Intent(context, AngkutActivity::class.java)
                        context.startActivity(intent)
                    },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                ActionCard(
                    iconRes = R.drawable.yukbuang,
                    title = "Yuk Buang!",
                    description = "Buang sampahmu langsung",
                    backgroundColor = Color(0xFFFFF3E0),
                    onClick = {
                        val intent = Intent(context, BuangActivity::class.java)
                        context.startActivity(intent)
                    },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            RedeemPointsCard(onClick = {
                val intent = Intent(context, TukarKuyPointActivity::class.java)
                context.startActivity(intent)
            })

            Spacer(modifier = Modifier.height(24.dp))
        }

        BottomNavigationBar(
            selectedItem = selectedItem,
            onItemSelected = { item ->
                selectedItem.value = item
                when (item) {
                    "Beranda" -> {}
                    "Order" -> {
                        val intent = Intent(context, OrderActivity::class.java)
                        context.startActivity(intent)
                    }
                    "Profil" -> {
                        val intent = Intent(context, ProfileActivity::class.java)
                        context.startActivity(intent)
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 20.dp, vertical = 24.dp) // Adjusted padding for elevation
        )
    }
}

@Composable
fun BackgroundOverlay() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        Image(
            painter = painterResource(id = R.drawable.bagrond),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentScale = ContentScale.Crop
        )
    }
}
@Composable
fun GreetingCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        val context = LocalContext.current
        Column(modifier = Modifier.padding(16.dp)) {
            // Bagian Atas: Profil
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Icon Profile
                IconButton(
                    onClick = {
                        val intent = Intent(context, ProfileActivity::class.java)
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Profile Picture",
                        modifier = Modifier.size(48.dp),
                        tint = Color.Unspecified
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                // Teks Profil
                Column {
                    Text(
                        text = "Halo, Agro!",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Text(
                        text = "Sudah buang sampah hari ini?",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }

            // Garis Pemisah
            Spacer(modifier = Modifier.height(16.dp))
            Divider(color = Color(0xFFCCCCCC), thickness = 1.dp)
            Spacer(modifier = Modifier.height(16.dp))

            // Bagian Bawah: Kuy Point
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Kuy Point Label dan Ikon
                Column {
                    Text(
                        text = "Kuy Point",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.koin),
                            contentDescription = "Coin Icon",
                            modifier = Modifier.size(32.dp),
                            tint = Color.Unspecified
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "15.000",
                            fontWeight = FontWeight.Bold,
                            fontSize = 28.sp,
                            color = Color(0xFFDAA520) // Warna emas
                        )
                    }
                }

                // Tombol Tukar di Sebelah Kanan Bawah
                Button(
                    onClick = {
                        val intent = Intent(context, TukarKuyPointActivity::class.java)
                        context.startActivity(intent)
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF55B3A4),
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .width(120.dp) // Lebar tombol tetap besar
                        .height(48.dp) // Tinggi tombol
                ) {
                    Text(
                        text = "Tukar",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun ActionCard(
    iconRes: Int,
    title: String,
    description: String,
    backgroundColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(177.68.dp)
            .height(210.dp)
            .padding(horizontal = 4.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = title,
                modifier = Modifier.size(width = 95.07.dp, height = 69.16.dp),
                tint = Color.Unspecified
            )
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = description,
                fontSize = 12.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
    }
}
@Composable
fun RedeemPointsCard(onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth() // Ensures the card spans the full width
            .padding(horizontal = 8.dp) // Matches other card paddings
            .height(140.dp) // Increased height for emphasis
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF00BFA5), Color(0xFFFFE082)) // Gradient colors
                    )
                )
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start, // Keeps icon and text aligned
                modifier = Modifier.fillMaxSize()
            ) {
                // Gift Icon
                Image(
                    painter = painterResource(id = R.drawable.gift),
                    contentDescription = "Gift Icon",
                    modifier = Modifier
                        .size(100.dp) // Adjusted icon size for a balanced layout
                        .padding(end = 16.dp)
                )

                // Text Section
                Text(
                    text = "Ayo tukar KuyPoint kamu!",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp, // Bold title with a slightly larger size
                    color = Color.White,
                    modifier = Modifier.padding(start = 8.dp) // Adds consistent spacing
                )
            }
        }
    }
}


@Composable
fun BottomNavigationBar(
    selectedItem: MutableState<String>,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(30.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavigationItem(
                iconId = R.drawable.home,
                label = "Beranda",
                selected = selectedItem.value == "Beranda",
                onClick = {
                    selectedItem.value = "Beranda"
                    val intent = Intent(context, DashboardActivity::class.java)
                    context.startActivity(intent)
                }
            )
            BottomNavigationItem(
                iconId = R.drawable.order,
                label = "Order",
                selected = selectedItem.value == "Order",
                onClick = {
                    selectedItem.value = "Order"
                    val intent = Intent(context, OrderActivity::class.java)
                    context.startActivity(intent)
                }
            )
            BottomNavigationItem(
                iconId = R.drawable.user,
                label = "Profil",
                selected = selectedItem.value == "Profil",
                onClick = {
                    selectedItem.value = "Profil"
                    val intent = Intent(context, ProfileActivity::class.java)
                    context.startActivity(intent)
                }
            )
        }
    }
}

@Composable
fun BottomNavigationItem(iconId: Int, label: String, selected: Boolean, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(if (selected) Color(0xFF55B3A4) else Color.Transparent)
            .clickable { onClick() }
            .padding(horizontal = if (selected) 36.dp else 12.dp, vertical = 2.dp)
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = label,
            tint = if (selected) Color.White else Color.Black,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            color = if (selected) Color.White else Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDashboardScreen() {
    MobileAPPTheme {
        DashboardScreen()
    }
}
