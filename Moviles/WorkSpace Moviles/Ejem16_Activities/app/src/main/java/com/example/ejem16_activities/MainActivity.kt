package com.example.ejem16_activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.example.ejem16_activities.databinding.ActivityMain2Binding
import com.example.ejem16_activities.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    // Donde llega la aplicacion se inicia
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        Log.d("Depurando", "LLegamos al onCreate")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

            var resultLauncher = registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    // There are no request codes
                    val dato = result.data
                    val datoDevuelto = dato?.extras?.getString("datodecuelto")
                    Snackbar.make(
                        binding.root,
                        "Elegiste ${datoDevuelto.toString()}",
                        Snackbar.LENGTH_LONG
                    ).show()

                    // Saltara esta opcion si nosotros lo ponemos o si se da para atras en la aplicacion
                } else if (result.resultCode == Activity.RESULT_CANCELED) {
                // There are no request codes
                val dato = result.data
                Snackbar.make(
                    binding.root,
                    "No se ha seleccionado ningun elemento",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }

        /* -- Versión antigua -- */
        binding.bIniciaActivity2.setOnClickListener {

            // Pasar cualquier dato, por ejemplo String
            // intent.extras?.putString("dato", binding.tilEtDato.text.toString())
            // Dato serializable para pasar uhna cadena de char a cadena de bytes

            val intent = Intent(this, MainActivity2::class.java)
            var bundle: Bundle = Bundle()
            // bundle.putSerializable("dato", Dato(binding.tilEtDato.text.toString()))
            bundle.putParcelable("dato", Dato(binding.tilEtDato.text.toString()))

            intent.putExtra("dato", bundle)
            Log.d("Depurando", "Iniciamos setOnClickListener")

        }

        /* -- Versión nueva -- */
        binding.bIniciarForresault.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            resultLauncher.launch(intent)
            startActivity(intent)
        }
    }

    // Donde llega la aplicacion cuando se deja la minimizada y se vuelve a la aplicacion
    override fun onStart() {
        super.onStart()
        Log.d("Depurando", "LLegamos al onStart")
    }

    // Donde llega la aplicacion cuando se deja la minimizada y se vuelve a la aplicacion
    override fun onResume() {
        super.onResume()
        Log.d("Depurando", "LLegamos al onResume")
    }
}