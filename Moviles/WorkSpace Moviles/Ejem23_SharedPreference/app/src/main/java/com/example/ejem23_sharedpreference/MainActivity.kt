package com.example.ejem23_sharedpreference

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ejem23_sharedpreference.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = getSharedPreferences("datos", MODE_PRIVATE)

        binding.btGuardar.setOnClickListener(){
            val dato = binding.tilTvDato.text.toString()
            val editor = pref.edit()
            editor.putString("dato", dato)
            editor.commit()
        }

        binding.btRecuperar.setOnClickListener() {
            val dato = pref.getString("dato","No hay datos que recoger")
            binding.tvDatoDevuelto.text = dato
        }
    }
}