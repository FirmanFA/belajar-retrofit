package com.example.belajarretrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.belajarretrofit.databinding.ActivityMainBinding
import com.example.belajarretrofit.model.KlasemenItem
import com.example.belajarretrofit.model.KlasemenResponse
import com.example.belajarretrofit.model.MobilResponseItem
import com.example.belajarretrofit.model.MovieResponse
import com.example.belajarretrofit.model.Result
import com.example.belajarretrofit.service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity:: class.java)
            startActivity(intent)
        }

        mainViewModel.isLoading.observe(this){
            if (it){
                binding.progressBar.visibility = View.VISIBLE
            }else{
                binding.progressBar.visibility = View.GONE
            }
        }

        mainViewModel.dataMovie.observe(this) {
            showListMovie(it.results)
        }

        mainViewModel.getAllMovies()

        //data klasemen
//        fetchKlasemen()
        //data mobil
//        fetchData()
//        fetchMovie()

    }

    private fun showListMovie(results: List<Result>?) {
        val adapter= MovieAdapter {

        }
        adapter.submitList(results)
        binding.recyclerView.adapter = adapter
    }

    private fun fetchMovie() {
        binding.progressBar.visibility = View.VISIBLE
        ApiClient.instance.getAllMovies().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                val body = response.body()
                Toast.makeText(this@MainActivity, response.headers().toString(), Toast.LENGTH_SHORT).show()
                val code = response.code()
                if (code == 200){
                    showListMovie(body?.results)
                    binding.progressBar.visibility = View.GONE
                }else{
                    Toast.makeText(this@MainActivity, "$code", Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(this@MainActivity, "${t.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
        })
    }



    fun fetchKlasemen(){
        binding.progressBar.visibility = View.VISIBLE
        ApiClient.instance2.getKlasemen().enqueue(object : Callback<KlasemenResponse> {
            override fun onResponse(
                call: Call<KlasemenResponse>,
                response: Response<KlasemenResponse>
            ) {
                val body = response.body()
                val code = response.code()
                if (code == 200){
                    showListKlasemen(body?.data)
                    binding.progressBar.visibility = View.GONE
                }else{
                    Toast.makeText(this@MainActivity, "$code", Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<KlasemenResponse>, t: Throwable) {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(this@MainActivity, "${t.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showListKlasemen(data: List<KlasemenItem>?) {
        val adapter= KlasemenAdapter {

        }
        adapter.submitList(data)
        binding.recyclerView.adapter = adapter
    }

    private fun fetchData(){
        binding.progressBar.visibility = View.VISIBLE
        ApiClient.instance.getAllCar().enqueue(object : Callback<List<MobilResponseItem>> {
            override fun onResponse(
                call: Call<List<MobilResponseItem>>,
                response: Response<List<MobilResponseItem>>
            ) {
                val body = response.body()
                val code = response.code()
                if (code == 200){
                    showList(body)

                }else{
                    Toast.makeText(this@MainActivity, "$code", Toast.LENGTH_SHORT).show()

                }
                binding.progressBar.visibility = View.GONE
            }

            override fun onFailure(call: Call<List<MobilResponseItem>>, t: Throwable) {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(this@MainActivity, "${t.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showList(data: List<MobilResponseItem>?) {
        val adapter= MobilAdapter {

        }
        adapter.submitList(data)
        binding.recyclerView.adapter = adapter
    }
}