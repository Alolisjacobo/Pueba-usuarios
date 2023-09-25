package com.example.usuarios.data.api

import com.example.usuarios.data.Paths
import com.example.usuarios.data.model.login.LoginRequest
import com.example.usuarios.data.model.SignupRequest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {

    @POST("/api/register")
    fun createUser(
        @Body signupRequest: SignupRequest
    ): Call<ResponseBody>

    @POST("/api/login")
    fun userLogin(
        @Body loginRequest: LoginRequest
    ):Call<ResponseBody>

    @GET(Paths.LIST_USERS_PATH)
    fun listUsers(
        @Path("page") page:Int?
    ):Call<ResponseBody>

}