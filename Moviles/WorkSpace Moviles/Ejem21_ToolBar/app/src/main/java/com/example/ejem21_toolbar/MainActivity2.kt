package com.example.ejem21_toolbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ejem21_toolbar.databinding.ActivityMain2Binding

class MainActivity2 : ActivityConMenus() {

    lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.myToolBar?.miToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // show the back button
        supportActionBar?.title = "App bar Activity2"
        supportActionBar?.subtitle = "Ejemplo de App bar"

    }


}