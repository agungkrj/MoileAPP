@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mobileapp.profile

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.example.mobileapp.network.RetrofitInstance
import com.example.mobileapp.network.com.example.mobileapp.profile.UserProfileData
import com.example.mobileapp.ui.theme.MobileAPPTheme
import kotlinx.coroutines.launch

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
    val context = LocalContext.current

    // State for user data
    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    var name by remember { mutableStateOf(sharedPreferences.getString("name", "") ?: "") }
    var email by remember { mutableStateOf(sharedPreferences.getString("email", "") ?: "") }
    var phone by remember { mutableStateOf(sharedPreferences.getString("phone", "") ?: "") }
    var gender by remember { mutableStateOf(sharedPreferences.getString("gender", "") ?: "") }
    var birthDate by remember { mutableStateOf(sharedPreferences.getString("birthDate", "") ?: "") }
    var address by remember { mutableStateOf(sharedPreferences.getString("address", "") ?: "") }
    var profileImageUri by remember {
        mutableStateOf(sharedPreferences.getString("profile_image", null)?.let { Uri.parse(it) })
    }

    // State to control UI
    var isEditing by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()

    // Get Token
    val token = sharedPreferences.getString("auth_token", null)

    if (token == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Token is missing! Please login again.", color = Color.Red)
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        FullWidthHeader()

        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            ProfilePictureSection(profileImageUri) { uri ->
                profileImageUri = uri
                saveImageToPreferences(context, uri)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // User Information
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

            // Action Buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (isEditing) {
                    Button(
                        onClick = { isEditing = false },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                    ) {
                        Text("Batal", color = Color.White, fontSize = 16.sp)
                    }

                    Button(
                        onClick = {
                            coroutineScope.launch {
                                isLoading = true
                                try {
                                    val response = RetrofitInstance.api.updateUserProfile(
                                        token = "Bearer $token",
                                        userProfile = UserProfileData(
                                            name = name,
                                            email = email,
                                            phone = phone,
                                            gender = gender,
                                            birthDate = birthDate,
                                            address = address
                                        )
                                    )
                                    if (response.isSuccessful) {
                                        saveUpdatedDataToPreferences(context, name, email, phone, gender, birthDate, address)
                                        Toast.makeText(context, "Profil berhasil diperbarui", Toast.LENGTH_SHORT).show()
                                        isEditing = false
                                    } else {
                                        errorMessage = "Gagal memperbarui profil."
                                    }
                                } catch (e: Exception) {
                                    errorMessage = "Error: ${e.message}"
                                } finally {
                                    isLoading = false
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF55B3A4)),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(color = Color.White)
                        } else {
                            Text("Simpan", color = Color.White, fontSize = 16.sp)
                        }
                    }
                } else {
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
}

fun saveUpdatedDataToPreferences(
    context: Context,
    name: String,
    email: String,
    phone: String,
    gender: String,
    birthDate: String,
    address: String
) {
    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    with(sharedPreferences.edit()) {
        putString("name", name)
        putString("email", email)
        putString("phone", phone)
        putString("gender", gender)
        putString("birthDate", birthDate)
        putString("address", address)
        apply()
    }
}

fun saveImageToPreferences(context: Context, uri: Uri) {
    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    with(sharedPreferences.edit()) {
        putString("profile_image", uri.toString())
        apply()
    }
}

@Composable
fun FullWidthHeader() {
    val context = LocalContext.current
    TopAppBar(
        title = { Text("Pengaturan Akun", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White) },
        navigationIcon = {
            IconButton(onClick = { context.startActivity(Intent(context, ProfileActivity::class.java)) }) {
                Icon(painter = painterResource(id = R.drawable.panah), contentDescription = "Back", tint = Color.White)
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF55B3A4))
    )
}

@Composable
fun ProfilePictureSection(profileImageUri: Uri?, onImageSelected: (Uri) -> Unit) {
    val imagePickerLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let { onImageSelected(it) }
    }
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = profileImageUri?.let { rememberAsyncImagePainter(it) }
                ?: painterResource(id = R.drawable.profile),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .clickable { imagePickerLauncher.launch("image/*") }
        )
    }
}

@Composable
fun ProfileInfoField(label: String, value: String, isEditing: Boolean, onValueChange: (String) -> Unit) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(text = label, fontSize = 14.sp, color = Color.Gray)
        if (isEditing) {
            OutlinedTextField(value = value, onValueChange = onValueChange, modifier = Modifier.fillMaxWidth())
        } else {
            Text(text = value, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEditProfileScreen() {
    EditProfileScreen()
}
