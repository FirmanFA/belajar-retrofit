package com.example.belajarretrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.belajarretrofit.model.MovieResponse
import com.example.belajarretrofit.service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    val error: MutableLiveData<String> = MutableLiveData()
    val isLoading = MutableLiveData<Boolean>()
    private val _dataMovie: MutableLiveData<MovieResponse> by lazy {
        MutableLiveData<MovieResponse>().also {
            getAllMovies()
        }
    }
    val dataMovie: LiveData<MovieResponse> = _dataMovie


    private fun getAllMovies(){
        isLoading.postValue(true)
        ApiClient.instance.getAllMovies().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                isLoading.postValue(false)
                if (response.code() == 200){
                    _dataMovie.postValue(response.body())
                }else{
                    error.postValue("Error")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                isLoading.postValue(false)
            }
        })
    }

}