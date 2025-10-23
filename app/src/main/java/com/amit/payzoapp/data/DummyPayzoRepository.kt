package com.amit.payzoapp.data

import com.amit.payzoapp.domain.HomeQuickAction
import com.amit.payzoapp.domain.Offer
import com.amit.payzoapp.domain.ProfileInfo
import com.amit.payzoapp.domain.repository.PayzoRepository
import javax.inject.Inject

class DummyPayzoRepository @Inject constructor(): PayzoRepository {
    override suspend fun getOffers(): List<Offer> {
        return listOf(
            Offer("1","DTH Recharge Offer","Use code FIRSTDHT20"),
            Offer("2","Flipcart Shopping Offer","Up to 50% cashback"),
            Offer("3","Money Transfer Offer","Cashback on transfers > 500"),
            Offer("4","Rs 50 Off on Flights","Flat Rs 50 on air tickets")
        )
    }

    override suspend fun getQuickActions(): List<HomeQuickAction> {
        return listOf(
            HomeQuickAction("scan","Scan QR Code"),
            HomeQuickAction("contact","Send to Contact"),
            HomeQuickAction("bank","Send To Bank"),
            HomeQuickAction("self","Self Transfer")
        )
    }

    override suspend fun getProfileInfo(): ProfileInfo {
        return ProfileInfo(name = "Elsa", level = 4, transactions = 1208, points = 726)
    }
}