package com.example.ejercicio_peliculas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ejercicio_peliculas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvPeliculas.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        binding.rvPeliculas.adapter = MovieAdapter(Movies.datos)

        binding.btInsertar.setOnClickListener {
            var fragment = InsertarFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fcv_nueva_peli, fragment)
                .commit()
        }


    }
}