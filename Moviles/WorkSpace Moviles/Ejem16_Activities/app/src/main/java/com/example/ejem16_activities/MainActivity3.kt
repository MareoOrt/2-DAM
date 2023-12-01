package com.example.ejem16_activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ejem16_activities.databinding.ActivityMain3Binding
import com.example.ejem16_activities.databinding.ActivityMainBinding

class MainActivity3 : AppCompatActivity() {

    lateinit var binding: ActivityMain3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btDevolverDato.setOnClickListener {
            val datoDevuelto = binding.datoDevuelto.selectedItem.toString()
            if (datoDevuelto.equals("Seleccione un elemento ...")) {
                setResult(RESULT_CANCELED, intent)
            } else {
                intent.putExtra("datodecuelto", datoDevuelto)
                setResult(RESULT_OK, intent)
            }
            finish()
        }
    }
}