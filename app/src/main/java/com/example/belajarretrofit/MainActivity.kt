package com.example.belajarretrofit

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.belajarretrofit.databinding.ActivityMainBinding
import com.example.belajarretrofit.model.KlasemenItem
import com.example.belajarretrofit.model.MobilResponseItem
import com.example.belajarretrofit.model.Result

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity:: class.java)
            startActivity(intent)
        }

        mainViewModel.isLoading.observe(this){
            if (it){
//                binding.progressBar.visibility = View.VISIBLE
//                binding.shimmerContainer.tim
                binding.shimmerContainer.startShimmer()
            }else{
//                binding.progressBar.visibility = View.GONE
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.shimmerContainer.stopShimmer()
                    binding.shimmerContainer.isVisible = false
                },2000)
            }
        }


        mainViewModel.dataMovie.observe(this) {
            Handler(Looper.getMainLooper()).postDelayed({
                showListMovie(it.results)
            },2000)
        }

        mainViewModel.detailMovie.observe(this){
            Toast.makeText(this, it.originalTitle, Toast.LENGTH_SHORT).show()
        }

//        mainViewModel.getAllMovies()

        //data klasemen
//        fetchKlasemen()
        //data mobil
//        fetchData()
//        fetchMovie()

    }

    private fun showListMovie(results: List<Result>?) {
        val adapter= MovieAdapter {
            mainViewModel.getDetailMovies(it.id)
        }
        adapter.submitList(results)
        binding.recyclerView.adapter = adapter
    }

//    private fun fetchMovie() {
//        binding.progressBar.visibility = View.VISIBLE
//        ApiClient.instance.getAllMovies().enqueue(object : Callback<MovieResponse> {
//            override fun onResponse(
//                call: Call<MovieResponse>,
//                response: Response<MovieResponse>
//            ) {
//                val body = response.body()
//                val code = response.code()
//                if (code == 200){
//                    showListMovie(body?.results)
//                    binding.progressBar.visibility = View.GONE
//                }else{
//                    Toast.makeText(this@MainActivity, "$code", Toast.LENGTH_SHORT).show()
//                    binding.progressBar.visibility = View.GONE
//                }
//            }
//
//            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
//                binding.progressBar.visibility = View.GONE
//                Toast.makeText(this@MainActivity, "${t.localizedMessage}", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }



//    fun fetchKlasemen(){
//        binding.progressBar.visibility = View.VISIBLE
//        ApiClient.instance2.getKlasemen().enqueue(object : Callback<KlasemenResponse> {
//            override fun onResponse(
//                call: Call<KlasemenResponse>,
//                response: Response<KlasemenResponse>
//            ) {
//                val body = response.body()
//                val code = response.code()
//                if (code == 200){
//                    showListKlasemen(body?.data)
//                    binding.progressBar.visibility = View.GONE
//                }else{
//                    Toast.makeText(this@MainActivity, "$code", Toast.LENGTH_SHORT).show()
//                    binding.progressBar.visibility = View.GONE
//                }
//            }
//
//            override fun onFailure(call: Call<KlasemenResponse>, t: Throwable) {
//                binding.progressBar.visibility = View.GONE
//                Toast.makeText(this@MainActivity, "${t.localizedMessage}", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }

    private fun showListKlasemen(data: List<KlasemenItem>?) {
        val adapter= KlasemenAdapter {

        }
        adapter.submitList(data)
        binding.recyclerView.adapter = adapter
    }

//    private fun fetchData(){
//        binding.progressBar.visibility = View.VISIBLE
//        ApiClient.instance.getAllCar().enqueue(object : Callback<List<MobilResponseItem>> {
//            override fun onResponse(
//                call: Call<List<MobilResponseItem>>,
//                response: Response<List<MobilResponseItem>>
//            ) {
//                val body = response.body()
//                val code = response.code()
//                if (code == 200){
//                    showList(body)
//
//                }else{
//                    Toast.makeText(this@MainActivity, "$code", Toast.LENGTH_SHORT).show()
//
//                }
//                binding.progressBar.visibility = View.GONE
//            }
//
//            override fun onFailure(call: Call<List<MobilResponseItem>>, t: Throwable) {
//                binding.progressBar.visibility = View.GONE
//                Toast.makeText(this@MainActivity, "${t.localizedMessage}", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }

    private fun showList(data: List<MobilResponseItem>?) {
        val adapter= MobilAdapter {

        }
        adapter.submitList(data)
        binding.recyclerView.adapter = adapter
    }
}