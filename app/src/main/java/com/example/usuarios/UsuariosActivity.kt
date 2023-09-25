package com.example.usuarios
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.usuarios.databinding.ActivityUsuariosBinding
import com.example.usuarios.ui.usuario.EditarFragment
import com.example.usuarios.ui.usuario.ListaFragment

class UsuariosActivity : AppCompatActivity(), CallbackFragment {
    private lateinit var fragmentTransaction: FragmentTransaction
    private lateinit var binding: ActivityUsuariosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsuariosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLista.setOnClickListener {
            listaFragment()
        }
        binding.btnCrear.setOnClickListener {
            editarFragment()
        }
    }

    private fun listaFragment() {
        val fragment = ListaFragment()
        fragment.setCallbackFragment(this)
        val fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragmentContainerUsuarios, fragment)
        fragmentTransaction.commit()
    }

    private fun editarFragment() {
        val fragment = EditarFragment()
        val fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.replace(R.id.fragmentContainerUsuarios, fragment)
        fragmentTransaction.commit()
    }

    override fun changeFragment() {
        editarFragment()
    }
}

