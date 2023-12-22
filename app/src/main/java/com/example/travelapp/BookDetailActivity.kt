package com.example.travelapp

import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.travelapp.databinding.ActivityBookDetailBinding
import com.google.firebase.firestore.FirebaseFirestore

class BookDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookDetailBinding
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Get the data passed from the KeretaAdapter
        val harga = intent.getStringExtra("harga")
        val kelas = intent.getStringExtra("kelas")
        val namaKereta = intent.getStringExtra("namaKereta")
        val kodeStasiunKeberangkatan = intent.getStringExtra("kodeStasiunKeberangkatan")
        val stasiunKeberangkatan = intent.getStringExtra("stasiunKeberangkatan")
        val jamBerangkat = intent.getStringExtra("jamBerangkat")
        val kodeStasiunTujuan = intent.getStringExtra("kodeStasiunTujuan")
        val stasiunTujuan = intent.getStringExtra("stasiunTujuan")
        val jamTiba = intent.getStringExtra("jamTiba")
        val durasiPerjalanan = intent.getStringExtra("durasiPerjalanan")
        val tanggalBerangkat = intent.getStringExtra("tanggalBerangkat")
        val editTextNamaPenumpang = findViewById<EditText>(R.id.editTextNamaPenumpang)
        val editTextNomorIdentitas = findViewById<EditText>(R.id.editTextNomorIdentitas)

        // Find the button


        // Use the data to populate the UI
        binding.hargaTiket.text = harga
        binding.kelasKereta.text = kelas
        binding.namaKereta.text = namaKereta
        binding.kotaKeberangkatan.text = kodeStasiunKeberangkatan
        binding.jamKeberangkatan.text = jamBerangkat
        binding.kotaTiba.text = kodeStasiunTujuan
        binding.jamTiba.text = jamTiba
        binding.durasi.text = durasiPerjalanan
        binding.totalHarga.text = harga

        binding.btnBack2.setOnClickListener {
            // Kembali ke halaman sebelumnya
            finish()
        }
        binding.btnBatal.setOnClickListener {
            // Kembali ke halaman sebelumnya
            finish()
        }
        binding.btnPesan.setOnClickListener {
            // Get the text from the EditText views
            val namaPenumpang = editTextNamaPenumpang.text.toString()
            val nomorIdentitas = editTextNomorIdentitas.text.toString()

            // Get other data from intent
            val harga = intent.getStringExtra("harga")
            val kelas = intent.getStringExtra("kelas")
            val namaKereta = intent.getStringExtra("namaKereta")
            val kodeStasiunKeberangkatan = intent.getStringExtra("kodeStasiunKeberangkatan")
            val stasiunKeberangkatan = intent.getStringExtra("stasiunKeberangkatan")
            val jamBerangkat = intent.getStringExtra("jamBerangkat")
            val kodeStasiunTujuan = intent.getStringExtra("kodeStasiunTujuan")
            val stasiunTujuan = intent.getStringExtra("stasiunTujuan")
            val jamTiba = intent.getStringExtra("jamTiba")
            val durasiPerjalanan = intent.getStringExtra("durasiPerjalanan")
            val tanggalBerangkat = intent.getStringExtra("tanggalBerangkat")

            // Create a map to store the data
            val pesananData = hashMapOf(
                "namaPenumpang" to namaPenumpang,
                "nomorIdentitas" to nomorIdentitas,
                "harga" to harga,
                "kelas" to kelas,
                "namaKereta" to namaKereta,
                "kodeStasiunKeberangkatan" to kodeStasiunKeberangkatan,
                "stasiunKeberangkatan" to stasiunKeberangkatan,
                "jamBerangkat" to jamBerangkat,
                "kodeStasiunTujuan" to kodeStasiunTujuan,
                "stasiunTujuan" to stasiunTujuan,
                "jamTiba" to jamTiba,
                "durasiPerjalanan" to durasiPerjalanan,
                "tanggalBerangkat" to tanggalBerangkat
            )

            // Add data to the "pesanan" collection
            db.collection("pesanan")
                .add(pesananData)
                .addOnSuccessListener { documentReference ->
                    println("DocumentSnapshot added with ID: ${documentReference.id}")

                }
                .addOnFailureListener { e ->
                    // Handle failure, e.g., show an error message
                    println("Error adding document: $e")
                }



            val intent = Intent(this, TicketDetailActivity::class.java)

            // Pass the data to the next activity
            intent.putExtra("NAMA_PENUMPANG", namaPenumpang)
            intent.putExtra("NOMOR_IDENTITAS", nomorIdentitas)
            intent.putExtra("HARGA", harga)
            intent.putExtra("KELAS", kelas)
            intent.putExtra("NAMA_KERETA", namaKereta)
            intent.putExtra("KODE_STASIUN_KEBERANGKATAN", kodeStasiunKeberangkatan)
            intent.putExtra("STASIUN_KEBERANGKATAN", stasiunKeberangkatan)
            intent.putExtra("JAM_KEBERANGKAT", jamBerangkat)
            intent.putExtra("KODE_STASIUN_TUJUAN", kodeStasiunTujuan)
            intent.putExtra("STASIUN_TUJUAN", stasiunTujuan)
            intent.putExtra("JAM_TIBA", jamTiba)
            intent.putExtra("DURASI_PERJALANAN", durasiPerjalanan)
            intent.putExtra("TANGGAL_BERANGKAT", tanggalBerangkat)

            // Start the TicketDetailActivity
            startActivity(intent)
        }
    }


}
