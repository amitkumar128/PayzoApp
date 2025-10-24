package com.amit.payzoapp.ui.viewmodel

import androidx.annotation.DrawableRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.amit.payzoapp.R
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

data class OfferUi(
    val id: String,
    val title: String,
    val subtitle: String,
    val code: String? = null,
    @DrawableRes val icon: Int
)
@HiltViewModel
class OfferViewModel @Inject constructor() : ViewModel() {

    // Exposed list of offers (mutable so you can update later)
    var offers by mutableStateOf(
        listOf(
            OfferUi(
                id = "dth",
                title = "DTH Recharge Offer",
                subtitle = "Get 20% instant cashback up to ₹50 on your first DTH recharge. T&C apply",
                code = "FIRSTDHT20",
                icon = R.drawable.outline_satellite_alt_24
            ),
            OfferUi(
                id = "flipkart",
                title = "Flipkart Shopping Offer",
                subtitle = "Shop on Flipkart using our payment system to get up to 50% cashback. T&C apply",
                code = null,
                icon = R.drawable.shopping_bag
            ),
            OfferUi(
                id = "money_transfer",
                title = "Money Transfer Offer",
                subtitle = "Get a scratch card with assured cashback and coupons on transfers of ₹500 or more. T&C apply",
                code = null,
                icon = R.drawable.send_money
            ),
            OfferUi(
                id = "flights",
                title = "Rs 50 Off on Flights",
                subtitle = "Get instant discount on flat ₹50 on Flight ticket booking. T&C apply",
                code = null,
                icon = R.drawable.flight
            )
        )
    )
        private set

    // Example actions you might call from UI:
    fun refreshOffers() {
        // TODO: fetch updated offers from repository / network
    }

    fun addOffer(offer: OfferUi) {
        offers = offers + offer
    }

    fun removeOfferById(id: String) {
        offers = offers.filterNot { it.id == id }
    }

    fun getOfferById(id: String): OfferUi? = offers.find { it.id == id }
}
