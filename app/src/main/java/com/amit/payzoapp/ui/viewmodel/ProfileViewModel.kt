package com.amit.payzoapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amit.payzoapp.domain.ProfileInfo
import com.amit.payzoapp.domain.repository.PayzoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repo: PayzoRepository): ViewModel() {
    private val _profile = MutableStateFlow<ProfileInfo?>(null)
    val profile : StateFlow<ProfileInfo?> = _profile

    init {
        viewModelScope.launch {
            _profile.value = repo.getProfileInfo()
        }
    }
}