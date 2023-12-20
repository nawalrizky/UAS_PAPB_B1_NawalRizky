package com.example.travelapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelapp.databinding.ActivityListTicketBinding

class ListTicketActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListTicketBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListTicketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ticketList = generateTicketData()
        val adapter = TicketAdapter(ticketList, isPurchaseButtonVisible = true)

        binding.rvCardTicketSearch.adapter = adapter

        // Menetapkan LinearLayoutManager pada RecyclerView
        val layoutManager = LinearLayoutManager(this)
        binding.rvCardTicketSearch.layoutManager = layoutManager

        binding.btnBack.setOnClickListener {
            // Intent untuk kembali ke MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Menutup Activity saat ini
        }
    }

    // Contoh generate data tiket
    private fun generateTicketData(): List<Ticket> {
        return listOf(
            Ticket(
                jamBerangkat = "11.00",
                jamTiba = "14.00",
                stasiunAsal = "JKT",
                stasiunTujuan = "BDG",
                hargaTiket = "120.000"
            ),
            Ticket(
                jamBerangkat = "09.30",
                jamTiba = "12.30",
                stasiunAsal = "BDG",
                stasiunTujuan = "JKT",
                hargaTiket = "320.000"
            ),
            // ... tambahkan item tiket lainnya sesuai kebutuhan
        )
    }
}
