package com.example.travelapp

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import com.example.travelapp.api.stasiun.Stasiun
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.travelapp.databinding.FragmentBookBinding
import com.example.travelapp.dialog.StasiunFragment
import com.example.travelapp.dialog.viewmodel.StasiunSpinnerViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class BookFragment : Fragment(), DatePickerDialog.OnDateSetListener {
    private lateinit var binding: FragmentBookBinding
    private var isStasiunAsalSelected = false
    private var isStasiunTujuanSelected = false
    private val stasiunViewModel: StasiunSpinnerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookBinding.inflate(layoutInflater, container, false)

        // Set up spinner for "Kelas" using data from string array
        val kelasAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.kelas_kereta,
            android.R.layout.simple_spinner_item
        )
        kelasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerKelas.adapter = kelasAdapter

        val penumpangAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.penumpang,
            android.R.layout.simple_spinner_item
        )
        penumpangAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerPenumpang.adapter = penumpangAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCariTiket.setOnClickListener {
            val stasiunAsal = binding.buttonAsal.text.toString()
            val stasiunTujuan = binding.buttonTujuan.text.toString()
            val kelasKereta = binding.spinnerKelas.selectedItem.toString()

            // Lakukan sesuatu dengan data yang dipilih, misalnya navigasi ke halaman selanjutnya
        }

        binding.editTxtTanggal.setOnClickListener {
            showDatePickerDialog()
        }
        binding.btnSwap.setOnClickListener {
            // Memperoleh teks dari button asal dan tujuan
            val asalText = binding.buttonAsal.text.toString()
            val tujuanText = binding.buttonTujuan.text.toString()

            // Mengatur teks button asal dan tujuan
            binding.buttonAsal.text = tujuanText
            binding.buttonTujuan.text = asalText
        }


        binding.editTxtTanggal.setOnClickListener {
            showDatePickerDialog()
        }
        binding.btnCariTiket.setOnClickListener {
            // Intent untuk berpindah ke ListTicketActivity
            val intent = Intent(requireContext(), ListTicketActivity::class.java)
            startActivity(intent)
        }

        stasiunAsalSpinner()
        stasiunTujuanSpinner()
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()

        val datePickerDialog = DatePickerDialog(
            requireContext(),
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

        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val selectedDate = dateFormat.format(calendar.time)

        binding.editTxtTanggal.setText(selectedDate)
    }



    private fun stasiunAsalSpinner() {
        binding.buttonAsal.setOnClickListener {
            StasiunFragment().show(parentFragmentManager, "StasiunFragment")
            isStasiunAsalSelected = true
            Log.d("BookFragment", "stasiunAsalSpinner. Asal is selected :$isStasiunAsalSelected")
        }


    }

    private fun stasiunTujuanSpinner() {
        binding.buttonTujuan.setOnClickListener {
            StasiunFragment().show(parentFragmentManager, "StasiunFragment")
            isStasiunTujuanSelected = true
            Log.d("BookFragment", "stasiunTujuanSpinner. Tujuan is selected :$isStasiunTujuanSelected")
        }


    }
}


