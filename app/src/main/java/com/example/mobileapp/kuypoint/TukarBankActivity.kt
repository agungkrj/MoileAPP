package com.example.mobileapp.kuypoint

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.mobileapp.R
import com.example.mobileapp.ui.theme.MobileAPPTheme
import java.text.NumberFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
class TukarBankActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileAPPTheme {
                TukarBankScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TukarBankScreen() {
    val context = LocalContext.current
    val bankList = listOf("Bank BNI", "Bank BRI", "Bank Mandiri", "Bank BCA")
    val selectedBank = remember { mutableStateOf("Nama Bank") }
    val showBankList = remember { mutableStateOf(false) }
    val rekeningNumber = remember { mutableStateOf(TextFieldValue("")) }
    val selectedNominal = remember { mutableStateOf("") }
    val showErrorDialog = remember { mutableStateOf(false) }
    val errorMessage = remember { mutableStateOf("") }

    // Validasi jika semua input sudah diisi
    val isFormComplete = remember(selectedBank.value, rekeningNumber.value.text, selectedNominal.value) {
        selectedBank.value != "Nama Bank" &&
                rekeningNumber.value.text.isNotEmpty() &&
                selectedNominal.value.isNotEmpty()
    }

    // Validasi nominal minimal 15.000
    val isNominalValid = remember(selectedNominal.value) {
        val nominalValue = selectedNominal.value.replace(Regex("[^\\d]"), "").toLongOrNull() ?: 0
        nominalValue >= 15000
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
    ) {
        // Header
        ExchangeHeader()

        Spacer(modifier = Modifier.height(16.dp))

        // Header Text
        Text(
            text = "Tukar Kuy Point ke saldo Bank",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Text(
            text = "Sekarang kamu bisa menukarkan KuyPoint yang kamu miliki dengan saldo Bank! Ayo lakukan lebih banyak Yuk Angkut! dan Yuk Buang!",
            fontSize = 15.sp,
            color = Color.Gray,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Dropdown Nama Bank
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 4.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.iconbank),
                    contentDescription = "Bank Icon",
                    tint = Color.Black,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Nama Bank",
                    fontSize = 12.sp,
                    color = Color.Black
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp))
                    .padding(horizontal = 12.dp, vertical = 12.dp)
                    .clickable { showBankList.value = true }
            ) {
                Text(
                    text = selectedBank.value,
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    painter = painterResource(id = R.drawable.drobdown),
                    contentDescription = "Dropdown Icon",
                    tint = Color.Black,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Input Nomor Rekening
        Text(
            text = "Masukkan Nomor Rekening Kamu",
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        TextField(
            value = rekeningNumber.value,
            onValueChange = { rekeningNumber.value = it },
            placeholder = { Text(text = "Masukkan nomor rekening") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp)),
            singleLine = true,
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
                AmountButton("Rp 20.000", Modifier.weight(1f), selectedNominal)
                AmountButton("Rp 30.000", Modifier.weight(1f), selectedNominal)
                AmountButton("Rp 50.000", Modifier.weight(1f), selectedNominal)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AmountButton("Rp 70.000", Modifier.weight(1f), selectedNominal)
                AmountButton("Rp 80.000", Modifier.weight(1f), selectedNominal)
                AmountButton("Rp 100.000", Modifier.weight(1f), selectedNominal)
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
            value = selectedNominal.value,
            onValueChange = { input ->
                val cleanedInput = input.replace(Regex("[^\\d]"), "")
                selectedNominal.value = cleanedInput
            },
            placeholder = { Text(text = "Minimal top up 15.000") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp)),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFF5F5F5)
            )
        )

        Spacer(modifier = Modifier.height(100.dp))

        // Exchange button
        Button(
            onClick = {
                val nominalValue = selectedNominal.value.replace(Regex("[^\\d]"), "").toLongOrNull() ?: 0
                if (nominalValue < 15000) {
                    errorMessage.value = "Nominal yang anda masukkan tidak sesuai minimal"
                    showErrorDialog.value = true
                } else {
                    val intent = Intent(context, ProsesTukarActivity::class.java)
                    context.startActivity(intent)
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

        // Bank List Dialog
        if (showBankList.value) {
            Dialog(onDismissRequest = { showBankList.value = false }) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(12.dp))
                        .padding(16.dp)
                        .wrapContentHeight()
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Pilih Bank",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        bankList.forEach { bank ->
                            Text(
                                text = bank,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                                    .clickable {
                                        selectedBank.value = bank
                                        showBankList.value = false
                                    },
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }

        // Error Dialog
        if (showErrorDialog.value) {
            Dialog(onDismissRequest = { showErrorDialog.value = false }) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(12.dp))
                        .padding(16.dp)
                        .wrapContentHeight()
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = errorMessage.value,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { showErrorDialog.value = false },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF55B3A4)
                            ),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("OK", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ExchangeHeader() {
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
fun AmountButton(
    amount: String,
    modifier: Modifier = Modifier,
    selectedNominal: MutableState<String>
) {
    val numericAmount = amount.replace(Regex("[^\\d]"), "")

    Button(
        onClick = { selectedNominal.value = numericAmount },
        modifier = modifier
            .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selectedNominal.value == numericAmount) Color(0xFF55B3A4) else Color(0xFFF5F5F5)
        )
    ) {
        Text(text = amount, fontSize = 14.sp, color = Color.Black)
    }
}

fun formatRupiah(amount: String): String {
    val number = amount.toLongOrNull() ?: 0
    val formatted = NumberFormat.getNumberInstance(Locale("id", "ID")).format(number)
    return "Rp $formatted"
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MobileAPPTheme {
        TukarBankScreen()
    }
}