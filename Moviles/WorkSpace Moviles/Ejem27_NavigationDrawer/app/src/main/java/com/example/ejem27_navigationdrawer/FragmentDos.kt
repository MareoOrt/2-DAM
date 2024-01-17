package com.example.ejem25_navigationcomponent

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentResultListener
import androidx.navigation.findNavController
import com.example.ejem27_navigationdrawer.databinding.FragmentDosBinding


class FragmentDos : Fragment() {

    lateinit var binding: FragmentDosBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentDosBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* Primera forma de recibir parámetros con Navigation Component */
        arguments?.let {
            binding.tvDatoRecibido.text=it.getString("dato")
        }

        /* Segunda forma de recibir parámetros con Fragment Result Listender */
        parentFragmentManager.setFragmentResultListener("EnviarDato", this) {
                key, bundle ->
            binding.tvDatoRecibido2.text = bundle.getString("dato")
            Log.d("depurando", "dato: ${bundle.getString("dato")}")
        }



      /*  view.findViewById<Button>(R.id.button2b).setOnClickListener {
            it.findNavController().navigate(R.id.action_fragmentDos_to_fragmentTres)
        }

        view.findViewById<Button>(R.id.button2).setOnClickListener {
            it.findNavController().navigate(R.id.action_fragmentDos_to_fragmentUno)
        }*/
    }
}