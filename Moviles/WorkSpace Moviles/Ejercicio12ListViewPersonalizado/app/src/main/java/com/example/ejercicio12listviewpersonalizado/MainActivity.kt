package com.example.ejercicio12listviewpersonalizado

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ejercicio12listviewpersonalizado.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    fun llenarList(){



        binding.lvLista
    }
}