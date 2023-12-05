package com.example.ejercicio15activitysministerioderobologa

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.ejercicio15activitysministerioderobologa.databinding.ActivityMainBinding
import java.util.Locale


class MainActivity : AppCompatActivity() {

    var binding = ActivityMainBinding.inflate(layoutInflater)
    var idioma = (Locale.getDefault().language.equals(Locale.ENGLISH.language))
    var votos = HashMap<String, Number>()
    var resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val dato = result.data
            val voto = dato?.extras?.getString("voto")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        rellenarVotos()
        botones()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }

    fun botones(){
        binding.btCambiarIdioma.setOnClickListener {
            val newConfig = Configuration()
            if(idioma) {
                newConfig.setLocale(Locale.ENGLISH)
                idioma = false
            }else {
                newConfig.setLocale(Locale.CANADA)
                idioma = false
            }
            onConfigurationChanged(newConfig)
        }

        binding.btVotar.setOnClickListener {
            intent = Intent(this, MainActivity2::class.java)
            resultLauncher.launch(intent)
            startActivity(intent)
        }
    }

    fun rellenarVotos(){
        votos.put("PSOE", 0)
        votos.put("Podemos", 0)
        votos.put("UI", 0)
        votos.put("PP", 0)

        binding.listaVotos.adapter(this, votos) as AdapterView
    }
}