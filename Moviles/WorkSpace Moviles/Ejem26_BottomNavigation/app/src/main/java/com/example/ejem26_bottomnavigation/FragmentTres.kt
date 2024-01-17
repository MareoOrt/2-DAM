package com.example.ejem25_navigationcomponent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.ejem26_bottomnavigation.databinding.FragmentTresBinding


class FragmentTres : Fragment() {

    lateinit var binding: FragmentTresBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTresBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

/*        binding.button3.setOnClickListener {
            it.findNavController().navigate(R.id.action_fragmentTres_to_fragmentDos)
        }*/
    }
}