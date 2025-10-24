package com.amit.payzoapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amit.payzoapp.data.balance.request.Balance
import com.amit.payzoapp.data.balance.response.DummyBalanceData
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for Balance Screen using StateFlow (coroutines).
 *
 * Exposes immutable StateFlow for UI to collect/observe.
 */
@HiltViewModel
class BalanceViewModel @Inject constructor() : ViewModel() {

    // internal mutable flows
    private val _balance = MutableStateFlow(DummyBalanceData.initialBalance)
    private val _cashback = MutableStateFlow(DummyBalanceData.initialCashback)
    private val _points = MutableStateFlow(DummyBalanceData.initialPoints)
    private val _transactions = MutableStateFlow(DummyBalanceData.sampleTransactions())

    // public read-only flows
    val balance: StateFlow<String> = _balance.asStateFlow()
    val cashback: StateFlow<String> = _cashback.asStateFlow()
    val points: StateFlow<String> = _points.asStateFlow()
    val transactions: StateFlow<List<Balance>> = _transactions.asStateFlow()

    /**
     * Add a transaction at the top (newest first).
     * This function updates the StateFlow on the ViewModel's scope.
     */
    fun addTransaction(balance: Balance) {
        viewModelScope.launch {
            // prepend new balance
            _transactions.value = listOf(balance) + _transactions.value
        }
    }

    /**
     * Update balance string (UI-friendly string). Replace with numeric handling if needed.
     */
    fun updateBalance(newBalance: String) {
        viewModelScope.launch {
            _balance.value = newBalance
        }
    }

    /**
     * Refresh method (placeholder). Demonstrates async update with coroutine + delay.
     * Replace with real repository/network calls.
     */
    fun refresh() {
        viewModelScope.launch {
            // simulate network latency
            delay(700)

            // fake updated values (in a real app you'll fetch from repository)
            _balance.value = DummyBalanceData.initialBalance
            _cashback.value = DummyBalanceData.initialCashback
            _points.value = DummyBalanceData.initialPoints
            _transactions.value = DummyBalanceData.sampleTransactions()
        }
    }

    /**
     * Example: add a small cashback amount when a transaction is added (demo logic).
     * This is synchronous on the viewModelScope for simplicity.
     */
    fun addTransactionAndAdjustCashback(balance: Balance, cashbackDelta: Int = 10) {
        viewModelScope.launch {
            addTransaction(balance)

            // parse numeric portion from cashback string if possible and increment
            val digits = _cashback.value.filter { it.isDigit() }
            val current = digits.toIntOrNull() ?: 0
            _cashback.value = "₹${current + cashbackDelta}"
        }
    }
}
