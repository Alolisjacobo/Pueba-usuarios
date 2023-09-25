package com.example.usuarios.domain.signup

import com.example.usuarios.data.model.SignupResponse

interface SignupListener {
    fun getCreateUserSuccess(signupResponse: SignupResponse)
    fun getCreateUserError(error : String)
}