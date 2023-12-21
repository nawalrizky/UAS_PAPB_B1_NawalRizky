package com.example.travelapp.database.kereta

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travelapp.BookDetailActivity
import com.example.travelapp.R
import com.example.travelapp.databinding.ItemTicketBookBinding

class KeretaAdapter(private val context: Context, private val arrayList: ArrayList<dataKereta>) : RecyclerView.Adapter<KeretaAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemTicketBookBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ticket_book, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentDataKereta = arrayList[position]

        // Set your data to the views using the ViewHolder's binding
        holder.binding.hargaTiket.text = currentDataKereta.harga
        holder.binding.kodeStasiunKeberangkatan.text = currentDataKereta.kodeStasiunKeberangkatan
        holder.binding.jamKeberangkatan.text = currentDataKereta.jamBerangkat
        holder.binding.namaKereta.text = currentDataKereta.namaKereta
        holder.binding.kodeStasiunTujuan.text = currentDataKereta.kodeStasiunTujuan
        holder.binding.jamTiba.text = currentDataKereta.jamTiba
        holder.binding.kelasKereta.text = currentDataKereta.kelasKereta
        holder.binding.waktuPerjalanan.text = currentDataKereta.durasiPerjalanan

        holder.binding.btnBeli.setOnClickListener {
            // Panggil fungsi untuk memulai BookDetailActivity dengan data tiket
            openBookDetailActivity(currentDataKereta)
        }
    }
    private fun openBookDetailActivity(selectedKereta: dataKereta) {
        val intent = Intent(context, BookDetailActivity::class.java)
        // Kirim data tiket ke BookDetailActivity
        intent.putExtra("harga", selectedKereta.harga)
        intent.putExtra("kelas", selectedKereta.kelasKereta)
        intent.putExtra("namaKereta", selectedKereta.namaKereta)
        intent.putExtra("kodeStasiunKeberangkatan", selectedKereta.kodeStasiunKeberangkatan)
        intent.putExtra("stasiunKeberangkatan", selectedKereta.stasiunKeberangkatan)
        intent.putExtra("jamBerangkat", selectedKereta.jamBerangkat)
        intent.putExtra("kodeStasiunTujuan", selectedKereta.kodeStasiunTujuan)
        intent.putExtra("stasiunTujuan", selectedKereta.stasiunTujuan)
        intent.putExtra("jamTiba", selectedKereta.jamTiba)
        intent.putExtra("durasiPerjalanan", selectedKereta.durasiPerjalanan)
        intent.putExtra("tanggalBerangkat", selectedKereta.tanggalBerangkat)

        // Mulai aktivitas BookDetailActivity
        context.startActivity(intent)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}
