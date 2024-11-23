package com.example.mobileapp.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobileapp.beranda.DashboardActivity
import com.example.mobileapp.ui.theme.MobileAPPTheme
import kotlinx.coroutines.delay

class MasukActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileAPPTheme {
                MasukScreen()
            }
        }
    }
}

@Composable
fun MasukScreen() {
    val context = LocalContext.current
    var isVisible by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showForgotPasswordDialog by remember { mutableStateOf(false) }
    var forgotPasswordEmail by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        delay(100)
        isVisible = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB2DFDB)),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Selamat Datang!",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF004D40),
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Masukkan email dan password yang telah kamu daftarkan sebelumnya!",
                fontSize = 14.sp,
                color = Color.Black,
                textAlign = TextAlign.Start
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        AnimatedVisibility(
            visible = isVisible,
            enter = slideInVertically(initialOffsetY = { it })
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color.White, shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .padding(horizontal = 24.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    shape = RoundedCornerShape(16.dp),
                    singleLine = true
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    shape = RoundedCornerShape(16.dp),
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Lupa Password?",
                    fontSize = 14.sp,
                    color = Color(0xFF57B5A8),
                    modifier = Modifier
                        .align(Alignment.End)
                        .clickable { showForgotPasswordDialog = true }
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        val intent = Intent(context, DashboardActivity::class.java)
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF55B3A4),
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Masuk", fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(16.dp))

                val annotatedText = buildAnnotatedString {
                    append("Belum punya akun? ")

                    pushStringAnnotation(tag = "register", annotation = "register")
                    withStyle(
                        style = SpanStyle(
                            color = Color(0xFF55B3A4),
                            textDecoration = TextDecoration.Underline
                        )
                    ) {
                        append("Daftar di sini")
                    }
                    pop()
                }

                ClickableText(
                    text = annotatedText,
                    style = TextStyle(fontSize = 14.sp, color = Color.Gray),
                    modifier = Modifier.padding(vertical = 8.dp),
                    onClick = { offset ->
                        annotatedText.getStringAnnotations(tag = "register", start = offset, end = offset)
                            .firstOrNull()?.let {
                                val intent = Intent(context, DaftarActivity::class.java)
                                context.startActivity(intent)
                            }
                    }
                )
            }
        }
    }

    if (showForgotPasswordDialog) {
        DialogLupaPassword(
            email = forgotPasswordEmail,
            onEmailChange = { forgotPasswordEmail = it },
            onDismiss = { showForgotPasswordDialog = false },
            onSend = {
                showForgotPasswordDialog = false
                val intent = Intent(context, GantiPasswordActivity::class.java)
                context.startActivity(intent)
            }
        )
    }
}

@Composable
fun DialogLupaPassword(
    email: String,
    onEmailChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onSend: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                // Circular icon container
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .background(Color(0xFF55B3A4), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "?",
                        fontSize = 36.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // Title text
                Text(
                    text = "Lupa Password",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color(0xFF004D40)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Subtitle text
                Text(
                    text = "Kami akan mengirimkan password ke email yang telah kamu daftarkan.",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Email input field
                OutlinedTextField(
                    value = email,
                    onValueChange = onEmailChange,
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Action buttons
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = onSend,
                        modifier = Modifier.weight(1f).padding(end = 4.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF55B3A4))
                    ) {
                        Text("Kirim", color = Color.White)
                    }
                    OutlinedButton(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f).padding(start = 4.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)
                    ) {
                        Text("Batal", color = Color.Red)
                    }
                }
            }
        },
        confirmButton = {}, // Remove default confirm button
        dismissButton = {}  // Remove default dismiss button
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewMasukScreen() {
    MobileAPPTheme {
        MasukScreen()
    }
}
