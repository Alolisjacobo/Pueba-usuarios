package com.example.usuarios.domain.login

import com.example.usuarios.data.model.login.LoginResponse

interface LoginListener {
    fun getLoginSuccess(loginResponse: LoginResponse)
    fun getLoginError(error : String)
}