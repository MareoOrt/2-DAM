package com.example.ejem21_toolbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.ejem21_toolbar.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.myToolBar?.miToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false) // show the back button
        supportActionBar?.title = "App bar"
        supportActionBar?.subtitle = "Ejemplo de App bar"

        binding.btPass.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }
    }
}
