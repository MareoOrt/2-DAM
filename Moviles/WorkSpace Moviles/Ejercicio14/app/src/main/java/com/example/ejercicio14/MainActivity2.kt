package com.example.ejercicio14

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ejercicio14.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        var contador = 0
        binding.btContar.setOnClickListener {
            contador ++
        }

        binding.btFinalizar.setOnClickListener {
            val c = contador.toString()

            intent.putExtra("contador", c)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}