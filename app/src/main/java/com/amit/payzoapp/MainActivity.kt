package com.amit.payzoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.amit.payzoapp.ui.screen.PayzoAppNavigate
import com.amit.payzoapp.ui.theme.PayzoAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PayzoAppTheme {
                PayzoAppNavigate()
            }
        }
    }
}
