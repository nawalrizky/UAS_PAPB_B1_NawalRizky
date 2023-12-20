package com.example.travelapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TicketAdapter(
    private val ticketList: List<Ticket>,
    private val isPurchaseButtonVisible: Boolean = true
) :
    RecyclerView.Adapter<TicketAdapter.TicketViewHolder>() {

    private var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onBeliClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    class TicketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val jamBerangkat: TextView = itemView.findViewById(R.id.jam_keberangkatan)
        val jamTiba: TextView = itemView.findViewById(R.id.jam_tiba)
        val stasiunAsal: TextView = itemView.findViewById(R.id.kota_keberangkatan)
        val stasiunTujuan: TextView = itemView.findViewById(R.id.kota_tiba)
        val hargaTiket: TextView = itemView.findViewById(R.id.harga_tiket)
        val btnBeli: Button = itemView.findViewById(R.id.btn_beli)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ticket_book, parent, false)

        return TicketViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        val currentItem = ticketList[position]

        holder.jamBerangkat.text = currentItem.jamBerangkat
        holder.jamTiba.text = currentItem.jamTiba
        holder.stasiunAsal.text = currentItem.stasiunAsal
        holder.stasiunTujuan.text = currentItem.stasiunTujuan
        holder.hargaTiket.text = currentItem.hargaTiket

        if (isPurchaseButtonVisible) {
            holder.btnBeli.visibility = View.VISIBLE
        } else {
            holder.btnBeli.visibility = View.GONE
        }

        holder.btnBeli.setOnClickListener {
            onItemClickListener?.onBeliClick(position)
        }
    }


    override fun getItemCount(): Int {
        return ticketList.size
    }
}
