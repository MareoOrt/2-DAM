package com.example.ejem19_datosfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import androidx.core.view.children
import androidx.core.view.iterator
import androidx.fragment.app.Fragment
import com.example.ejem19_datosfragment.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btEnviar.setOnClickListener {
            var dato = binding.etDato.text.toString()
            Snackbar.make(binding.root, dato, Snackbar.LENGTH_LONG).show()

            // val bundle = Bundle().putString("param1", dato)

            val fragment: Fragment

            when (binding.rgEleccion.checkedRadioButtonId){
                R.id.rb_Fragment1 -> fragment = BlankFragment.newInstance(dato)
                R.id.rb_Fragment2 -> fragment = BlankFragment2.newInstance(dato)
            }

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fcv_Fragment, fragment())
                .commit()
        }
    }
}