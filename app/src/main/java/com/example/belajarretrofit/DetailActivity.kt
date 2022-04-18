package com.example.belajarretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.belajarretrofit.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieId = intent.getIntExtra("movieId",-1)

        mainViewModel.detailMovie.observe(this){
            Glide.with(binding.imageView)
                .load("https://image.tmdb.org/t/p/w500"+it.posterPath)
                .into(binding.imageView)
            binding.textView.text = it.title
            binding.textView2.text = it.overview
        }

        mainViewModel.getDetailMovies(movieId)

    }
}