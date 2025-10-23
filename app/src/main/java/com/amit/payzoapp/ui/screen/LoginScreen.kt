package com.amit.payzoapp.ui.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController) {
    var number by remember { mutableStateOf("") }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = androidx.compose.ui.graphics.Color(0xFF5B4FFE))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "LOGIN WITH YOUR\nMOBILE PHONE\nNUMBER",
                color = androidx.compose.ui.graphics.Color.White
            )
            Spacer(modifier = Modifier.height(40.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = androidx.compose.ui.graphics.Color(0xFFF6F8FA),
                        shape = RoundedCornerShape(24.dp)
                    )
                    .padding(20.dp)
            ) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("+91", modifier = Modifier.padding(end = 12.dp))
                    OutlinedTextField(
                        value = number,
                        onValueChange = { input ->
                            // keep digits only and limit to 10 chars
                            number = input.filter { it.isDigit() }.take(10)
                        },
                        label = { Text("Enter Mobile Number") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (number.length == 10) {
                            // Navigate to Home and remove Login from backstack so back won't return here
                            navController.navigate("home") {
                                popUpTo("login") { inclusive = true }
                            }
                        } else {
                            Toast.makeText(context, "Please enter a valid 10-digit mobile number", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(28.dp)
                ) {
                    Text("Verify")
                }

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    "Your personal details are safe with us\nRead our Privacy Policy and Terms and Conditions",
                    fontSize = 12.sp
                )
            }
        }
    }
}
