package com.example.travelapp.admin

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Kereta(
    @PrimaryKey
    var id: String = "",
    var namaKereta: String = "",
    var stasiunKeberangkatan: String = "",
    var stasiunTujuan: String = "",
    var tanggalBerangkat: String = "",
    var kodeStasiunKeberangkatan: String = "",
    var kodeStasiunTujuan: String = "",
    var jamBerangkat: String = "",
    var jamTiba: String = "",
    var harga: String = "",
    var kelasKereta: String = "",
    var durasiPerjalanan: String= ""
) : Parcelable