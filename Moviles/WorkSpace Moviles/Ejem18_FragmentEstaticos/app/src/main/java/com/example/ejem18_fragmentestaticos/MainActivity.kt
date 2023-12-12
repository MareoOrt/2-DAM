package com.example.ejem18_fragmentestaticos

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.result.contract.ActivityResultContracts
import com.example.ejem18_fragmentestaticos.databinding.ActivityMainBinding
import com.example.ejem18_fragmentestaticos.databinding.FragmentBlankBinding

class MainActivity : AppCompatActivity(), BlankFragment.EscuchadorFragmentEstatico {

    lateinit var binding: ActivityMainBinding
    var contador =0
    var resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {}
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btActivity2.setOnClickListener {
            var intent = Intent(this, MainActivity2::class.java)
            launchedFromPackage()
            startActivity(intent)
        }
    }

    override fun recibirPulsacion() {
        binding.tvContador.text = (contador+1).toString()
    }

    override fun recibirTexto(texto:String) {
        binding.tvContador.text = texto
    }
}