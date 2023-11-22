package com.example.ejercicio10mensdeaplicacinycontextualesdilogoconfirmacin

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.ejercicio10mensdeaplicacinycontextualesdilogoconfirmacin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var rojo : MenuItem = findViewById(R.id.mn_rojo)
        var amarillo : MenuItem = findViewById(R.id.mn_amarillo)
        var verde : MenuItem = findViewById(R.id.mn_verde)

        rojo.setOnMenuItemClickListener {
            binding.layoutPadre.background= Color.RED
        }
        amarillo.setOnMenuItemClickListener {
            binding.layoutPadre.background= Color.YELLOW
        }
        verde.setOnMenuItemClickListener {
            binding.layoutPadre.background= Color.GREEN
        }

        binding.layoutPadre.setOnTouchListener()

    }
}