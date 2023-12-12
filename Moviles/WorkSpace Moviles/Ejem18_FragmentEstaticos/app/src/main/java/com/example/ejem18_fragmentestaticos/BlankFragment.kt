package com.example.ejem18_fragmentestaticos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.ejem18_fragmentestaticos.databinding.FragmentBlankBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BlankFragment : Fragment() {

    lateinit var binding: FragmentBlankBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBlankBinding.inflate(inflater, container, false)
        binding.btFragment.setOnClickListener {
            binding.tvFragment.text = "Hola desde el fragment"
            (activity as EscuchadorFragmentEstatico).recibirPulsacion()
        }
        binding.etTexto.setOnFocusChangeListener {view, b ->
            var texto = binding.etTexto.text.toString()
            (activity as EscuchadorFragmentEstatico).recibirTexto(texto)
        }
        return binding.root
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    // Esta vista es el componente raiz
    // Aqui se hace la misma funcion que arriba
    /*
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.bt_fragment).setOnClickListener {
            view.findViewById<TextView>(R.id.tv_fragment).text = "Hola desde el fragment"
        }
    }
    */

    interface EscuchadorFragmentEstatico {
        fun recibirPulsacion()

        fun recibirTexto(texto:String)
    }
}