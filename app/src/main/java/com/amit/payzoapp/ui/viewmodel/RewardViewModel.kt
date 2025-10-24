package com.amit.payzoapp.ui.viewmodel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

/**
 * ViewModel for Rewards screen.
 * Keeps cashback, scratchcards and collectable rewards.
 */

data class RewardItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val collected: Boolean = false
)
@HiltViewModel
class RewardViewModel @Inject constructor() : ViewModel() {

    var cashback by mutableStateOf("₹507")
        private set

    // simple representation of scratchcards (count or ids)
    var scratchcardsCount by mutableStateOf(3)
        private set

    // Collectable rewards list
    var rewards by mutableStateOf(
        listOf(
            RewardItem("1", "Flat 50 off On Food Delivery", "On orders above ₹99 on Swiggy, Zomato"),
            RewardItem("2", "20% Cashback On Amazon", "Up to ₹150 on minimum order of ₹1000"),
            RewardItem("3", "5% Fuel Discount", "On selected fuel stations")
        )
    )
        private set

    /**
     * Mark reward as collected. This will update the list state so Compose recomposes.
     */
    fun collectReward(rewardId: String) {
        rewards = rewards.map {
            if (it.id == rewardId) it.copy(collected = true) else it
        }
        // Example: increment cashback/scratchcards or update other stats
        // For demo, we add a small amount to cashback when a reward is collected
        cashback = adjustCashbackAfterCollect(cashback)
    }

    private fun adjustCashbackAfterCollect(current: String): String {
        // Very simple parser: remove non-digits, add 10, and format back — safe for demo only
        return try {
            val digits = current.filter { it.isDigit() }
            val value = if (digits.isEmpty()) 0 else digits.toInt()
            "₹ ${value + 10}"
        } catch (e: Exception) {
            current
        }
    }

    fun refreshRewards() {
        // placeholder for network/db refresh
    }

    fun clearAll() {
        cashback = "₹0"
        scratchcardsCount = 0
        rewards = emptyList()
    }
}
