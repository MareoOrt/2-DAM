package com.example.ejemrecycledview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ejemrecycledview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvList.layoutManager =

        binding.rvList.adapter = PersonaAdapter(ArrayList<Persona>())


    }
}