package com.example.ejemlistas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ejemlistas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Insertar fragement
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainerView, Persona.newInstance(1))
            .commit()

        // Cambiar columnas del fragement
        binding.btGuardar.setOnClickListener() {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragmentContainerView,
                    Persona.newInstance(binding.etColumnas.text.toString().toInt())
                )
                .commit()
        }
    }
}