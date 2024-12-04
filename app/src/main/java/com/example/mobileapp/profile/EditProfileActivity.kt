@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mobileapp.profile

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.mobileapp.R
import com.example.mobileapp.beranda.DashboardActivity
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
    // State for user data
    var name by remember { mutableStateOf("Agro") }
    var email by remember { mutableStateOf("username@gmail.com") }
    var phone by remember { mutableStateOf("081234567810") }
    var gender by remember { mutableStateOf("Laki - laki") }
    var birthDate by remember { mutableStateOf("27/10/2024") }
    var address by remember { mutableStateOf("JL. Anggrek Sari perumahan Legenda Seluler Blok Z No. 192 RT.12/RW.11 Batam Center, Batam kota, Batam, Kepulauan Riau.") }

    // State for original data (used for the "Batal" button)
    var originalName by remember { mutableStateOf(name) }
    var originalEmail by remember { mutableStateOf(email) }
    var originalPhone by remember { mutableStateOf(phone) }
    var originalGender by remember { mutableStateOf(gender) }
    var originalBirthDate by remember { mutableStateOf(birthDate) }
    var originalAddress by remember { mutableStateOf(address) }

    // State to control edit mode
    var isEditing by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        FullWidthHeader()

        Spacer(modifier = Modifier.height(8.dp))

        // Scrollable Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()) // Enable scrolling
                .padding(horizontal = 16.dp)
        ) {
            ProfilePictureSection()

            Spacer(modifier = Modifier.height(16.dp))

            // User information fields
            ProfileInfoField("Nama Lengkap", name, isEditing) { name = it }
            Divider(color = Color.LightGray, thickness = 1.dp)

            ProfileInfoField("Email", email, isEditing) { email = it }
            Divider(color = Color.LightGray, thickness = 1.dp)

            ProfileInfoField("No Handphone", phone, isEditing) { phone = it }
            Divider(color = Color.LightGray, thickness = 1.dp)

            ProfileInfoField("Jenis Kelamin", gender, isEditing) { gender = it }
            Divider(color = Color.LightGray, thickness = 1.dp)

            ProfileInfoField("Tanggal Lahir", birthDate, isEditing) { birthDate = it }
            Divider(color = Color.LightGray, thickness = 1.dp)

            ProfileInfoField("Alamat", address, isEditing) { address = it }
            Divider(color = Color.LightGray, thickness = 1.dp)

            Spacer(modifier = Modifier.height(16.dp))

            // Action buttons
            if (isEditing) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp) // Consistent spacing
                ) {
                    // "Batal" button
                    Button(
                        onClick = {
                            // Restore original values
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
                            .weight(1f) // Equal width for both buttons
                            .height(50.dp)
                    ) {
                        Text("Batal", color = Color.White, fontSize = 16.sp)
                    }

                    // "Simpan" button
                    Button(
                        onClick = {
                            // Save updated values
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
                            .weight(1f) // Equal width for both buttons
                            .height(50.dp)
                    ) {
                        Text("Simpan", color = Color.White, fontSize = 16.sp)
                    }
                }
            } else {
                // "Edit" button
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
        },
        navigationIcon = {
            IconButton(onClick = {
                val intent = Intent(context, ProfileActivity::class.java)
                context.startActivity(intent)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.panah),
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF55B3A4)),
        modifier = Modifier.fillMaxWidth() // Header spans the entire screen width
    )
}

@Composable
fun ProfilePictureSection() {
    val context = LocalContext.current

    // State to hold the image URI
    var imageUri by remember { mutableStateOf<android.net.Uri?>(null) }

    // Launcher to pick an image
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: android.net.Uri? ->
            if (uri != null) {
                imageUri = uri // Update state with the new image URI
            }
        }
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Profile Picture
            Box(contentAlignment = Alignment.BottomEnd) {
                if (imageUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(imageUri),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF55B3A4), CircleShape)
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.profile), // Default image
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF55B3A4), CircleShape)
                    )
                }

                // Pencil Icon for editing
                Icon(
                    painter = painterResource(id = R.drawable.pensil), // Replace with your pencil icon
                    contentDescription = "Edit Profile Picture",
                    modifier = Modifier
                        .size(26.dp)
                        .background(Color.Transparent, CircleShape)
                        .padding(4.dp)
                        .clickable {
                            launcher.launch("image/*") // Open gallery
                        },
                    tint = Color.Black
                )
            }
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
