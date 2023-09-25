package com.example.usuarios.domain.login

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.example.usuarios.UsuariosActivity
import com.example.usuarios.data.model.login.LoginResponse
import com.example.usuarios.data.model.login.LoginService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel():ViewModel (){
    val loginService = LoginService()
    /*private val _email = MutableLiveData<String>()
    val email: LiveData<String>
        get() = _email
    private val _password = MutableLiveData<String>()
    val password: LiveData<String>
        get() = _password
    fun setEmail(email: String) {
        _email.value = email
    }
    fun setPassword(password: String) {
        _password.value = password
    }*/
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    fun login(email: String,password:String,context: Context?,activity: FragmentActivity?){
        coroutineScope.launch {
            loginService.LoginService(
                email,password,
                object : LoginListener{
                    override fun getLoginSuccess(loginResponse: LoginResponse) {
                        val sharedPreferences = context?.getSharedPreferences("usuarios",Context.MODE_PRIVATE)
                        val editor = sharedPreferences?.edit()
                        editor?.putString("email",email)
                        editor?.putString("password",password)
                        editor?.putString("token",loginResponse.token)
                        editor?.apply()
                        val intent = Intent(context, UsuariosActivity::class.java)
                        context?.startActivity(intent)
                        Toast.makeText(context, "Login exitoso", Toast.LENGTH_SHORT).show()
                        activity?.finish()
                    }
                    override fun getLoginError(error: String) {
                        Toast.makeText(context, "Login fallo", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }
    }
}