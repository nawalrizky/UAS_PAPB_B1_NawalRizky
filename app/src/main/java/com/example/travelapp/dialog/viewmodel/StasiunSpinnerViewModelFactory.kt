package com.example.travelapp.dialog.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.travelapp.dialog.repository.StasiunRepository

class StasiunSpinnerViewModelFactory(private val stationRepository: StasiunRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StasiunSpinnerViewModel::class.java)) {
            return StasiunSpinnerViewModel(stationRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
