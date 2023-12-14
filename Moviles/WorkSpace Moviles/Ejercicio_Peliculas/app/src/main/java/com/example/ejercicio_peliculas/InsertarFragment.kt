package com.example.ejercicio_peliculas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ejercicio_peliculas.databinding.FragmentInsertarBinding

class InsertarFragment : Fragment() {
    lateinit var binding: FragmentInsertarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btCrear.setOnClickListener{
            var titulo = binding.fragEtNombre.text.toString()
            var desc = binding.fragEtDescripcion.text.toString()
            var fecha = binding.editTextNumber.text.toString()
            Movies.datos.add(Movie(titulo, desc, fecha.toInt()))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInsertarBinding.inflate(layoutInflater)
        return binding.root
    }

    companion object {
        fun newInstance() = InsertarFragment()
    }
}