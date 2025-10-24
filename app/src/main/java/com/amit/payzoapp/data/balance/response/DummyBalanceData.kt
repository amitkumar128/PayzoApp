package com.amit.payzoapp.data.balance.response

import com.amit.payzoapp.data.balance.request.Balance

object DummyBalanceData {

    fun sampleTransactions(): List<Balance> = listOf(
        Balance("1", "Salary Credited", "Company - Sept", "+₹75,000", true),
        Balance("2", "Electricity Bill", "TNEB - Sept", "-₹3,420", false),
        Balance("3", "Mobile Recharge", "Airtel", "-₹399", false),
        Balance("4", "Money Received", "From Rahul", "+₹2,500", true),
        Balance("5", "Flight Booking", "IndiGo", "-₹4,999", false)
    )

    // Example initial values
    const val initialBalance = "₹ 28,540"
    const val initialCashback = "₹507"
    const val initialPoints = "726 pts"
}