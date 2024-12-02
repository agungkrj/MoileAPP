package com.example.mobileapp.yukangkut

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
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import coil.compose.rememberAsyncImagePainter
import com.example.mobileapp.sampah.AluminiumActivity
import com.example.mobileapp.sampah.BesiActivity
import com.example.mobileapp.sampah.BotolKacaActivity
import com.example.mobileapp.beranda.DashboardActivity
import com.example.mobileapp.sampah.ElektronikActivity
import com.example.mobileapp.sampah.JenisSampahActivity
import com.example.mobileapp.sampah.PlastikActivity
import com.example.mobileapp.R
import com.example.mobileapp.ui.theme.MobileAPPTheme
import java.io.File

class AngkutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileAPPTheme {
                AngkutScreen()
            }
        }
    }
}

@Composable
fun AngkutScreen() {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    // State untuk formulir
    val phoneState = remember { mutableStateOf("") }
    val addressState = remember { mutableStateOf("") }
    val dateState = remember { mutableStateOf("") }
    val timeState = remember { mutableStateOf("") }
    val additionalMessageState = remember { mutableStateOf("") }
    val errorMessageState = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
    ) {
        AngkutHeaderSection()

        Spacer(modifier = Modifier.height(16.dp))

        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                text = "Jenis Sampah",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Pilih jenis dan masukkan perkiraan berat sampah.",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            SampahOptions()

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Informasi Penjemputan",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            PenjemputanForm(
                phoneState = phoneState,
                addressState = addressState,
                dateState = dateState,
                timeState = timeState,
                errorMessageState = errorMessageState
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Pesan Tambahan",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            CustomTextField(
                label = "Masukkan pesan tambahan di sini (opsional)",
                textState = additionalMessageState,
                isMultiline = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Foto Sampah",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                FotoSampahSection()
                FotoSampahSection()
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (phoneState.value.isEmpty() || addressState.value.isEmpty() ||
                        dateState.value.isEmpty() || timeState.value.isEmpty()
                    ) {
                        errorMessageState.value = "Semua field wajib diisi"
                    } else {
                        errorMessageState.value = ""
                        val intent = Intent(context, PembayaranActivity::class.java)
                        context.startActivity(intent)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF55B3A4)
                )
            ) {
                Text(text = "Selanjutnya", color = Color.White)
            }

            if (errorMessageState.value.isNotEmpty()) {
                Text(
                    text = errorMessageState.value,
                    color = Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Composable
fun AngkutHeaderSection() {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(88.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFF79D7C7), Color(0xFF52AC9D))
                )
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = {
                val intent = Intent(context, DashboardActivity::class.java)
                context.startActivity(intent)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.panah),
                    contentDescription = "Back",
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp) // Sesuaikan ukuran sesuai kebutuhan
                )

            }
            Spacer(modifier = Modifier.width(8.dp))
            Column{
                Text(
                    text = "Yuk Angkut!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Pesan sekarang, sampah mu langsung diangkut!",
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }

        }
    }
}

@Composable
fun SampahOptions() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            KertasCard()
            PlastikCard()
            BotolKacaCard()
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ElektronikCard()
            BesiCard()
            AluminiumCard()
        }
    }
}

@Composable
fun KertasCard() {
    val context = LocalContext.current
    SampahActionCard(
        name = "Kertas",
        iconRes = R.drawable.kertas,
        color = Color(0xFF4CAF50)
    ) {
        val intent = Intent(context, JenisSampahActivity::class.java)
        context.startActivity(intent)
    }
}

@Composable
fun PlastikCard() {
    val context = LocalContext.current
    SampahActionCard(
        name = "Plastik",
        iconRes = R.drawable.plastik,
        color = Color(0xFFFF9800)
    ) {
        val intent = Intent(context, PlastikActivity::class.java)
        context.startActivity(intent)
    }
}

@Composable
fun BotolKacaCard() {
    val context = LocalContext.current
    SampahActionCard(
        name = "Botol Kaca",
        iconRes = R.drawable.kaca,
        color = Color(0xFF2196F3)
    ) {
        val intent = Intent(context, BotolKacaActivity::class.java)
        context.startActivity(intent)
    }
}

@Composable
fun ElektronikCard() {
    val context = LocalContext.current
    SampahActionCard(
        name = "Elektronik",
        iconRes = R.drawable.elektronik,
        color = Color(0xFFF44336)
    ) {
        val intent = Intent(context, ElektronikActivity::class.java)
        context.startActivity(intent)
    }
}

@Composable
fun BesiCard() {
    val context = LocalContext.current
    SampahActionCard(
        name = "Besi",
        iconRes = R.drawable.besi,
        color = Color(0xFF607D8B)
    ) {
        val intent = Intent(context, BesiActivity::class.java)
        context.startActivity(intent)
    }
}

@Composable
fun AluminiumCard() {
    val context = LocalContext.current
    SampahActionCard(
        name = "Aluminium",
        iconRes = R.drawable.alumanium1,
        color = Color(0xFF9C27B0)
    ) {
        val intent = Intent(context, AluminiumActivity::class.java)
        context.startActivity(intent)
    }
}

