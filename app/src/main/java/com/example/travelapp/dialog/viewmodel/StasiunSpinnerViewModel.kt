package com.example.travelapp.dialog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.travelapp.api.stasiun.Stasiun
import com.example.travelapp.dialog.repository.StasiunRepository

class StasiunSpinnerViewModel(private val stationRepository: StasiunRepository) : ViewModel() {

    // Menggunakan LiveData<List<Stasiun>> untuk menyimpan data stasiun
    private val _stasiunData: MutableLiveData<List<Stasiun>> = MutableLiveData()
    val stasiunData: LiveData<List<Stasiun>> get() = _stasiunData

    private val _selectedStasiun = MutableLiveData<Stasiun>()
    val selectedStasiun: LiveData<Stasiun> get() = _selectedStasiun

    // Anda dapat menambahkan konstruktor tambahan untuk dependency injection jika diperlukan
    constructor() : this(StasiunRepository()) {
        // Inisialisasi tambahan jika diperlukan
    }

    fun setStasiunData(stasiunList: List<Stasiun>) {
        _stasiunData.value = stasiunList
    }

    fun selectStasiun(stasiun: Stasiun) {
        _selectedStasiun.value = stasiun
    }
}
