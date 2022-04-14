package com.example.belajarretrofit

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.belajarretrofit.databinding.MobilListLayoutBinding
import com.example.belajarretrofit.model.MobilResponseItem
import java.util.*


class MobilAdapter(private val onClick:(MobilResponseItem)->Unit)
    : ListAdapter<MobilResponseItem, MobilAdapter.ViewHolder>(MobilResponseItemComparator()) {


    class ViewHolder(private val binding: MobilListLayoutBinding): RecyclerView.ViewHolder(binding.root) {


        fun bind(currentMobilResponseItem: MobilResponseItem,
                 onClick: (MobilResponseItem) -> Unit){

            binding.apply {
                tvMobil.text = currentMobilResponseItem.name
                tvHarga.text = "Rp.${currentMobilResponseItem.price}"
                root.setOnClickListener {
                    onClick(currentMobilResponseItem)
                }
            }

        }

    }

    class MobilResponseItemComparator : DiffUtil.ItemCallback<MobilResponseItem>() {
        override fun areItemsTheSame(oldItem: MobilResponseItem, newItem: MobilResponseItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: MobilResponseItem, newItem: MobilResponseItem): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MobilListLayoutBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }

}