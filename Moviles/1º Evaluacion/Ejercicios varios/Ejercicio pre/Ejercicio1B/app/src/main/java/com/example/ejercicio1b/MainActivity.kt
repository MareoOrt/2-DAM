package com.example.ejercicio1b

import android.R
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val boton = findViewById<Button>(R.id.boton)

        boton.setOnClickListener {
            val nombre = findViewById<EditText>(R.id.nombre)
            val nombreTxt = nombre.text.toString()
            val texto = findViewById<TextView>(R.id.texto)
            val textoTxt = texto.text.toString()
            val txt = "$textoTxt\nBuenos días $nombreTxt"
            texto.text = txt
        }
    }
}