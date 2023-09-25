package com.example.usuarios.data.model.login

import android.annotation.SuppressLint
import android.util.Log
import com.example.usuarios.data.api.RetrofitClient
import com.example.usuarios.domain.login.LoginListener
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginService {
    private val gson = Gson()
    @SuppressLint("NotConstructor")
    suspend fun LoginService(email:String,password:String, loginListener: LoginListener){
        withContext(Dispatchers.IO){
            var loginRequest = LoginRequest(email!!,password!!)
            RetrofitClient.getApiService().userLogin(loginRequest)
                .enqueue(object : Callback<ResponseBody> {
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    }
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>,
                    ) {
                        if (response.isSuccessful){
                            val loginResponse: LoginResponse =
                                gson.fromJson(response.body()?.string() , LoginResponse::class.java)
                            loginListener.getLoginSuccess(loginResponse)
                            Log.d("Success","Inicio Sesion")
                        }else{
                            loginListener.getLoginError("Login fallido")
                            Log.d("Error","No entro")
                        }
                    }
                }
            )

        }
    }
}