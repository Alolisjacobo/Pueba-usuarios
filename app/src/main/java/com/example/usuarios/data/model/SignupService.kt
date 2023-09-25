package com.example.usuarios.data.model

import android.annotation.SuppressLint
import android.util.Log
import com.example.usuarios.data.api.RetrofitClient
import com.example.usuarios.domain.signup.SignupListener
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignupService() {
    private val gson = Gson()
    @SuppressLint("NotConstructor")
    suspend fun SignupService(email: String, password:String, signupListener: SignupListener){
        withContext(Dispatchers.IO){
            var signupRequest = SignupRequest(email!!, password!!)
            RetrofitClient.getApiService().createUser(signupRequest)
                .enqueue(object : Callback<ResponseBody> {
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    }
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>,
                    ) {
                        if (response.isSuccessful) {
                            val signupResponse: SignupResponse =
                                gson.fromJson(response.body()!!.string(), SignupResponse::class.java)
                            signupListener.getCreateUserSuccess(signupResponse)
                            Log.d("Success","Si paso")
                        } else {
                            signupListener.getCreateUserError("Registro fallido")
                            Log.d("Error","No paso")
                        }
                    }
                })

        }
    }
}

