package com.example.belajarretrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.belajarretrofit.model.DetailMovieResponse
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

    val errorDetail: MutableLiveData<String> = MutableLiveData()
    val isLoadingDetail = MutableLiveData<Boolean>()
    private val _detailMovie: MutableLiveData<DetailMovieResponse> by lazy {
        MutableLiveData<DetailMovieResponse>().also {
            getAllMovies()
        }
    }
    val detailMovie: LiveData<DetailMovieResponse> = _detailMovie


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

    fun getDetailMovies(id: Int){
        isLoadingDetail.postValue(true)
        ApiClient.instance.getDetailMovie(id).enqueue(object : Callback<DetailMovieResponse> {
            override fun onResponse(call: Call<DetailMovieResponse>, response: Response<DetailMovieResponse>) {
                isLoading.postValue(false)
                if (response.code() == 200){
                    _detailMovie.postValue(response.body())
                }else{
                    errorDetail.postValue("Error")
                }
            }

            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                isLoadingDetail.postValue(false)
            }
        })
    }

}