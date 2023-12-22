package com.example.travelapp.admin

import AppDatabase
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.travelapp.databinding.ActivityAddTicketBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.example.travelapp.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.UUID

class AddTicketActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    private lateinit var binding: ActivityAddTicketBinding
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTicketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddTicket.setOnClickListener {
            saveDataToFirestore()
            saveDataToRoom()
        }
    }

    private fun saveDataToFirestore() {
        val stasiunKeberangkatan = binding.stasiunKeberangkatan.text.toString()
        val stasiunTujuan = binding.stasiunTujuan.text.toString()
        val kodeStasiunKeberangkatan = binding.kodeStasiunKeberangkatan.text.toString()
        val kodeStasiunTujuan = binding.kodeStasiunTujuan.text.toString()
        val jamBerangkat = binding.jamBerangkat.text.toString()
        val jamTiba = binding.jamTiba.text.toString()
        val durasiPerjalanan = binding.durasiPerjalanan.text.toString()
        val tanggalBerangkat = binding.tanggalBerangkat.text.toString()
        val namaKereta = binding.namaKereta.text.toString()
        val hargaTiket = binding.hargaTiket.text.toString()

        val ticket = hashMapOf(
            "stasiunKeberangkatan" to stasiunKeberangkatan,
            "stasiunTujuan" to stasiunTujuan,
            "kodeStasiunKeberangkatan" to kodeStasiunKeberangkatan,
            "kodeStasiunTujuan" to kodeStasiunTujuan,
            "jamBerangkat" to jamBerangkat,
            "jamTiba" to jamTiba,
            "durasiPerjalanan" to durasiPerjalanan,
            "tanggalBerangkat" to tanggalBerangkat,
            "namaKereta" to namaKereta,
            "hargaTiket" to hargaTiket
        )

        firestore.collection("dataKereta")
            .add(ticket)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(this, "Ticket saved to Firestore with ID: ${documentReference.id}", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error saving ticket to Firestore: $e", Toast.LENGTH_SHORT).show()
            }
    }

    private fun saveDataToRoom() {
        val stasiunKeberangkatan = binding.stasiunKeberangkatan.text.toString()
        val stasiunTujuan = binding.stasiunTujuan.text.toString()
        val kodeStasiunKeberangkatan = binding.kodeStasiunKeberangkatan.text.toString()
        val kodeStasiunTujuan = binding.kodeStasiunTujuan.text.toString()
        val jamBerangkat = binding.jamBerangkat.text.toString()
        val jamTiba = binding.jamTiba.text.toString()
        val durasiPerjalanan = binding.durasiPerjalanan.text.toString()
        val tanggalBerangkat = binding.tanggalBerangkat.text.toString()
        val namaKereta = binding.namaKereta.text.toString()
        val hargaTiket = binding.hargaTiket.text.toString()

        val ticket = Kereta(
            id = UUID.randomUUID().toString(),
            stasiunKeberangkatan = stasiunKeberangkatan,
            stasiunTujuan = stasiunTujuan,
            kodeStasiunKeberangkatan = kodeStasiunKeberangkatan,
            kodeStasiunTujuan = kodeStasiunTujuan,
            jamBerangkat = jamBerangkat,
            jamTiba = jamTiba,
            durasiPerjalanan = durasiPerjalanan,
            tanggalBerangkat = tanggalBerangkat,
            namaKereta = namaKereta,
            harga = hargaTiket
        )

        if(this@AddTicketActivity != null) {
            GlobalScope.launch(Dispatchers.IO) {
                AppDatabase.getInstance(this@AddTicketActivity)?.keretaDao()?.upsert(ticket)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@AddTicketActivity, "Ticket saved to Room", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()

        val datePickerDialog = DatePickerDialog(
            this,
            this,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000
        datePickerDialog.show()
    }



    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val selectedDate = dateFormat.format(calendar.time)

        binding.tanggalBerangkat.setText(selectedDate)
    }

}
