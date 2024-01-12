package com.example.ejem25_navigationcomponent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController

class BlankFragment2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.bt_f2_to_f1).setOnClickListener(){
            it.findNavController().navigate(R.id.action_blankFragment2_to_blankFragment)
        }

        view.findViewById<Button>(R.id.bt_f2_to_f3).setOnClickListener(){
            it.findNavController().navigate(R.id.action_blankFragment2_to_blankFragment3)
        }
    }
}