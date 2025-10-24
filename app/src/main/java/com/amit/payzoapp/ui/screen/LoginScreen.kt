package com.amit.payzoapp.ui.screen

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.amit.payzoapp.R
import com.amit.payzoapp.ui.viewmodel.LoginViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = viewModel()
) {
    val number by remember { derivedStateOf { viewModel.number } }
    val isValid by remember { derivedStateOf { viewModel.isValidNumber() } }
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    // subtle button press scale animation
    val buttonScale by animateFloatAsState(targetValue = if (isLoading) 0.98f else 1f)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF5B4FFE), Color(0xFF7A6CFF))
                )
            )
            .padding(16.dp)
    ) {
        // Decorative rotated circle top-right
        Box(
            modifier = Modifier
                .size(160.dp)
                .align(Alignment.TopEnd)
                .offset(x = 24.dp, y = (-40).dp)
                .rotate(20f)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(Color(0x88FFFFFF), Color(0x00FFFFFF))
                    ),
                    shape = CircleShape
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // App logo / icon
            Surface(
                shape = CircleShape,
                tonalElevation = 6.dp,
                color = Color.White.copy(alpha = 0.15f),
                modifier = Modifier
                    .size(96.dp)
                    .clip(CircleShape)
            ) {
                // using material icon as placeholder; replace with Image(painterResource(...)) if you have a logo
                Box(contentAlignment = Alignment.Center) {
                    AsyncImage(
                        model = R.mipmap.ic_launcher,
                        contentDescription = "App launcher icon",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "Welcome to Payzo",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Login with your mobile number to continue",
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Card containing form
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    // Input row
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // country code chip
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFFF1F5F9))
                                .padding(horizontal = 12.dp, vertical = 12.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "+91", fontWeight = FontWeight.Medium)
                            }
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        // Phone field
                        OutlinedTextField(
                            value = number,
                            onValueChange = { input -> viewModel.onNumberChange(input) },
                            singleLine = true,
                            placeholder = {
                                Text(
                                    "Enter mobile number",
                                    color = Color.Gray,
                                    fontSize = 14.sp
                                )
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFF5B4FFE),
                                unfocusedBorderColor = Color.LightGray,
                                cursorColor = Color(0xFF5B4FFE),
                                focusedLeadingIconColor = Color(0xFF5B4FFE),
                                unfocusedLeadingIconColor = Color.Gray,
                                focusedTrailingIconColor = Color(0xFF5B4FFE),
                                unfocusedTrailingIconColor = Color.Gray
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            ),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Phone,
                                    contentDescription = "phone icon"
                                )
                            },
                            trailingIcon = {
                                if (number.isNotEmpty()) {
                                    IconButton(onClick = { viewModel.clear() }) {
                                        Icon(
                                            imageVector = Icons.Default.Clear,
                                            contentDescription = "clear"
                                        )
                                    }
                                }
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // helper / error text
                    AnimatedVisibility(visible = showError, enter = fadeIn() + expandIn()) {
                        Text(
                            text = "Please enter a valid 10-digit mobile number.",
                            color = MaterialTheme.colorScheme.error,
                            fontSize = 12.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Verify button
                    ElevatedButton(
                        onClick = {
                            // basic validation
                            if (isValid) {
                                showError = false
                                isLoading = true
                                // simulate verification delay - replace with your real API call
                                scope.launch {
                                    delay(900) // fake network
                                    isLoading = false
                                    // navigate to home and clear login from backstack
                                    navController.navigate("home") {
                                        popUpTo("login") { inclusive = true }
                                    }
                                    // optional: clear the number in viewmodel
                                    // viewModel.clear()
                                }
                            } else {
                                showError = true
                                Toast.makeText(
                                    context,
                                    "Enter a valid 10-digit number",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .graphicsLayer(scaleX = buttonScale, scaleY = buttonScale),
                        shape = RoundedCornerShape(28.dp)
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                strokeWidth = 2.dp,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text("Verifying...")
                        } else {
                            Text("Verify & Continue")
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // small privacy text
                    Text(
                        text = "We’ll send an SMS with a 6-digit code. By continuing, you agree to our Privacy Policy & Terms.",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // divider with OR
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Divider(modifier = Modifier.weight(1f))
                        Text("  or  ", fontSize = 12.sp, color = Color.Gray)
                        Divider(modifier = Modifier.weight(1f))
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Social / alternate actions (placeholders)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        OutlinedButton(
                            onClick = { /* TODO: implement Google sign-in */ },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            // If you have a google icon image, replace with Image
                            Text("Sign in with Google")
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            text = "Need help?",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .clickable {
                                    Toast.makeText(
                                        context,
                                        "Contact support tapped",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                },
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // small footer
            Text(
                text = "Having trouble with login? Call +91-XXXXXXXXXX",
                color = Color.White.copy(alpha = 0.85f),
                fontSize = 12.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewLogin() {
    LoginScreen(navController = rememberNavController())
}
