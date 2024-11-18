@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mobileapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
    // State untuk data pengguna
    var name by remember { mutableStateOf("Agro") }
    var email by remember { mutableStateOf("username@gmail.com") }
    var phone by remember { mutableStateOf("081234567810") }
    var gender by remember { mutableStateOf("Laki - laki") }
    var birthDate by remember { mutableStateOf("27/10/2024") }
    var address by remember { mutableStateOf("JL. Anggrek Sari perumahan Legenda Seluler Blok Z No. 192 RT.12/RW.11 Batam Center, Batam kota, Batam, Kepulauan Riau.") }

    // State untuk data asli (salinan) - digunakan untuk tombol "Batal"
    var originalName by remember { mutableStateOf(name) }
    var originalEmail by remember { mutableStateOf(email) }
    var originalPhone by remember { mutableStateOf(phone) }
    var originalGender by remember { mutableStateOf(gender) }
    var originalBirthDate by remember { mutableStateOf(birthDate) }
    var originalAddress by remember { mutableStateOf(address) }

    // State untuk mengontrol mode edit
    var isEditing by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        FullWidthHeader()

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            ProfilePictureSection()

            Spacer(modifier = Modifier.height(16.dp))

            // Informasi pengguna
            ProfileInfoField("Nama Lengkap", name, isEditing) { name = it }
            ProfileInfoField("Email", email, isEditing) { email = it }
            ProfileInfoField("No Handphone", phone, isEditing) { phone = it }
            ProfileInfoField("Jenis Kelamin", gender, isEditing) { gender = it }
            ProfileInfoField("Tanggal Lahir", birthDate, isEditing) { birthDate = it }
            ProfileInfoField("Alamat", address, isEditing) { address = it }

            Spacer(modifier = Modifier.height(16.dp))

            // Tombol aksi
            if (isEditing) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Tombol "Batal"
                    Button(
                        onClick = {
                            // Mengembalikan nilai asli
                            name = originalName
                            email = originalEmail
                            phone = originalPhone
                            gender = originalGender
                            birthDate = originalBirthDate
                            address = originalAddress
                            isEditing = false
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp)
                            .height(50.dp)
                    ) {
                        Text("Batal", color = Color.White, fontSize = 16.sp)
                    }

                    // Tombol "Simpan"
                    Button(
                        onClick = {
                            // Menyimpan data terbaru
                            originalName = name
                            originalEmail = email
                            originalPhone = phone
                            originalGender = gender
                            originalBirthDate = birthDate
                            originalAddress = address
                            isEditing = false
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF55B3A4)),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp)
                            .height(50.dp)
                    ) {
                        Text("Simpan", color = Color.White, fontSize = 16.sp)
                    }
                }
            } else {
                // Tombol "Edit"
                Button(
                    onClick = { isEditing = true },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEEEEEE)),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text("Edit", color = Color.Black, fontSize = 16.sp)
                }
            }
        }
    }
}

@Composable
fun FullWidthHeader() {
    val context = LocalContext.current
    TopAppBar(
        title = {
            Text(
                "Pengaturan Akun",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            IconButton(onClick = {
                val intent = Intent(context, DashboardActivity::class.java)
                context.startActivity(intent)
            }) {
            Icon(
                painter = painterResource(id = R.drawable.panah),
                contentDescription = "Back",
                tint = Color.Black
            )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF55B3A4)),
        modifier = Modifier.fillMaxWidth() // Header memenuhi lebar layar
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
        }
    }
}

@Composable
fun ProfileInfoField(
    label: String,
    value: String,
    isEditing: Boolean,
    onValueChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(text = label, fontSize = 14.sp, color = Color.Gray)
        if (isEditing) {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.Gray
                )
            )
        } else {
            Text(text = value, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
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
