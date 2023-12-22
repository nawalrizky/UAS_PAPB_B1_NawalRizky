package com.example.travelapp.admin

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travelapp.R
import com.example.travelapp.database.kereta.dataKereta
import com.example.travelapp.databinding.ItemTicketAdminBinding
import com.google.firebase.firestore.FirebaseFirestore

class KeretaAdminAdapter(private val context: Context, private val arrayList: ArrayList<dataKereta>) :
    RecyclerView.Adapter<KeretaAdminAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemTicketAdminBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ticket_admin, parent, false)
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

        holder.binding.btnEdit.setOnClickListener {
            openAddTicketActivity(currentDataKereta)
        }

        holder.binding.btnHapus.setOnClickListener {
            deleteKereta(position)
        }


    }


    private fun openAddTicketActivity(selectedKereta: dataKereta) {
        val intent = Intent(context, AddTicketActivity::class.java)
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

        context.startActivity(intent)
    }

    private fun deleteKereta(position: Int) {
        val kereta = arrayList[position]
        val db = FirebaseFirestore.getInstance()

        // Use the id property to delete the document
        db.collection("dataKereta")
            .document(kereta.id)
            .delete()
            .addOnSuccessListener {
                Log.d("KeretaAdminAdapter", "DocumentSnapshot successfully deleted!")
                // After deletion, you may want to update your local ArrayList and notify the adapter
                arrayList.removeAt(position)
                notifyItemRemoved(position)
            }
            .addOnFailureListener { e ->
                Log.w("KeretaAdminAdapter", "Error deleting document", e)
            }
    }




    override fun getItemCount(): Int {
        return arrayList.size
    }
}
