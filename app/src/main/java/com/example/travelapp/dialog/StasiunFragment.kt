package com.example.travelapp.dialog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelapp.api.stasiun.Stasiun
import com.example.travelapp.databinding.FragmentBottomRecyclerBinding
import com.example.travelapp.dialog.adapter.StasiunAdapter
import com.example.travelapp.dialog.repository.StasiunRepository
import com.example.travelapp.dialog.viewmodel.StasiunSpinnerViewModel
import com.example.travelapp.dialog.viewmodel.StasiunSpinnerViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StasiunFragment : BottomSheetDialogFragment() {
    internal var stasiunSelectionListener: StasiunSelectionListener? = null
    private var selectedStasiun: Stasiun? = null
    private val binding by lazy {
        FragmentBottomRecyclerBinding.inflate(layoutInflater)
    }


    private lateinit var stasiunViewModel: StasiunSpinnerViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textViewSheetTitle.text = "Pilih Stasiun"


        // Create StasiunRepository
        val stasiunRepository = StasiunRepository()

        // Create StasiunSpinnerViewModel with StasiunRepository
        stasiunViewModel = ViewModelProvider(
            this,
            StasiunSpinnerViewModelFactory(stasiunRepository)
        ).get(StasiunSpinnerViewModel::class.java)

        fetchStasiunApi()
    }

    private fun fetchStasiunApi() {

        val stasiunRepository = StasiunRepository()

        stasiunRepository.getStations(object : Callback<List<Stasiun>> {
            override fun onResponse(call: Call<List<Stasiun>>, response: Response<List<Stasiun>>) {
                if (response.isSuccessful) {
                    Log.d("StasiunSheetFragment", "onResponse: ${response.body()}")
                    val data = response.body()

                    if (data != null) {
                        setupRecycler(data)
                        binding.progressIndicatorSheet.visibility = View.GONE

                        // Set the first station as the selected station initially
                        if (data.isNotEmpty()) {
                            selectedStasiun = data[0]
                        }
                    } else {
                        Log.w("StasiunSheetFragment", "onResponse: data is null")
                    }

                } else {
                    Log.e("StasiunSheetFragment", "onResponse: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<List<Stasiun>>, t: Throwable) {
                Log.e("StasiunSheetFragment", "onFailure: ${t.message}", t)
            }
        })
    }

    private fun setupRecycler(stasiunList: List<Stasiun>) {
        // Sort the stasiunList in ascending order by station name
        val sortedStasiunList = stasiunList.sortedBy { it.name }

        with(binding) {
            recyclerViewSheet.apply {
                adapter = StasiunAdapter(
                    stasiunList = sortedStasiunList,
                    onClickItemListener = {
                        Log.d("StasiunSheetFragment", "setupRecycler: $it")

                        // Set the selected station's name to the button
                        stasiunSelectionListener?.onStasiunSelected(it)
                        dismiss()
                    }
                )

                layoutManager = LinearLayoutManager(requireActivity())
            }
        }
    }
}