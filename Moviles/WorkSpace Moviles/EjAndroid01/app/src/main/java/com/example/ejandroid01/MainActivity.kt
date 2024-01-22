package com.example.ejandroid01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.OnClickListener
import androidx.navigation.findNavController
import com.example.controlgastos.GastosProvider
import com.example.controlgastos.UsuariosProvider
import com.example.ejandroid01.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btLogin.setOnClickListener() {
            var msj = ""

            var name = binding.etName.text.toString()
            var password = binding.etPassword.text.toString()

            if (!name.isNullOrEmpty() && !password.isNullOrEmpty()) {
                if (!UsuariosProvider.usuarios.contains(UsuariosProvider.Users(name, password))) {
                    msj =
                        "Alguno de los datos que escribistes esta mal introducido.\nNo se encontro el usuario"
                } else {
                    var intent = Intent(this, MainActivityND::class.java)
                    var bundle = Bundle()
                    bundle.putString("user", name)

                    intent.putExtra("bundle", bundle)
                    startActivity(intent)
                }
            } else {
                msj = "Debes introducir los datos para poder inciar sesion"
            }

            if (!msj.isNullOrEmpty()) {
                Snackbar.make(binding.root, msj, Snackbar.LENGTH_LONG).show()
            }
        }
    }
}

