package com.example.ejem16_activities

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.ejem16_activities.databinding.ActivityMain2Binding
import com.example.ejem16_activities.databinding.ActivityMainBinding

class MainActivity2 : AppCompatActivity() {
    lateinit var binding2: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding2 = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding2.root)
        var dato = intent.extras?.getSerializable("dato") as Dato

        binding2.tvDato.text = dato.dato

        binding2.bFinalizar.setOnClickListener {
            // Para que  vuelva al primer activity ponemos:
            finish()
        }
    }
}