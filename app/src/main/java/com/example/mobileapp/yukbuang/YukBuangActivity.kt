package com.example.mobileapp.yukbuang

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

class BuangActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileAPPTheme {
                BuangScreen()
            }
        }
    }
}

@Composable
fun BuangScreen() {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    val additionalMessageState = remember { mutableStateOf("") }

    val phoneState = remember { mutableStateOf("") }
    val addressState = remember { mutableStateOf("") }

    val dateState = remember { mutableStateOf("") }
    val timeState = remember { mutableStateOf("") }
    val deliveryAddressState = remember { mutableStateOf("") }


    val phoneError = remember { mutableStateOf(false) }
    val addressError = remember { mutableStateOf(false) }
    val dateError = remember { mutableStateOf(false) }
    val timeError = remember { mutableStateOf(false) }
    val deliveryAddressError = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
    ) {

        BuangHeaderSection()

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
            BuangSampahOptions()

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Informasi Tempat Tinggal",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            BuangTempatTinggalForm(phoneState, addressState, phoneError, addressError)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Informasi Pengantaran",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            BuangPengantaranForm(dateState, timeState, deliveryAddressState, dateError, timeError, deliveryAddressError)

            Spacer(modifier = Modifier.height(16.dp))


            Text(
                text = "Pesan Tambahan",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            BuangCustomTextField(
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
            BuangFotoSampahSection()

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val isPhoneValid = phoneState.value.isNotEmpty()
                    val isAddressValid = addressState.value.isNotEmpty()
                    val isDateValid = dateState.value.isNotEmpty()
                    val isTimeValid = timeState.value.isNotEmpty()
                    val isDeliveryAddressValid = deliveryAddressState.value.isNotEmpty()

                    // Update error state
                    phoneError.value = !isPhoneValid
                    addressError.value = !isAddressValid
                    dateError.value = !isDateValid
                    timeError.value = !isTimeValid
                    deliveryAddressError.value = !isDeliveryAddressValid

                    // Jika semua validasi lolos, pindah ke layar berikutnya
                    if (isPhoneValid && isAddressValid && isDateValid && isTimeValid && isDeliveryAddressValid) {
                        val intent = Intent(context, PembayaranYukBuangActivity::class.java)
                        context.startActivity(intent)
                    } else {
                        Toast.makeText(context, "Harap isi semua kolom yang wajib diisi.", Toast.LENGTH_SHORT).show()
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
        }
    }
}


// Fungsi Header Section
@Composable
fun BuangHeaderSection() {
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
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = "Yuk Buang!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Kamu bisa antar sampah mu ke titik terdekat",
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun BuangSampahOptions() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            BuangKertasCard()
            BuangPlastikCard()
            BuangBotolKacaCard()
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            BuangElektronikCard()
            BuangBesiCard()
            BuangAluminiumCard()
        }
    }
}

@Composable
fun BuangKertasCard() {
    val context = LocalContext.current
    BuangSampahActionCard("Kertas", R.drawable.kertas, Color(0xFF4CAF50)) {
        val intent = Intent(context, JenisSampahActivity::class.java)
        context.startActivity(intent)
    }
}

@Composable
fun BuangPlastikCard() {
    val context = LocalContext.current
    BuangSampahActionCard("Plastik", R.drawable.plastik, Color(0xFFFF9800)) {
        val intent = Intent(context, PlastikActivity::class.java)
        context.startActivity(intent)

    }
}

@Composable
fun BuangBotolKacaCard() {
    val context = LocalContext.current
    BuangSampahActionCard("Botol Kaca", R.drawable.kaca, Color(0xFF2196F3)) {
        val intent = Intent(context, BotolKacaActivity::class.java)
        context.startActivity(intent)
    }
}

@Composable
fun BuangElektronikCard() {
    val context = LocalContext.current
    BuangSampahActionCard("Elektronik", R.drawable.elektronik, Color(0xFFF44336)) {
        val intent = Intent(context, ElektronikActivity::class.java)
        context.startActivity(intent)

    }
}

@Composable
fun BuangBesiCard() {
    val context = LocalContext.current
    BuangSampahActionCard("Besi", R.drawable.besi, Color(0xFF607D8B)) {
        val intent = Intent(context, BesiActivity::class.java)
        context.startActivity(intent)
    }
}

