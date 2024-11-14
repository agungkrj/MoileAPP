@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mobileapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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

class EditProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileAPPTheme {
                EditProfileScreen()
            }
        }
    }
}

@Composable
fun EditProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        FullWidthHeader()  // Menggunakan header yang full width

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(vertical = 8.dp)
        ) {
            ProfilePictureSection()
            Spacer(modifier = Modifier.height(16.dp))
            ProfileInfoField("Nama Lengkap", "Agro")
            ProfileInfoField("Email", "username@gmail.com")
            ProfileInfoField("No Handphone", "081234567810")
            ProfileInfoField("Jenis Kelamin", "Laki - laki")
            ProfileInfoField("Tanggal Lahir", "27/10/2024")
            AddressField()
        }
    }
}

@Composable
fun FullWidthHeader() {
    TopAppBar(
        title = {
            Text(
                "Pengaturan Akun",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        },
        navigationIcon = {
            IconButton(onClick = {

            }) {
                Icon(
                    painter = painterResource(id = R.drawable.panah),
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF55B3A4)),
        modifier = Modifier.fillMaxWidth() // Menjadikan header full width
    )
}

@Composable
fun ProfilePictureSection() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(100.dp)
                    .background(Color(0xFF55B3A4), CircleShape)
                    .padding(8.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Edit Profile Picture",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { /* Edit action */ }
            )
        }
    }
}

@Composable
fun ProfileInfoField(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = label, fontSize = 14.sp, color = Color.Gray)
            Text(text = value, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        }
        Icon(
            painter = painterResource(id = R.drawable.pensil),
            contentDescription = "Edit $label",
            modifier = Modifier
                .size(24.dp)
                .clickable { /* Edit action */ }
        )
    }
    Divider(color = Color.LightGray, thickness = 1.dp)
}

@Composable
fun AddressField() {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(text = "Alamat", fontSize = 14.sp, color = Color.Gray)
        OutlinedTextField(
            value = "JL. Anggrek Sari perumahan Legenda Seluler Blok Z No. 192 RT.12/RW.11 Batam Center, Batam kota, Batam, Kepulauan Riau.",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.Gray
            )
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Tombol "Ganti Alamat" yang diperkecil
            Button(
                onClick = { /* Ganti Alamat action */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEEEEEE)),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .width(120.dp) // Sesuaikan lebar sesuai kebutuhan
                    .height(31.dp) // Tinggi sesuai dimensi yang diminta
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.pensil),
                    contentDescription = "Edit Icon",
                    modifier = Modifier.size(14.dp), // Ukuran ikon
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("Ganti Alamat", color = Color.Black, fontSize = 12.sp) // Ukuran teks
            }

            // Tombol "Batal" dan "Simpan" dengan tinggi dan lebar sesuai dimensi yang diminta
            Button(
                onClick = { /* Batal action */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .width(120.dp) // Sesuaikan lebar sesuai kebutuhan
                    .height(31.dp)
            ) {
                Text("Batal", color = Color.White)
            }

            Button(
                onClick = { /* Simpan action */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF55B3A4)),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .width(120.dp) // Sesuaikan lebar sesuai kebutuhan
                    .height(31.dp)
            ) {
                Text("Simpan", color = Color.White)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewEditProfileScreen() {
    MobileAPPTheme {
        EditProfileScreen()
    }
}
