package com.example.mobileapp.kuypoint

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobileapp.R
import com.example.mobileapp.ui.theme.MobileAPPTheme
import androidx.compose.ui.text.input.KeyboardType
import com.example.mobileapp.order.OrderActivity

@OptIn(ExperimentalMaterial3Api::class)
class TukarDanaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileAPPTheme {
                TukarDanaMainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TukarDanaMainScreen() {
    val rekeningDANA = remember { mutableStateOf(TextFieldValue("")) }
    val selectedNominal = remember { mutableStateOf("15.000") } // Default minimal amount
    val customNominal = remember { mutableStateOf(TextFieldValue("")) } // For custom nominal input
    val showErrorDialog = remember { mutableStateOf(false) } // State to control the error dialog visibility
    val showSuccessDialog = remember { mutableStateOf(false) } // State for success dialog visibility

    // Validasi jika semua input sudah diisi
    val isFormComplete = remember(rekeningDANA.value.text, selectedNominal.value) {
        rekeningDANA.value.text.isNotEmpty() && (selectedNominal.value.isNotEmpty() || customNominal.value.text.isNotEmpty())
    }

    // Combine the selectedNominal and customNominal logic
    val nominalAmount = remember(customNominal.value.text) {
        val cleanText = customNominal.value.text.replace(".", "") // Remove dots for validation
        cleanText.toLongOrNull() ?: 0
    }

    // Validasi minimal nominal top-up
    val isNominalValid = nominalAmount >= 15000 // Ensure entered value is >= 15000

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header
        TukarDanaHeader()

        Spacer(modifier = Modifier.height(16.dp))

        // Header Text
        Text(
            text = "Tukar Kuy Point ke saldo DANA",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Text(
            text = "Sekarang kamu bisa menukarkan KuyPoint yang kamu miliki dengan saldo DANA! Ayo lakukan lebih banyak Yuk Angkut! dan Yuk Buang!",
            fontSize = 15.sp,
            color = Color.Gray,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Input Nomor DANA
        Text(
            text = "Masukkan Nomor DANA Kamu",
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        TextField(
            value = rekeningDANA.value,
            onValueChange = { rekeningDANA.value = it },
            placeholder = { Text(text = "Masukkan nomor DANA") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp)),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number // Ensures number only keyboard
            ),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFF5F5F5)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Nominal Options
        Text(
            text = "Nominal",
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 16.dp, end = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TukarDanaNominalButton("Rp 20.000", Modifier.weight(1f), selectedNominal, customNominal)
                TukarDanaNominalButton("Rp 30.000", Modifier.weight(1f), selectedNominal, customNominal)
                TukarDanaNominalButton("Rp 50.000", Modifier.weight(1f), selectedNominal, customNominal)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TukarDanaNominalButton("Rp 70.000", Modifier.weight(1f), selectedNominal, customNominal)
                TukarDanaNominalButton("Rp 80.000", Modifier.weight(1f), selectedNominal, customNominal)
                TukarDanaNominalButton("Rp 100.000", Modifier.weight(1f), selectedNominal, customNominal)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Custom amount input
        Text(
            text = "Masukkan nominal",
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        TextField(
            value = customNominal.value,
            onValueChange = { customNominal.value = it },
            placeholder = { Text(text = "Minimal top up Rp 15.000") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp)),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number // Ensures number only keyboard
            ),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFF5F5F5)
            )
        )

        Spacer(modifier = Modifier.height(100.dp))

        // Exchange button
        Button(
            onClick = {
                if (!isNominalValid) {
                    // Show error dialog if the nominal is invalid
                    showErrorDialog.value = true
                } else {
                    // Show success dialog if the nominal is valid
                    showSuccessDialog.value = true
                }
            },
            enabled = isFormComplete,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isFormComplete) Color(0xFF55B3A4) else Color.Gray
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Tukar Sekarang", fontSize = 16.sp, color = Color.White)
        }

        // Error Dialog
        if (showErrorDialog.value) {
            AlertDialog(
                onDismissRequest = { showErrorDialog.value = false },
                title = {
                    Text(text = "Error", fontWeight = FontWeight.Bold, color = Color.Black)
                },
                text = {
                    Text(text = "Nominal anda tidak sesuai dengan minimal top up", color = Color.Black)
                },
                confirmButton = {
                    TextButton(
                        onClick = { showErrorDialog.value = false }
                    ) {
                        Text(text = "OK", color = Color.Black)
                    }
                }
            )
        }

        // Success Dialog
        if (showSuccessDialog.value) {
            val context = LocalContext.current

            AlertDialog(
                onDismissRequest = { showSuccessDialog.value = false },
                title = {
                    Text(text = "Success", fontWeight = FontWeight.Bold, color = Color.Black)
                },
                text = {
                    Text(text = "Top-up berhasil, lanjut ke halaman selanjutnya.", color = Color.Black)
                },
                confirmButton = {
                    TextButton(onClick = {
                        showSuccessDialog.value = false
                        // Navigate to the next screen (OrderActivity)
                        val intent = Intent(context, OrderActivity::class.java)
                        context.startActivity(intent)
                    }) {
                        Text(text = "OK", color = Color.Black)
                    }
                }
            )
        }
    }
}

@Composable
fun TukarDanaHeader() {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF55B3A4))
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        Text(
            text = "Tukar Kuy Point",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.Center)
        )
        Icon(
            painter = painterResource(id = R.drawable.keluar),
            contentDescription = "Back",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .size(24.dp)
                .clickable {
                    (context as? ComponentActivity)?.finish()
                },
            tint = Color.Black
        )
    }
}

@Composable
fun TukarDanaNominalButton(
    text: String,
    modifier: Modifier = Modifier,
    selectedNominal: MutableState<String>,
    customNominal: MutableState<TextFieldValue>
) {
    val isSelected = selectedNominal.value == text

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(if (isSelected) Color(0xFF55B3A4) else Color(0xFFF5F5F5), RoundedCornerShape(8.dp))
            .clickable {
                selectedNominal.value = text
                customNominal.value = TextFieldValue(text.replace("Rp", "").trim()) // Update the custom nominal
            }
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            color = if (isSelected) Color.White else Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTukarDanaMainScreen() {
    MobileAPPTheme {
        TukarDanaMainScreen()
    }
}
