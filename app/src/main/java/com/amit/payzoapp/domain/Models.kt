package com.amit.payzoapp.domain

data class Offer(val id: String, val title: String, val subtitle: String)


data class HomeQuickAction(val id: String, val title: String)


data class ProfileInfo(val name: String, val level: Int, val transactions:Int, val points:Int)