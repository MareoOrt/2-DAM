package com.example.ejem16_activities

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.ejem16_activities.databinding.ActivityMain2Binding
import com.example.ejem16_activities.databinding.ActivityMainBinding

class MainActivity2 : AppCompatActivity() {
    lateinit var binding2: ActivityMain2Binding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding2 = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding2.root)
        Log.d("Depurando", "Entramos en el MainActivity2")
        // var dato = intent.extras?.getSerializable("dato") as Dato
        var bundle = intent.getBundleExtra("dato")
        var dato = bundle?.getParcelable("dato", Dato::class.java)

        binding2.tvDato.text = dato.toString()

        binding2.bFinalizar.setOnClickListener {
            // Para que  vuelva al primer activity ponemos:
            finish()
        }
    }
}