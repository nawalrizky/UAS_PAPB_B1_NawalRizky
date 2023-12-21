package com.example.travelapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelapp.database.pesanan.PesananAdapter
import com.example.travelapp.database.pesanan.dataPesanan
import com.example.travelapp.databinding.FragmentHistoryBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private lateinit var pesananAdapter: PesananAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)

        // Initialize RecyclerView and Adapter
        pesananAdapter = PesananAdapter(requireContext())
        binding.rvCardTicket.layoutManager = LinearLayoutManager(context)
        binding.rvCardTicket.adapter = pesananAdapter

        // Fetch history pesanan data from Firestore
        getHistoryPesananData()

        return binding.root
    }

    // Function to fetch history pesanan data from Firestore
    private fun getHistoryPesananData() {
        val historyPesananList = mutableListOf<dataPesanan>()

        // Get reference to Firestore instance
        val db = FirebaseFirestore.getInstance()

        // Replace "pesanan" with the actual name of your collection in Firestore
        db.collection("pesanan")
            .get()
            .addOnSuccessListener { result ->
                val currentDate = Calendar.getInstance().time

                for (document in result) {
                    // Convert Firestore document to your dataPesanan object
                    val pesanan = document.toObject(dataPesanan::class.java)

                    // Convert tanggalBerangkat to Date
                    val tanggalBerangkatDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                        .parse(pesanan.tanggalBerangkat)

                    // Check if the tanggalBerangkat is before currentDate
                    if (tanggalBerangkatDate != null && tanggalBerangkatDate < currentDate) {
                        historyPesananList.add(pesanan)
                    }
                }

                // Update the adapter with the fetched data
                pesananAdapter.submitList(historyPesananList)
            }
            .addOnFailureListener { exception ->
                // Handle errors here
                // You might want to log the error or display a message to the user
            }
    }
}
