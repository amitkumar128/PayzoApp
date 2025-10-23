package com.amit.payzoapp.domain.repository

import com.amit.payzoapp.domain.HomeQuickAction
import com.amit.payzoapp.domain.Offer
import com.amit.payzoapp.domain.ProfileInfo

interface PayzoRepository {

    suspend fun getOffers(): List<Offer>
    suspend fun getQuickActions(): List<HomeQuickAction>
    suspend fun getProfileInfo(): ProfileInfo
}