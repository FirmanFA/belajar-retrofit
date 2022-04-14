package com.example.belajarretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.belajarretrofit.databinding.ActivityRegisterBinding
import com.example.belajarretrofit.model.RegisterRequest
import com.example.belajarretrofit.model.RegisterResponse
import com.example.belajarretrofit.service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val passwrd = binding.etPassword.text.toString()
            val role = "admin"
            val user = RegisterRequest(email,passwrd,role)
            registerUser(user)
        }

    }

    private fun registerUser(registerRequest: RegisterRequest) {
        binding.progressBar2.visibility = View.VISIBLE
        binding.btnRegister.visibility = View.GONE
        ApiClient.instance.postRegister(registerRequest).enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                val code = response.code()
                if (code==200){
                    binding.progressBar2.visibility = View.GONE
                    binding.btnRegister.visibility = View.VISIBLE
                    Toast.makeText(this@RegisterActivity, "Register Berhasil", Toast.LENGTH_SHORT)
                        .show()
                    finish()
                }else{
                    Toast.makeText(this@RegisterActivity, "email sudah ada", Toast.LENGTH_SHORT).show()
                    binding.progressBar2.visibility = View.GONE
                    binding.btnRegister.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                binding.progressBar2.visibility = View.GONE
                binding.btnRegister.visibility = View.VISIBLE
            }

        })
    }
}