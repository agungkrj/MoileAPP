package com.example.mobileapp.profile

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobileapp.MainActivity
import com.example.mobileapp.R
import com.example.mobileapp.components.BottomNavigationBar
import com.example.mobileapp.ui.theme.MobileAPPTheme

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileAPPTheme {
                ProfileScreen()
            }
        }
    }
}

// Di dalam fungsi ProfileScreen
@Composable
fun ProfileScreen() {
    val context = LocalContext.current
    var selectedScreen by remember { mutableStateOf("Profil") }
    var showLogoutDialog by remember { mutableStateOf(false) }

    // Layout utama
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)) // Warna background abu-abu terang
            .padding(top = 24.dp, bottom = 16.dp) // Padding atas dan bawah
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header
            Text(
                text = "Akun",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Kartu Profil
            ProfileCard()

            Spacer(modifier = Modifier.height(24.dp)) // Jarak antar elemen

            // Kartu Menu
            ProfileMenuCard(
                iconRes = R.drawable.user,
                title = "Akun Saya",
                subtitle = "Ubah data akun anda",
                showEditIcon = true,
                onEditClick = {
                    val intent = Intent(context, EditProfileActivity::class.java)
                    context.startActivity(intent)
                },
                onArrowClick = {
                    val intent = Intent(context, EditProfileActivity::class.java)
                    context.startActivity(intent)
                }
            )
            ProfileMenuCard(
                iconRes = R.drawable.exit,
                title = "Keluar",
                onArrowClick = { showLogoutDialog = true } // Memunculkan dialog keluar
            )
        }

        // Bottom Navigation
        BottomNavigationBar(
            selectedItem = remember { mutableStateOf(selectedScreen) },
            onItemSelected = { selectedScreen = it },
            modifier = Modifier
                .align(Alignment.BottomCenter) // Posisi di bawah
                .padding(horizontal = 16.dp, vertical = 8.dp) // Padding
        )
    }

    // Dialog untuk konfirmasi keluar
    if (showLogoutDialog) {
        ConfirmLogoutDialog(
            onConfirm = {
                showLogoutDialog = false
                // Logika keluar aplikasi, bisa tambahkan di sini
                (context as ComponentActivity).finish()
            },
            onDismiss = { showLogoutDialog = false }
        )
    }
}


@Composable
fun ConfirmLogoutDialog(onConfirm: () -> Unit, onDismiss: () -> Unit) {
    val context = LocalContext.current // Mengambil konteks lokal

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.warning),
                    contentDescription = null,
                    tint = Color(0xFF4CAF50),
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Keluar",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4CAF50)
                )
            }
        },
        text = {
            Text(
                text = "Apakah anda yakin ingin keluar?",
                fontSize = 16.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirm() // Eksekusi logika yang dikirimkan
                    val intent = Intent(context, MainActivity::class.java) // Pindah ke MainActivity
                    context.startActivity(intent)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Keluar", color = Color.White)
            }
        },
        dismissButton = {
            OutlinedButton(
                onClick = { onDismiss() },
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF4CAF50)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Batal")
            }
        },
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    )
}


@Composable
fun ProfileCard() {
    Card(
        modifier = Modifier
            .width(374.dp)
            .height(242.dp)
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF55B3A4)),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color(0xFF55B3A4), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape),
                        tint = Color.Unspecified
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Agro",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .background(Color(0xFF4CAF50), RoundedCornerShape(12.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "Kuy Point",
                        fontSize = 12.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.koin),
                        contentDescription = "Koin Icon",
                        tint = Color.Yellow,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "15.000",
                        fontSize = 12.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun ProfileMenuCard(
    iconRes: Int,
    title: String,
    subtitle: String? = null,
    showEditIcon: Boolean = false,
    onEditClick: (() -> Unit)? = null,
    onArrowClick: (() -> Unit)? = null
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = title,
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = title, fontSize = 16.sp, color = Color.Black)
                if (subtitle != null) {
                    Text(text = subtitle, fontSize = 12.sp, color = Color.Gray)
                }
            }
            if (showEditIcon) {
                Icon(
                    painter = painterResource(id = R.drawable.pensil),
                    contentDescription = "Edit Icon",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { onEditClick?.invoke() }
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.row),
                    contentDescription = "Arrow Icon",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { onArrowClick?.invoke() }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    MobileAPPTheme {
        ProfileScreen()
    }
}
