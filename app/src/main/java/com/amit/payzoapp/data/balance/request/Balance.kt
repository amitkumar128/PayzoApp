package com.amit.payzoapp.data.balance.request

data class Balance(
    val id: String,
    val title: String,
    val subtitle: String,
    val amount: String,
    val positive: Boolean
)