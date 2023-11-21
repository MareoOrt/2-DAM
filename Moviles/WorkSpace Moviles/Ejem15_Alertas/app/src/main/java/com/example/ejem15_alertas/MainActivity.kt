package com.example.ejem15_alertas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.example.ejem15_alertas.databinding.ActivityMainBinding
import com.example.ejem15_alertas.databinding.CustomDialogBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btAlerta.setOnClickListener {
            val alertBuilder = AlertDialog.Builder(this)
            alertBuilder.setTitle("Venatana Alerta")
            alertBuilder.setMessage("Ejemplo de alerta")
            alertBuilder.setPositiveButton("OK"){ dialog, which ->
                Snackbar.make(binding.root, "Has pulsado en OK", Snackbar.LENGTH_LONG).show()
            }
            alertBuilder.setNegativeButton("Cancelar"){ dialog, which ->
                Snackbar.make(binding.root, "Has pulsado en Cancelar", Snackbar.LENGTH_LONG).show()
            }
            alertBuilder.show()
        }
        binding.btAlertBasic.setOnClickListener {
            val alertBuilder = AlertDialog.Builder(this)
            alertBuilder.setTitle("Venatana Alerta")
            alertBuilder.setMessage("Ejemplo de alerta basica")
            alertBuilder.setPositiveButton("OK", null)
            alertBuilder.show()
        }

        binding.btSelMult.setOnClickListener {
            var listaopciones = arrayOf("Opcion 1", "OPcion 2", "Opción 3", "OPcion 4")
            val alertBuilder = AlertDialog.Builder(this)
            alertBuilder.setTitle("Venatana Alerta")
            alertBuilder.setItems(listaopciones){ dialog, which ->
                Snackbar.make(
                    binding.root,
                    "Has pulsado la " + listaopciones[which],
                    Snackbar.LENGTH_LONG)
                    .show()
                dialog.cancel()
            }
            alertBuilder.show()
        }

        binding.btDialogPersonal.setOnClickListener {
            var bCustomDialog = CustomDialogBinding.inflate(layoutInflater)

            var listaopciones = arrayOf("Opcion 1", "OPcion 2", "OPcion 3", "OPcion 4")
            val alertBuilder = AlertDialog.Builder(this)
            alertBuilder.setTitle("Venatana Alerta Personalizada")
            alertBuilder.setView(bCustomDialog.root)
            Glide.with(this).load("https://picsum.photos/200/300").into(bCustomDialog.ivImagen)
            alertBuilder.setPositiveButton("OK"){ dialog, which ->
                var nombre = bCustomDialog.tilEtNombre.text.toString()
                var soltero = bCustomDialog.cbEstado.isChecked
                var ciudad = bCustomDialog.spCiudad.selectedItem.toString()

                Snackbar.make(binding.root, "Te registrastes " + nombre, Snackbar.LENGTH_LONG).show()
            }
            alertBuilder.show()
        }

        Glide.with(this).load("https://picsum.photos/200/300").into(binding.ivImagen)

    }

}