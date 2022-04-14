package com.example.belajarretrofit

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.belajarretrofit.databinding.MobilListLayoutBinding
import com.example.belajarretrofit.model.KlasemenItem
import java.util.*


class KlasemenAdapter(private val onClick:(KlasemenItem)->Unit)
    : ListAdapter<KlasemenItem, KlasemenAdapter.ViewHolder>(KlasemenItemComparator()) {


    class ViewHolder(private val binding: MobilListLayoutBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(currentKlasemenItem: KlasemenItem,
                 onClick: (KlasemenItem) -> Unit){

            binding.apply {
                tvMobil.text = currentKlasemenItem.name
                tvHarga.text = currentKlasemenItem.abbr
                root.setOnClickListener {
                    onClick(currentKlasemenItem)
                }
            }

        }

    }

    class KlasemenItemComparator : DiffUtil.ItemCallback<KlasemenItem>() {
        override fun areItemsTheSame(oldItem: KlasemenItem, newItem: KlasemenItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: KlasemenItem, newItem: KlasemenItem): Boolean {
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