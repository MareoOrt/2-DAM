package com.example.ejercicio_peliculas

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ejercicio_peliculas.databinding.FragmentInsertarBinding
import com.google.android.material.snackbar.Snackbar

class InsertarFragment : Fragment() {
    lateinit var binding: FragmentInsertarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInsertarBinding.inflate(layoutInflater)

        binding.btCrear.setOnClickListener{
            var titulo = binding.fragEtNombre.text.toString()
            var desc = binding.fragEtDescripcion.text.toString()
            var fecha = binding.editTextNumber.text.toString()
            if(comprobarFecha(fecha)){
                Movies.datos.add(Movie(titulo, desc, fecha.toInt()))
            }else{
                Snackbar.make(
                    binding.root,
                    "Revise que los datos fueron bien introducidos",
                    Snackbar.LENGTH_LONG).show()
            }
        }
        return binding.root
    }
    fun comprobarFecha (fecha:String): Boolean{
        return try {
            var f = fecha.toInt()
            if (f > 2023 || f < 1950) {
                throw IllegalArgumentException("Fecha no es un año asignable")
            }
            true
        } catch (e: NumberFormatException) {
            Log.d("Error", e.message.toString())
            false
        } catch (e: IllegalArgumentException) {
            Log.d("Error", e.message.toString())
            false
        }
    }

    companion object {
        fun newInstance() = InsertarFragment()
    }
}