package com.example.usuarios.ui.usuario

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.usuarios.CallbackFragment
import com.example.usuarios.UsuariosActivity

class EditarFragment : Fragment() {
    private var sharedPreferences: SharedPreferences? = null
    private var callbackFragment: CallbackFragment? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        sharedPreferences = context.getSharedPreferences("userFile", Context.MODE_PRIVATE)
        Toast.makeText(requireContext(), "Edtitar frgamento", Toast.LENGTH_SHORT).show()
    }

    fun setCallbackFragment(callbackFragment: UsuariosActivity) {
        this.callbackFragment = callbackFragment
    }
}