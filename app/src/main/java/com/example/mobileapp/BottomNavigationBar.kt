package com.example.mobileapp

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomNavigationBar(
    selectedScreen: String,
    onItemSelected: (String) -> Unit,
    context: Context,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp) // Menyesuaikan padding luar
            .height(60.dp),
        shape = RoundedCornerShape(30.dp), // Membuat sudut melengkung
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp), // Membuat padding antar item lebih rapi
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavigationItem(
                iconId = R.drawable.home,
                label = "Beranda",
                selected = selectedScreen == "Beranda",
                onClick = {
                    onItemSelected("Beranda")
                    val intent = Intent(context, DashboardActivity::class.java)
                    context.startActivity(intent)
                }
            )
            BottomNavigationItem(
                iconId = R.drawable.order,
                label = "Order",
                selected = selectedScreen == "Order",
                onClick = {
                    onItemSelected("Order")
                    val intent = Intent(context, OrderActivity::class.java)
                    context.startActivity(intent)
                }
            )
            BottomNavigationItem(
                iconId = R.drawable.user,
                label = "Profil",
                selected = selectedScreen == "Profil",
                onClick = {
                    onItemSelected("Profil")
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
            .clip(RoundedCornerShape(50)) // Membuat background item melengkung
            .background(if (selected) Color(0xFF55B3A4) else Color.Transparent) // Warna latar belakang untuk item yang dipilih
            .clickable { onClick() }
            .padding(horizontal = 24.dp, vertical = 8.dp) // Menambah padding agar lebih lebar
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = label,
            tint = if (selected) Color.White else Color.Black,
            modifier = Modifier.size(20.dp) // Ukuran ikon
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            color = if (selected) Color.White else Color.Black
        )
    }
}