@Composable
fun BuangAluminiumCard() {
    val context = LocalContext.current
    BuangSampahActionCard("Aluminium", R.drawable.alumanium1, Color(0xFF9C27B0)) {
        val intent = Intent(context, AluminiumActivity::class.java)
        context.startActivity(intent)

    }
}

@Composable
fun BuangSampahActionCard(name: String, iconRes: Int, color: Color, onClick: () -> Unit) {
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
                .padding(8.dp), // Padding internal untuk menyesuaikan konten
            horizontalAlignment = Alignment.CenterHorizontally, // Konten horisontal di tengah
            verticalArrangement = Arrangement.Center // Konten vertikal di tengah
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = name,
                modifier = Modifier.size(40.dp), // Ukuran ikon sedikit lebih besar
                colorFilter = ColorFilter.tint(color) // Menambahkan warna ikon
            )
            Spacer(modifier = Modifier.height(8.dp)) // Spasi antara ikon dan teks
            Text(
                text = name,
                fontSize = 14.sp, // Ukuran font teks sedikit lebih besar
                fontWeight = FontWeight.Bold, // Teks dibuat lebih tebal
                color = Color.Black,
                textAlign = TextAlign.Center, // Teks berada di tengah
                maxLines = 1 // Membatasi teks hanya satu baris
            )
        }
    }
}

@Composable
fun BuangTempatTinggalForm(
    phoneState: MutableState<String>,
    addressState: MutableState<String>,
    phoneError: MutableState<Boolean>,
    addressError: MutableState<Boolean>
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        BuangCustomTextField(
            label = if (phoneError.value) "No. Ponsel (wajib diisi)" else "No. Ponsel :",
            textState = phoneState,
            isError = phoneError.value
        )
        Spacer(modifier = Modifier.height(8.dp))
        BuangCustomTextField(
            label = if (addressError.value) "Alamat (wajib diisi)" else "Alamat :",
            textState = addressState,
            isMultiline = true,
            isError = addressError.value
        )
    }
}


@Composable
fun BuangPengantaranForm(
    dateState: MutableState<String>,
    timeState: MutableState<String>,
    deliveryAddressState: MutableState<String>,
    dateError: MutableState<Boolean>,
    timeError: MutableState<Boolean>,
    deliveryAddressError: MutableState<Boolean>
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        BuangCustomTextField(
            label = if (dateError.value) "Tanggal (wajib diisi)" else "Tanggal :",
            textState = dateState,
            isError = dateError.value
        )
        Spacer(modifier = Modifier.height(8.dp))
        BuangCustomTextField(
            label = if (timeError.value) "Waktu (wajib diisi)" else "Waktu :",
            textState = timeState,
            isError = timeError.value
        )
        Spacer(modifier = Modifier.height(8.dp))
        BuangCustomTextField(
            label = if (deliveryAddressError.value) "Alamat Pengantaran (wajib diisi)" else "Alamat Pengantaran :",
            textState = deliveryAddressState,
            isError = deliveryAddressError.value
        )
    }
}




@Composable
fun BuangFotoSampahSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        // Kartu Foto Sampah 1
        BuangFotoSampahCard(label = "Foto Sampah 1")

        // Kartu Foto Sampah 2 (Duplikat)
        BuangFotoSampahCard(label = "Foto Sampah 2")
    }
}

@Composable
fun BuangFotoSampahCard(label: String) {
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
fun BuangCustomTextField(
    label: String,
    textState: MutableState<String>,
    isMultiline: Boolean = false,
    isError: Boolean = false
) {
    OutlinedTextField(
        value = textState.value,
        onValueChange = { textState.value = it },
        label = {
            Text(text = if (isError) "$label (wajib diisi)" else label, color = if (isError) Color.Red else Color.Gray)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(if (isMultiline) 100.dp else 56.dp),
        isError = isError, // Tampilkan outline merah jika error
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = if (isError) Color.Red else Color(0xFF55B3A4),
            unfocusedBorderColor = if (isError) Color.Red else Color.Gray
        ),
        maxLines = if (isMultiline) Int.MAX_VALUE else 1
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewBuangScreen() {
    MobileAPPTheme {
        BuangScreen()
    }
}