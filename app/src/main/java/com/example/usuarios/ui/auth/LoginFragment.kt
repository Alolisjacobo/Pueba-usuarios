package com.example.usuarios.ui.auth

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.usuarios.CallbackFragment
import com.example.usuarios.R
import com.example.usuarios.databinding.FragmentLoginBinding
import com.example.usuarios.domain.login.LoginViewModel

class LoginFragment : Fragment() {

    private var sharedPreferences: SharedPreferences? = null
    private var callbackFragment: CallbackFragment? = null
    private var userEmail: String? = null
    private var userPassword: String? = null
    private lateinit var loginViewModel: LoginViewModel
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        sharedPreferences = context.getSharedPreferences("userFile", Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        /*val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )*/
        val view = binding.root
        /*binding.loginVM = loginViewModel
        loginViewModel.email.observe(viewLifecycleOwner, Observer { email ->
            binding.userEmail.setText(email)
        })
        loginViewModel.password.observe(viewLifecycleOwner, Observer { password ->
            binding.userPassword.setText(password)
        })*/

        binding.btnLogin.setOnClickListener {
            userEmail = binding.userEmail.text.toString()
            userPassword = binding.userPassword.text.toString()
            val editor = sharedPreferences?.edit()

            if (userEmail.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "Email requerido", Toast.LENGTH_SHORT).show()
                binding.userEmail.requestFocus()
                return@setOnClickListener
            }

            if (userPassword.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "Password requerido", Toast.LENGTH_SHORT).show()
                binding.userPassword.requestFocus()
                return@setOnClickListener
            }
            loginViewModel.login(userEmail!!, userPassword!!, context, activity)
        }
        binding.btnRegistro.setOnClickListener {
            callbackFragment?.changeFragment()
        }
        return view

    }

    fun setCallbackFragment(callbackFragment: CallbackFragment?) {
        this.callbackFragment = callbackFragment
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

