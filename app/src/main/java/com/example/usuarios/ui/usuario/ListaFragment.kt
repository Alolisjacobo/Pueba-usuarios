package com.example.usuarios.ui.usuario

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.usuarios.CallbackFragment
import com.example.usuarios.R
import com.example.usuarios.databinding.FragmentListaBinding

class ListaFragment : Fragment() {

    private var sharedPreferences: SharedPreferences? = null
    private var callbackFragment: CallbackFragment? = null
    private var _binding: FragmentListaBinding? = null
    private val binding get() = _binding!!

    var avatares = arrayOf<String>()
    var emails = arrayOf("correo1@example.com", "correo2@example.com", "correo3@example.com")
    var fNames = arrayOf("Nombre1", "Nombre2", "Nombre3")
    var lNames = arrayOf("Apellido1", "Apellido2", "Apellido3")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        sharedPreferences = context.getSharedPreferences("userFile", Context.MODE_PRIVATE)

        Toast.makeText(requireContext(), "lista fragmento", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListaBinding.inflate(inflater, container, false)
        val recycler = binding.usersLista
        recycler.layoutManager = LinearLayoutManager(requireActivity())
        recycler.adapter = AdaptadorPersona()
        val view = binding.root
        return view
    }

    private inner class AdaptadorPersona :
        RecyclerView.Adapter<AdaptadorPersona.AdaptadorPersonaHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorPersonaHolder {
            return AdaptadorPersonaHolder(layoutInflater.inflate(R.layout.layout_tarjeta,
                parent,
                false))
        }

        override fun onBindViewHolder(holder: AdaptadorPersonaHolder, position: Int) {
            holder.imprimir(position)
        }

        override fun getItemCount(): Int {
            return fNames.size
        }

        inner class AdaptadorPersonaHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {
            var iv1: ImageView
            var tv1: TextView
            var tv2: TextView
            var tv3: TextView

            init {
                iv1 = itemView.findViewById(R.id.imageView)
                tv1 = itemView.findViewById(R.id.userNombre)
                tv2 = itemView.findViewById(R.id.userCorreo)
                tv3 = itemView.findViewById(R.id.userTrabajo)
            }

            fun imprimir(position: Int) {
                iv1.setImageResource(R.drawable.usuario_img)
                tv1.text = fNames[position]
                tv2.text = emails[position]
                tv3.text = lNames[position]
            }
        }
    }

    fun setCallbackFragment(callbackFragment: CallbackFragment) {
        this.callbackFragment = callbackFragment
    }
}


