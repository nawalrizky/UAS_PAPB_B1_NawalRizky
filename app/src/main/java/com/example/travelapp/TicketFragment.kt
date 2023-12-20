package com.example.travelapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelapp.databinding.FragmentTicketBinding

class TicketFragment : Fragment() {

    private lateinit var binding: FragmentTicketBinding
    private val bookedTicketList = mutableListOf<Ticket>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTicketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Assume you have a function to get booked tickets
        bookedTicketList.addAll(getBookedTickets())

        val adapter = TicketAdapter(bookedTicketList)
        binding.rvCardTicket.adapter = adapter
        binding.rvCardTicket.layoutManager = LinearLayoutManager(context)
    }

    // Sample function to get booked tickets
    private fun getBookedTickets(): List<Ticket> {
        return listOf(
            Ticket(
                jamBerangkat = "08.00",
                jamTiba = "11.00",
                stasiunAsal = "JKT",
                stasiunTujuan = "BDG",
                hargaTiket = "150.000"
            ),
            // Add other booked tickets as needed
        )
    }
}
