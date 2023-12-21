package com.example.travelapp.database.pesanan

data class dataPesanan(
    var harga: String? = null,
    var stasiunKeberangkatan: String? = null,
    var kodeStasiunKeberangkatan: String? = null,
    var jamBerangkat: String? = null,
    var namaKereta: String? = null,
    var stasiunTujuan: String? = null,
    var kodeStasiunTujuan: String? = null,
    var jamTiba: String? = null,
    var kelas: String? = null,
    var namaPenumpang: String? = null,
    var nomorIdentitas: String? = null,
    var durasiPerjalanan: String? = null,
    var tanggalBerangkat: String? = null
)
