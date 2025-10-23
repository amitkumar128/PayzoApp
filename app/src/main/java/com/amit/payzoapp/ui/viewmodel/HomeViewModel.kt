package com.amit.payzoapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amit.payzoapp.domain.HomeQuickAction
import com.amit.payzoapp.domain.repository.PayzoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: PayzoRepository): ViewModel() {

    private val _quickActions = MutableStateFlow<List<HomeQuickAction>>(emptyList())
    val quickActions: StateFlow<List<HomeQuickAction>> = _quickActions

    init {
        viewModelScope.launch {
            _quickActions.value = repo.getQuickActions()
        }
    }
}