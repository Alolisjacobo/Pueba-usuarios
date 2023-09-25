package com.example.usuarios.domain.signup

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.example.usuarios.UsuariosActivity
import com.example.usuarios.data.model.SignupResponse
import com.example.usuarios.data.model.SignupService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignupViewModel (): ViewModel(){
    val signupService = SignupService()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    fun signup(email: String, password: String, context: Context?, activity: FragmentActivity?){
        coroutineScope.launch {
            signupService.SignupService(
                email,
                password,
                object : SignupListener {
                    override fun getCreateUserSuccess(signupResponse: SignupResponse) {
                        val sharedPreferences = context?.getSharedPreferences("usuarios", Context.MODE_PRIVATE)
                        val editor = sharedPreferences?.edit()
                        editor?.putString("email", email)
                        editor?.putString("token", signupResponse.token)
                        editor?.apply()
                        val intent = Intent(context, UsuariosActivity::class.java)
                        context?.startActivity(intent)
                        Toast.makeText(context, "Registro exitos", Toast.LENGTH_SHORT).show()
                        activity?.finish()
                    }
                    override fun getCreateUserError(error: String) {
                        Toast.makeText(context, "Registro fallo", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }
    }
}