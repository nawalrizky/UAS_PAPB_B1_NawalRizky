package com.example.travelapp.dialog.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travelapp.api.stasiun.Stasiun
import com.example.travelapp.databinding.ItemSimpleCardForRecyclerBinding

typealias onClickItemStasiunListener = (Stasiun) -> Unit

class StasiunAdapter(
    private val stasiunList: List<Stasiun>,
    private val onClickItemListener: onClickItemStasiunListener
) : RecyclerView.Adapter<StasiunAdapter.StasiunViewHolder>() {

    inner class StasiunViewHolder(
        private val binding: ItemSimpleCardForRecyclerBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(stasiun: Stasiun) {
            Log.d("StasiunAdapter", "bind: ${stasiun.toString()}")

            with(binding) {
                textViewItemCardOption.text = stasiun.name

                cardViewItemCardOption.setOnClickListener {
                    onClickItemListener(stasiun)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StasiunViewHolder {
        val binding = ItemSimpleCardForRecyclerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StasiunViewHolder(binding)
    }

    override fun getItemCount(): Int = stasiunList.size

    override fun onBindViewHolder(holder: StasiunViewHolder, position: Int) {
        holder.bind(stasiunList[position])
    }
}
