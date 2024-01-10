package com.example.fastejlistfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fastejlistfragment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainerView, UsuariosFragment.newInstance(1))
            .commit()


    }
}