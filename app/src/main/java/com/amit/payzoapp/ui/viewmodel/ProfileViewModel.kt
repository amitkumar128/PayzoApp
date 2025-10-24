package com.amit.payzoapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject


/**
 * Simple ViewModel to hold profile UI state.
 * Keep it simple: expose mutable state properties that Compose can observe.
 * You can replace these with flows/LiveData or inject a repository later.
 */
@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {
    // Basic profile info
    var name by mutableStateOf("Amit Kumar")
        private set


    var levelLabel by mutableStateOf("Level 4")
        private set


    // progress: 0f..1f
    var progress by mutableStateOf(0.85f)
        private set


    // stats
    var transactionsCount by mutableStateOf("1,208")
        private set


    var pointsCount by mutableStateOf("726")
        private set


    var rank by mutableStateOf("8")
        private set


    // Placeholder avatar resource id (if you decide to store an image resource id)
// Keep nullable so screen can decide how to render
    var avatarResId: Int? = null


    // Actions
    fun updateName(newName: String) {
        name = newName
    }


    fun updateProgress(newProgress: Float) {
        progress = newProgress.coerceIn(0f, 1f)
    }


    fun clearProfile() {
        name = ""
        levelLabel = ""
        progress = 0f
        transactionsCount = "0"
        pointsCount = "0"
        rank = "0"
    }


    // Simulate refresh (you can replace with repository call)
    fun refreshProfile() {
    }
}