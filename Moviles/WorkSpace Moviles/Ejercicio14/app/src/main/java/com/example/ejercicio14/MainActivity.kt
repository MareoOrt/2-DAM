package com.example.ejercicio14

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.Nullable
import androidx.annotation.RequiresApi
import com.example.ejercicio14.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvContador.text = "0"



        var resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val dato = result.data
                val contador = dato?.extras?.getString("contador")

                binding.tvContador.text = contador
            }
        }

        binding.btLlamada.setOnClickListener {
            var intent = Intent(this, MainActivity2::class.java)
            resultLauncher.launch(intent)
            startActivity(intent)
        }
    }
}