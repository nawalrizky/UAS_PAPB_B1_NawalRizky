package com.example.travelapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travelapp.databinding.ActivityBookedTicketDetailBinding

class BookedTicketDetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBookedTicketDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookedTicketDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack3.setOnClickListener {
           finish()
        }

        val namaPenumpang = intent.getStringExtra("NAMA_PENUMPANG")
        val nomorIdentitas = intent.getStringExtra("NOMOR_IDENTITAS")
        val harga = intent.getStringExtra("HARGA")
        val kelas = intent.getStringExtra("KELAS")
        val namaKereta = intent.getStringExtra("NAMA_KERETA")
        val kodeStasiunKeberangkatan = intent.getStringExtra("KODE_STASIUN_KEBERANGKATAN")
        val jamBerangkat = intent.getStringExtra("JAM_KEBERANGKAT")
        val kodeStasiunTujuan = intent.getStringExtra("KODE_STASIUN_TUJUAN")
        val jamTiba = intent.getStringExtra("JAM_TIBA")
        val tanggalBerangkat = intent.getStringExtra("TANGGAL_BERANGKAT")


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