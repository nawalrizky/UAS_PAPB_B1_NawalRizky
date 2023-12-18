package com.example.travelapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.travelapp.databinding.FragmentBookBinding


class BookFragment : Fragment() {
    private lateinit var binding: FragmentBookBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookBinding.inflate(layoutInflater, container, false)

        // Set up spinner for "Asal" using data from string array
        val stasiunAsalAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.stasiun_kereta,
            android.R.layout.simple_spinner_item
        )
        stasiunAsalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerAsal.adapter = stasiunAsalAdapter

        // Set up spinner for "Tujuan" using data from string array
        val stasiunTujuanAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.stasiun_kereta,
            android.R.layout.simple_spinner_item
        )
        stasiunTujuanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerTujuan.adapter = stasiunTujuanAdapter

        // Set up spinner for "Kelas" using data from string array
        val kelasAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.kelas_kereta,
            android.R.layout.simple_spinner_item
        )
        kelasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerKelas.adapter = kelasAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPesanTiket.setOnClickListener {
            val stasiunAsal = binding.spinnerAsal.selectedItem.toString()
            val stasiunTujuan = binding.spinnerTujuan.selectedItem.toString()
            val kelasKereta = binding.spinnerKelas.selectedItem.toString()

            // Lakukan sesuatu dengan data yang dipilih, misalnya navigasi ke halaman selanjutnya
        }
    }
}