@Composable
fun SampahActionCard(name: String, iconRes: Int, color: Color, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(100.dp)
            .height(120.dp)
            .padding(4.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        // Menyatukan konten ke tengah
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally, // Konten horisontal di tengah
            verticalArrangement = Arrangement.Center // Konten vertikal di tengah
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = name,
                modifier = Modifier.size(40.dp), // Ikon dengan ukuran lebih besar jika diperlukan
                colorFilter = ColorFilter.tint(color)
            )
            Spacer(modifier = Modifier.height(8.dp)) // Spasi antara ikon dan teks
            Text(
                text = name,
                fontSize = 14.sp, // Font sedikit lebih besar agar terlihat jelas
                fontWeight = FontWeight.Bold, // Teks dibuat lebih tebal
                color = Color.Black,
                textAlign = TextAlign.Center,
                maxLines = 1 // Membatasi teks ke satu baris
            )
        }
    }
}

@Composable
fun PenjemputanForm(
    phoneState: MutableState<String>,
    addressState: MutableState<String>,
    dateState: MutableState<String>,
    timeState: MutableState<String>,
    errorMessageState: MutableState<String>
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        CustomTextField(
            label = "No. Ponsel :",
            textState = phoneState
        )
        if (phoneState.value.isEmpty()) {
            Text(
                text = "Nomor ponsel wajib diisi!",
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        CustomTextField(
            label = "Alamat :",
            textState = addressState,
            isMultiline = true
        )
        if (addressState.value.isEmpty()) {
            Text(
                text = "Alamat wajib diisi!",
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        CustomTextField(
            label = "Tanggal :",
            textState = dateState
        )
        if (dateState.value.isEmpty()) {
            Text(
                text = "Tanggal wajib diisi!",
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        CustomTextField(
            label = "Waktu :",
            textState = timeState
        )
        if (timeState.value.isEmpty()) {
            Text(
                text = "Waktu wajib diisi!",
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}

@Composable
fun FotoSampahSection() {
    FotoSampahCard(label = "Foto Sampah")
}


@Composable
fun FotoSampahCard(label: String) {
    val context = LocalContext.current

    // State untuk menyimpan URI foto
    val photoUri = remember { mutableStateOf<Uri?>(null) }
    val cacheFile = remember { mutableStateOf<File?>(null) }

    // State untuk dialog pilihan (kamera atau galeri)
    val showDialog = remember { mutableStateOf(false) }

    // Launcher untuk membuka kamera
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && cacheFile.value != null) {
            photoUri.value = FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                cacheFile.value!!
            )
            Toast.makeText(context, "Foto berhasil diambil!", Toast.LENGTH_SHORT).show()
        } else {
            cacheFile.value?.let { file ->
                if (file.exists()) file.delete()
            }
            photoUri.value = null
            Toast.makeText(context, "Pengambilan foto dibatalkan.", Toast.LENGTH_SHORT).show()
        }
    }

    // Launcher untuk membuka galeri
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            photoUri.value = uri
            Toast.makeText(context, "Gambar berhasil dipilih!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Tidak ada gambar yang dipilih.", Toast.LENGTH_SHORT).show()
        }
    }

    // Desain kartu
    Card(
        modifier = Modifier
            .size(120.dp) // Ukuran kartu 120x120 dp
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F7F7)),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            // Jika ada foto, tampilkan foto
            photoUri.value?.let { uri ->
                Image(
                    painter = rememberAsyncImagePainter(model = uri),
                    contentDescription = "Captured Photo",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp))
                )
            } ?: run {
                // Jika belum ada foto, tampilkan ikon kamera
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.kamera),
                        contentDescription = "Foto Sampah",
                        tint = Color.Gray,
                        modifier = Modifier
                            .size(40.dp)
                            .clickable {
                                showDialog.value = true // Tampilkan dialog pilihan
                            }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Ambil foto",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }

    // Dialog untuk memilih kamera atau galeri
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text(text = "Pilih Sumber Gambar") },
            text = {
                Text(text = "Pilih untuk mengambil gambar melalui kamera atau memilih dari galeri.")
            },
            confirmButton = {
                TextButton(onClick = {
                    showDialog.value = false
                    // Buat file cache untuk kamera
                    val file = File(context.cacheDir, "${label.replace(" ", "_")}_photo.jpg")
                    cacheFile.value = file
                    val uri = FileProvider.getUriForFile(
                        context,
                        "${context.packageName}.fileprovider",
                        file
                    )
                    cameraLauncher.launch(uri) // Buka kamera
                }) {
                    Text("Kamera")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDialog.value = false
                    galleryLauncher.launch("image/*") // Buka galeri
                }) {
                    Text("Galeri")
                }
            }
        )
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(label: String, textState: MutableState<String>, isMultiline: Boolean = false) {
    OutlinedTextField(
        value = textState.value,
        onValueChange = { textState.value = it },
        label = { Text(text = label) },
        modifier = Modifier
            .fillMaxWidth()
            .height(if (isMultiline) 100.dp else 56.dp),
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF55B3A4),
            unfocusedBorderColor = Color.Gray
        ),
        maxLines = if (isMultiline) Int.MAX_VALUE else 1
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewAngkutScreen() {
    MobileAPPTheme {
        AngkutScreen()
    }
}
