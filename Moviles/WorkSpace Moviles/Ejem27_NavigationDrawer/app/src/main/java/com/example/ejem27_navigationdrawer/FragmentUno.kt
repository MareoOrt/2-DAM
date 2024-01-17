package com.example.ejem25_navigationcomponent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.ejem27_navigationdrawer.databinding.FragmentUnoBinding


class FragmentUno : Fragment() {

    lateinit var binding: FragmentUnoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentUnoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.button.setOnClickListener(View.OnClickListener {

            /* Envío de datos con FragmentResult */
            val dato = binding.etDatoAEnviar2.text.toString()
            parentFragmentManager.setFragmentResult("EnviarDato", Bundle().apply {
                putString("dato", dato)
            })

            /* Forma de enviar datos con el Navigation Component*/
         /*   it.findNavController().navigate(R.id.action_fragmentUno_to_fragmentDos,
                Bundle().apply {
                    putString("dato", binding.etDatoAEnviar.text.toString())
                })*/
        })






    }
}