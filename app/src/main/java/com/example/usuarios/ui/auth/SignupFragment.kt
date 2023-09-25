package com.example.usuarios.ui.auth

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.usuarios.databinding.FragmentSignupBinding
import com.example.usuarios.domain.signup.SignupViewModel

class SignupFragment : Fragment() {
    private var sharedPreferences: SharedPreferences? = null
    private var uEmail: String? = null
    private var uPass: String? = null
    private lateinit var signupViewModel : SignupViewModel
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!
    override fun onAttach(context: Context) {
        sharedPreferences = context.getSharedPreferences("userFile", Context.MODE_PRIVATE)
        super.onAttach(context)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        signupViewModel = ViewModelProvider(this).get(SignupViewModel::class.java)
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.btnRegistro.setOnClickListener {
            uEmail = binding.userEmail.text.toString()
            uPass = binding.userPassword.text.toString()
            val editor = sharedPreferences?.edit()
            if (uEmail.isNullOrEmpty()) {
                Toast.makeText(context, "Email requerido", Toast.LENGTH_SHORT).show()
                binding.userEmail.requestFocus()
                return@setOnClickListener
            }
            if (uPass.isNullOrEmpty()) {
                Toast.makeText(context, "Password requerido", Toast.LENGTH_SHORT).show()
                binding.userPassword.requestFocus()
                return@setOnClickListener
            }
            signupViewModel.signup(uEmail!!, uPass!!,context,activity)
        }
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

