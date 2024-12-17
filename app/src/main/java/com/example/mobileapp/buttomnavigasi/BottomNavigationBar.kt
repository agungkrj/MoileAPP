package com.example.mobileapp.components

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobileapp.beranda.DashboardActivity
import com.example.mobileapp.order.OrderActivity
import com.example.mobileapp.profile.ProfileActivity
import com.example.mobileapp.R

@Composable
fun BottomNavigationBar(
    selectedItem: MutableState<String>,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier // Menambahkan parameter modifier
) {
    val context = LocalContext.current // Mendapatkan context di sini

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
                    selectedItem.value = "Beranda" // Set item terpilih
                    val intent = Intent(context, DashboardActivity::class.java)
                    context.startActivity(intent) // Navigasi ke DashboardActivity
                }
            )
            BottomNavigationItem(
                iconId = R.drawable.order,
                label = "Order",
                selected = selectedItem.value == "Order",
                onClick = {
                    selectedItem.value = "Order" // Set item terpilih
                    val intent = Intent(context, OrderActivity::class.java)
                    context.startActivity(intent) // Navigasi ke OrderActivity
                }
            )
            BottomNavigationItem(
                iconId = R.drawable.user,
                label = "Profil",
                selected = selectedItem.value == "Profil",
                onClick = {
                    selectedItem.value = "Profil" // Set item terpilih
                    val intent = Intent(context, ProfileActivity::class.java)
                    context.startActivity(intent) // Navigasi ke ProfileActivity
                }
            )
        }
    }
}

@Composable
fun BottomNavigationItem(
    iconId: Int,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
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

