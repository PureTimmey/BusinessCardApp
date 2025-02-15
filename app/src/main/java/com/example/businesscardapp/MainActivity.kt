package com.example.businesscardapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import com.example.businesscardapp.ui.theme.BusinessCardAppTheme
import androidx.compose.ui.graphics.ColorFilter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardAppTheme {
                var isDarkMode by remember { mutableStateOf(false) }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier
                        .padding(innerPadding)
                        .background(if (isDarkMode) Color(0xFF121212) else Color(0xFFF5F5F5))
                        .fillMaxSize()) {
                        DarkModeToggleButton(isDarkMode) { isDarkMode = !isDarkMode }
                        BusinessCard(isDarkMode, Modifier.fillMaxSize())
                    }
                }
            }
        }
    }
}

@Composable
fun DarkModeToggleButton(isDarkMode: Boolean, onToggle: () -> Unit) {
    val buttonBackground = if (isDarkMode) Color.White else Color.Black
    val buttonTextColor = if (isDarkMode) Color.Black else Color.White

    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = onToggle,
            colors = ButtonDefaults.buttonColors(containerColor = buttonBackground)
        ) {
            Text(
                text = if (isDarkMode) "Switch to Light Mode" else "Switch to Dark Mode",
                color = buttonTextColor
            )
        }
    }
}

@Composable
fun BusinessCard(isDarkMode: Boolean, modifier: Modifier = Modifier) {
    val backgroundColor = if (isDarkMode) Color(0xFF121212) else Color(0xFFF5F5F5)
    val textColor = if (isDarkMode) Color.White else Color.Black
    val contactColor = if (isDarkMode) Color.Cyan else Color.Blue
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile Image
        Image(
            painter = painterResource(id = R.drawable.android_logo),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape),
            colorFilter = if (isDarkMode) ColorFilter.tint(Color.White) else ColorFilter.tint(Color.Black) // Adjust image tint based on theme
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Name and Title
        Text(text = "Tymur Karachentsev", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = textColor)
        Text(text = "Student", fontSize = 18.sp, color = textColor.copy(alpha = 0.7f))

        Spacer(modifier = Modifier.height(64.dp))


        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.email_logo), // Add an email icon to res/drawable
                contentDescription = "Email Icon",
                modifier = Modifier.size(24.dp),
                colorFilter = if (isDarkMode) ColorFilter.tint(Color.White) else ColorFilter.tint(Color.Black) // Adjust image tint based on theme
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "timothy.tk101@gmail.com",
                fontSize = 16.sp,
                color = contactColor,
                modifier = Modifier.clickable {
                    val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:timothy.tk101@gmail.com")
                    }
                    context.startActivity(emailIntent)
                }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))


        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.phone_logo), // Add a phone icon to res/drawable
                contentDescription = "Phone Icon",
                modifier = Modifier.size(24.dp),
                colorFilter = if (isDarkMode) ColorFilter.tint(Color.White) else ColorFilter.tint(Color.Black) // Adjust image tint based on theme
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "+1 403 550 2553",
                fontSize = 16.sp,
                color = contactColor,
                modifier = Modifier.clickable {
                    val callIntent = Intent(Intent.ACTION_DIAL).apply {
                        data = Uri.parse("tel:+14035502553")
                    }
                    context.startActivity(callIntent)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BusinessCardPreview() {
    var isDarkMode by remember { mutableStateOf(false) }
    BusinessCardAppTheme {
        Column(
            modifier = Modifier
                .background(if (isDarkMode) Color.Black else Color.White)
                .fillMaxSize()
        ) {
            DarkModeToggleButton(isDarkMode) { isDarkMode = !isDarkMode }
            BusinessCard(isDarkMode)
        }
    }
}
