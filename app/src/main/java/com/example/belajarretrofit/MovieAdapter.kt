package com.example.belajarretrofit

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.belajarretrofit.databinding.MobilListLayoutBinding
import com.example.belajarretrofit.model.Result
import java.util.*


class MovieAdapter(private val onClick:(Result)->Unit)
    : ListAdapter<Result, MovieAdapter.ViewHolder>(ResultComparator()) {


    class ViewHolder(private val binding: MobilListLayoutBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(currentResult: Result,
                 onClick: (Result) -> Unit){

            binding.apply {
                tvMobil.text = currentResult.title
                tvHarga.text = currentResult.overview
                Glide.with(binding.ivPoster)
                    .load("https://image.tmdb.org/t/p/w500"+currentResult.posterPath)
                    .into(ivPoster)
                root.setOnClickListener {
                    onClick(currentResult)
                }
            }

        }

    }

    class ResultComparator : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
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