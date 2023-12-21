package com.example.travelapp.database.pesanan

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travelapp.BookedTicketDetailActivity
import com.example.travelapp.databinding.ItemTicketDetailBinding

class PesananAdapter(private val context: Context) : RecyclerView.Adapter<PesananAdapter.PesananViewHolder>() {

    private var pesananList: List<dataPesanan> = listOf()

    fun submitList(newList: List<dataPesanan>) {
        pesananList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PesananViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTicketDetailBinding.inflate(inflater, parent, false)
        return PesananViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PesananViewHolder, position: Int) {
        val pesanan = pesananList[position]
        holder.bind(pesanan)
    }

    override fun getItemCount(): Int {
        return pesananList.size
    }

    // Inside PesananAdapter
    inner class PesananViewHolder(private val binding: ItemTicketDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            // Add click listener to the Detail button
            binding.btnDetail.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    // Get the selected pesanan
                    val pesanan = pesananList[position]

                    // Create an intent to start BookedTicketDetailActivity
                    val intent = Intent(context, BookedTicketDetailActivity::class.java).apply {
                        // Pass data to BookedTicketDetailActivity
                        putExtra("HARGA", pesanan.harga)
                        putExtra("KELAS", pesanan.kelas)
                        putExtra("NAMA_KERETA", pesanan.namaKereta)
                        putExtra("JAM_KEBERANGKAT", pesanan.jamBerangkat)
                        putExtra("JAM_TIBA", pesanan.jamTiba)
                        putExtra("KODE_STASIUN_KEBERANGKATAN", pesanan.kodeStasiunKeberangkatan)
                        putExtra("KODE_STASIUN_TUJUAN", pesanan.kodeStasiunTujuan)
                        putExtra("NAMA_PENUMPANG", pesanan.namaPenumpang)
                        putExtra("NOMOR_IDENTITAS", pesanan.nomorIdentitas)
                        putExtra("TANGGAL_BERANGKAT", pesanan.tanggalBerangkat)

                    }

                    // Start the BookedTicketDetailActivity
                    context.startActivity(intent)
                }
            }
        }

        fun bind(pesanan: dataPesanan) {
            // Bind data to the view
            binding.hargaTiket.text = pesanan.harga
            binding.kelasKereta.text = pesanan.kelas
            binding.namaKereta.text = pesanan.namaKereta
            binding.jamKeberangkatan.text = pesanan.jamBerangkat
            binding.jamTiba.text = pesanan.jamTiba
            binding.kodeStasiunKeberangkatan.text = pesanan.kodeStasiunKeberangkatan
            binding.kodeStasiunTujuan.text = pesanan.kodeStasiunTujuan
        }
    }

}
