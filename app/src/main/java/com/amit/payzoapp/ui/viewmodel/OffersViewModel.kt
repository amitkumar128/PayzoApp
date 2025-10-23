package com.amit.payzoapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amit.payzoapp.domain.Offer
import com.amit.payzoapp.domain.repository.PayzoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OffersViewModel @Inject constructor(private val repo: PayzoRepository): ViewModel(){


    private val _offers= MutableStateFlow<List<Offer>>(emptyList())
    val offers: StateFlow<List<Offer>> = _offers

    init {
        viewModelScope.launch {
            _offers.value = repo.getOffers()
        }
    }
}