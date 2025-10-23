package com.amit.payzoapp.ui.screen

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OnboardingScreen(onNext: () -> Unit){
    Box(modifier = Modifier.fillMaxSize().background(color = Color(0xFFF6F8FA))){
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(60.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(modifier = Modifier.size(120.dp).background(Color(0xFF5B4FFE), shape = RoundedCornerShape(28.dp)), contentAlignment = Alignment.Center){
                    Text("⚡", fontSize = 36.sp)
                }
                Spacer(modifier = Modifier.height(20.dp))


                Box(modifier = Modifier.background(androidx.compose.ui.graphics.Color(0xFF5B4FFE), shape = RoundedCornerShape(6.dp)).padding(horizontal = 18.dp, vertical = 8.dp)) {
                    Text("INSTANT PAY", color = androidx.compose.ui.graphics.Color.White)
                }
                Text("Your Perfect Payment Partner", fontSize = 12.sp, modifier = Modifier.padding(top = 8.dp))
            }


            Column(modifier = Modifier.fillMaxWidth().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
// page indicators
                Row(horizontalArrangement = Arrangement.Center) {
                    repeat(4) { idx ->
                        val size = if (idx == 0) 8.dp else 6.dp
                        Box(modifier = Modifier.size(size).background(androidx.compose.ui.graphics.Color.DarkGray, shape = RoundedCornerShape(8.dp)).padding(4.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = onNext, shape = RoundedCornerShape(24.dp)) {
                    Text("Get started")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }

}