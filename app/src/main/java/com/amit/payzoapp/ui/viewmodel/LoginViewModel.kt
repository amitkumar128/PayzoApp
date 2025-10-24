package com.amit.payzoapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    // phone number state (digits only, max 10)
    var number by mutableStateOf("")
        private set

    /** Update number: keeps only digits and limits to 10 chars */
    fun onNumberChange(input: String) {
        number = input.filter { it.isDigit() }.take(10)
    }

    /** Simple validation */
    fun isValidNumber(): Boolean = number.length == 10

    /** Optional: clear after successful verify (if you want) */
    fun clear() {
        number = ""
    }
}
