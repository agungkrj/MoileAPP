package com.example.mobileapp.profile

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import coil.compose.rememberAsyncImagePainter
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

@Composable
fun ProfileScreen() {
    val context = LocalContext.current

    // Load data user dari SharedPreferences
    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    val name = sharedPreferences.getString("name", "Nama Pengguna") ?: "Nama Pengguna"
    val profileImageUri = sharedPreferences.getString("profile_image", null)

    var selectedScreen by remember { mutableStateOf("Profil") }
    var showLogoutDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(top = 24.dp, bottom = 16.dp)
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

            // Kartu Profil dengan data nama & foto
            ProfileCard(name = name, profileImageUri = profileImageUri)

            Spacer(modifier = Modifier.height(24.dp))

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
                onArrowClick = { showLogoutDialog = true }
            )
        }

        BottomNavigationBar(
            selectedItem = remember { mutableStateOf(selectedScreen) },
            onItemSelected = { selectedScreen = it },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }

    if (showLogoutDialog) {
        ConfirmLogoutDialog(
            onConfirm = {
                showLogoutDialog = false
                (context as ComponentActivity).finish()
            },
            onDismiss = { showLogoutDialog = false }
        )
    }
}

@Composable
fun ProfileCard(name: String, profileImageUri: String?) {
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
                        .clip(CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    if (profileImageUri != null) {
                        Image(
                            painter = rememberAsyncImagePainter(Uri.parse(profileImageUri)),
                            contentDescription = "Profile Picture",
                            modifier = Modifier.size(100.dp)
                        )
                    } else {
                        Icon(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = "Default Profile Picture",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(100.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = name,
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
