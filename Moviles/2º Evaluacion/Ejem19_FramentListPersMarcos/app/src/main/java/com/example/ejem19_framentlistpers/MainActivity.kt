package com.example.ejem19_framentlistpers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ejem19_framentlistpers.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().add(R.id.fcv_usuarios,
            UsuarioFragment.newInstance(1)).commit()

    }
}