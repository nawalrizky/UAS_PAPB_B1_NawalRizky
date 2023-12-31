package com.example.travelapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.travelapp.databinding.ActivityTicketDetailBinding

class TicketDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTicketDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicketDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnBack3.setOnClickListener {
            // Create an intent to go back to MainActivity
            val intent = Intent(this, MainActivity::class.java)

            // Add any additional data you want to pass to MainActivity
            // For example, you might want to indicate that it's coming from TicketDetailActivity
            intent.putExtra("FROM_TICKET_DETAIL", true)

            // Start the MainActivity and clear the back stack
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        // Retrieve data from intent
        val namaPenumpang = intent.getStringExtra("NAMA_PENUMPANG")
        val nomorIdentitas = intent.getStringExtra("NOMOR_IDENTITAS")
        val harga = intent.getStringExtra("HARGA")
        val kelas = intent.getStringExtra("KELAS")
        val namaKereta = intent.getStringExtra("NAMA_KERETA")
        val kodeStasiunKeberangkatan = intent.getStringExtra("KODE_STASIUN_KEBERANGKATAN")
        val stasiunKeberangkatan = intent.getStringExtra("STASIUN_KEBERANGKATAN")
        val jamBerangkat = intent.getStringExtra("JAM_KEBERANGKAT")
        val kodeStasiunTujuan = intent.getStringExtra("KODE_STASIUN_TUJUAN")
        val stasiunTujuan = intent.getStringExtra("STASIUN_TUJUAN")
        val jamTiba = intent.getStringExtra("JAM_TIBA")
        val durasiPerjalanan = intent.getStringExtra("DURASI_PERJALANAN")
        val tanggalBerangkat = intent.getStringExtra("TANGGAL_BERANGKAT")

        // Use the data as needed in your UI or business logic
        // For example, set text to TextViews in your layout
        binding.namaPenumpang.text = namaPenumpang
        binding.nomorIdentitas.text = nomorIdentitas
        binding.harga.text = harga
        binding.kelasKereta.text = kelas
        binding.namaKereta.text = namaKereta
        binding.kodeStasiunKeberangkatan.text = kodeStasiunKeberangkatan
        binding.jamBerangkat.text = jamBerangkat
        binding.jamKeberangkatan.text = jamBerangkat
        binding.kodeStasiunTujuan.text = kodeStasiunTujuan
        binding.jamTiba.text = jamTiba
        binding.tanggalBerangkat.text = tanggalBerangkat
    }
}
