package com.example.travelapp

data class Ticket(
    val jamBerangkat: String = "",
    val jamTiba: String = "",
    val stasiunAsal: String = "",
    val stasiunTujuan: String = "",
    val hargaTiket: String = "",
    var isBooked: Boolean = false

)
