package com.example.travelapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelapp.database.kereta.DataKeretaGenerator
import com.example.travelapp.database.kereta.KeretaAdapter
import com.example.travelapp.database.kereta.dataKereta
import com.example.travelapp.databinding.ActivityListTicketBinding
import com.google.firebase.firestore.FirebaseFirestore

class ListTicketActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListTicketBinding
    private val db = FirebaseFirestore.getInstance()

    private fun updateResultText(filteredTickets: List<dataKereta>) {
        val resultText = "Showing ${filteredTickets.size} Results"
        binding.textResult.text = resultText
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListTicketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val backButton: ImageButton = findViewById(R.id.btn_back)
        val stasiunAsal: String? = intent.getStringExtra("stasiunAsal")
        val stasiunTujuan: String? = intent.getStringExtra("stasiunTujuan")
        val kelasKereta: String? = intent.getStringExtra("kelasKereta")
        val selectedDate: String? = intent.getStringExtra("selectedDate")


        backButton.setOnClickListener {
            // Panggil fungsi onBackPressed untuk kembali ke fragment sebelumnya atau activity sebelumnya
            onBackPressed()
        }
        Log.d(
            "ListTicketActivity",
            "Received intent with stasiunAsal: $stasiunAsal, stasiunTujuan: $stasiunTujuan, kelasKereta: $kelasKereta, selectedDate: $selectedDate"
        )

        val layoutManager = LinearLayoutManager(this)

        binding.rvCardTicketSearch.layoutManager = layoutManager
        val keretaList: List<dataKereta> = DataKeretaGenerator.generateSampleData()

        for (kereta in keretaList) {
            db.collection("dataKereta")
                .add(kereta)
                .addOnSuccessListener { documentReference ->
                    Log.d("Firestore", "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w("Firestore", "Error adding document", e)
                }
        }
        db.collection("dataKereta").get()
            .addOnSuccessListener { result ->
                Log.d("Firestore", "Success getting documents")
                val keretaList = result.documents.mapNotNull { it.toObject(dataKereta::class.java) }
                Log.d("Firestore", "All keretaList: $keretaList")

                val filteredKeretaList = ArrayList(keretaList.filter {
                    Log.d("Firestore", "Data: ${it.stasiunKeberangkatan}, ${it.stasiunTujuan}, ${it.tanggalBerangkat}, ${it.kelasKereta}")

                    val isStasiunMatch = it.stasiunKeberangkatan.equals(stasiunAsal, ignoreCase = true) &&
                            it.stasiunTujuan.equals(stasiunTujuan, ignoreCase = true) &&
                            it.tanggalBerangkat == selectedDate

                    val isKelasKeretaMatch = if (kelasKereta == "Semua") {
                        true // Show all tickets for any class
                    } else {
                        it.kelasKereta?.startsWith(kelasKereta ?: "", ignoreCase = true) ?: false
                    }



                    isStasiunMatch && isKelasKeretaMatch
                })


                Log.d("Firestore", "Filtered keretaList: $filteredKeretaList")

                val keretaAdapter = KeretaAdapter(this, filteredKeretaList)
                binding.rvCardTicketSearch.adapter = keretaAdapter

                // Setelah filtering, panggil fungsi updateResultText dengan list tiket yang sudah difilter
                updateResultText(filteredKeretaList)
            }
            .addOnFailureListener { exception ->
                Log.w("Firestore", "Error getting documents: ", exception)
            }

    }
}