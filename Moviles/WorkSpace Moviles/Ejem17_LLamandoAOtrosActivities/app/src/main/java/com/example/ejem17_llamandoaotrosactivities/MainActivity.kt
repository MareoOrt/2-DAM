package com.example.ejem17_llamandoaotrosactivities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ejem17_llamandoaotrosactivities.databinding.ActivityMainBinding
import java.lang.reflect.Type

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btEnviarTexto.setOnClickListener {
            intent = Intent(Intent.ACTION_SEND)
            intent.setType("text/plain")
            intent.putExtra(Intent.EXTRA_TEXT, "Hola mundo comparativo")
            startActivity(intent)
        }

        binding.btEnviarTexto2.setOnClickListener {
            intent = Intent().apply {
                action = Intent.ACTION_SEND
                setType("text/plain")
                putExtra(Intent.EXTRA_TEXT, "Hola mundo comparativo")
                startActivity(this)
            }
        }

        binding.btEnviarEmail.setOnClickListener {
            intent = Intent().apply {
                action = Intent.ACTION_SEND
                setType("text/plain")
                putExtra(Intent.EXTRA_EMAIL, "mario.ortunez.sanz@gmail.com")
                putExtra(Intent.EXTRA_CC,  "mario.ortunez.sanz@gmail.com")
                putExtra(Intent.EXTRA_SUBJECT, "Asunto del gmail")
                putExtra(Intent.EXTRA_TEXT, "Hola mundo")
                startActivity(Intent.createChooser(this, "Enviar email"))
            }
        }

        binding.btLlamadaTelf.setOnClickListener {
            intent = Intent().apply {
                action = Intent.ACTION_DIAL
                setData(Uri.parse("tel:334455"))
                startActivity(this)
            }
        }

        binding.btLanzarWeb.setOnClickListener {
            intent = Intent().apply {
                action = Intent.ACTION_VIEW
                setData(Uri.parse("https://www.gregoriofer.es"))
                startActivity(this)
            }
        }

        binding.btGoogleMaps.setOnClickListener {
            intent = Intent().apply {
                action = Intent.ACTION_VIEW
                setData(Uri.parse(("geo:"+41.39+","+4.44+"?q=Valladolid" )))
                startActivity(this)
            }
        }



    }
}