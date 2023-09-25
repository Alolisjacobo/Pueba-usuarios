package com.example.usuarios

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.usuarios.ui.auth.LoginFragment
import com.example.usuarios.ui.auth.SignupFragment
import com.example.usuarios.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), CallbackFragment {

    private var fragmentManager: FragmentManager? = null
    private lateinit var fragmentTransaction: FragmentTransaction
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPreferences = getSharedPreferences("usuarios", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token", null)
        if(token!=null){
            val intent = Intent(this, UsuariosActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            addFragment()
        }
    }

    private fun addFragment() {
        val fragment = LoginFragment()
        fragment.setCallbackFragment(this)
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.add(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }

    private fun replaceFragment() {
        val fragment = SignupFragment()
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }

    override fun changeFragment() {
        replaceFragment()
    }
}