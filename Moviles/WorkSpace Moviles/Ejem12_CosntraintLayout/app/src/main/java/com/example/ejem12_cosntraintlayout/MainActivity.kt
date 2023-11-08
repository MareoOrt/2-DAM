package com.example.ejem12_cosntraintlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ejem12_cosntraintlayout.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    // Ya no hablamos de izquierda y derecha sino de inicio y fin (start - end)
    // ChainStyle son para ajustar horizontalmente

    // Lateinit nos deja inicializarlo depsues en kotlin
    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(getLayoutInflater())
        setContentView(binding.root)

        binding.btGrabar.setOnClickListener {
            val nombre = binding.tilElNombre.text.toString()
            val usuario = Usuario(nombre)
            Snackbar.make(
                binding.clPrincipal,
                "usuario creado: ${usuario.nombre}",
                Snackbar.LENGTH_LONG
            ).show()
        }
        var pedro:Usuario = Usuario("Pedro")

        binding.nombre = pedro
    }
}
class Usuario{
    var nombre:String = ""

    constructor(nombre: String) {
        this.nombre = nombre
    }

}