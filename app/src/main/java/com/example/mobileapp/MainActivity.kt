package com.example.mobileapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobileapp.ui.theme.MobileAPPTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileAPPTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(300)
        isVisible = true
    }

    val infiniteTransition = rememberInfiniteTransition()
    val logoScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.04f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE6F3F3)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            // Logo with breathing animation
            AnimatedVisibility(
                visible = isVisible,
                enter = fadeIn(
                    animationSpec = tween(1800, easing = EaseInOutQuad)
                ) + scaleIn(
                    initialScale = 0.85f,
                    animationSpec = tween(
                        durationMillis = 1800,
                        easing = EaseOutQuad
                    )
                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo1),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(200.dp)
                        .graphicsLayer(
                            scaleX = logoScale,
                            scaleY = logoScale
                        )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Subtitle
            AnimatedVisibility(
                visible = isVisible,
                enter = fadeIn(
                    animationSpec = tween(2000, delayMillis = 600, easing = EaseInOutSine)
                )
            ) {
                Text(
                    text = "Bersama untuk Lingkungan Bersih",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF55B3A4),
                    modifier = Modifier.padding(bottom = 32.dp),
                    textAlign = TextAlign.Center
                )
            }

            // Illustration
            AnimatedVisibility(
                visible = isVisible,
                enter = slideInVertically(
                    initialOffsetY = { 40 },
                    animationSpec = tween(
                        durationMillis = 1800,
                        easing = EaseOutCubic,
                        delayMillis = 800
                    )
                ) + fadeIn()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ilustrasi),
                    contentDescription = "Illustration",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Button "Masuk"
            AnimatedVisibility(
                visible = isVisible,
                enter = scaleIn(
                    initialScale = 0.9f,
                    animationSpec = tween(
                        durationMillis = 1000,
                        easing = FastOutSlowInEasing,
                        delayMillis = 1000
                    )
                ) + fadeIn()
            ) {
                Button(
                    onClick = {
                        val intent = Intent(context, MasukActivity::class.java)
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .width(360.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF55B3A4),
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Masuk")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Button "Daftar"
            AnimatedVisibility(
                visible = isVisible,
                enter = slideInVertically(
                    initialOffsetY = { 50 },
                    animationSpec = tween(
                        durationMillis = 1200,
                        easing = EaseInOutQuad,
                        delayMillis = 1200
                    )
                ) + fadeIn()
            ) {
                OutlinedButton(
                    onClick = {
                        val intent = Intent(context, DaftarActivity::class.java)
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .width(360.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = androidx.compose.material3.ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.White,
                        contentColor = Color(0xFF55B3A4)
                    )
                ) {
                    Text(text = "Daftar")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MobileAPPTheme {
        MainScreen()
    }
}
