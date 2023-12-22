package com.example.travelapp.database.kereta

data class dataKereta(
    var id: String = "",  // Add this line for the id property
    var harga: String? = null,
    var stasiunKeberangkatan: String? = null,
    var kodeStasiunKeberangkatan: String? = null,
    var jamBerangkat: String? = null,
    var sisaTiket: String? = null,
    var namaKereta: String? = null,
    var stasiunTujuan: String? = null,
    var kodeStasiunTujuan: String? = null,
    var jamTiba: String? = null,
    var kelasKereta: String? = null,
    var durasiPerjalanan: String? = null,
    var tanggalBerangkat: String? = null
)
