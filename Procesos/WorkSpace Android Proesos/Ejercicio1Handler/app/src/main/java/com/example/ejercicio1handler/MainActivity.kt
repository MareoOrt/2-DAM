package com.example.ejercicio1handler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import com.example.ejercicio1handler.databinding.ActivityMainBinding
import kotlin.math.min

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    var list = ArrayList<String>()
    var adapter = ArrayAdapter(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, list)

    var flag = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lvLista.adapter = adapter

        binding.btParar.setOnClickListener {
            if(binding.lvLista.count <= 0) binding.btLimpiar.isEnabled = true

            var txt = binding.tvContador.text.toString()
            list.add(txt)
        }

        var handler = Handler()
        var contador = Thread(Runnable {
            Log.d("depurando", "Inicio del contador")
            while (true) {
                var txt = binding.tvContador.text.toString()
                Log.d("depurando", "Minutos: $txt")

                var time = txt.split(":")
                var minutes = (Integer.parseInt(time[0]))
                var seconds = (Integer.parseInt(time[1]))

                for (i in seconds..59) {
                    if (i in 1..9) {
                        txt = minutes.toString() + ":0" + i
                    } else {
                        txt = minutes.toString() + ":" + i
                    }
                    Log.d("depurando", "Segundos: $txt")
                    Thread.sleep(1000)
                    handler.post {
                        binding.tvContador.text = txt
                    }
                    txt = (minutes.plus(1)).toString() + ":00"
                    handler.post {
                        binding.tvContador.text = txt
                    }
                }
            }
        })

        binding.btParar.setOnClickListener(View.OnClickListener {
            if(flag){
                flag = false
                contador.start()
                binding.btParar.text = "Parar"
            }else{
                flag = true
                contador.resume()
                binding.btParar.text = "Iniciar"
            }
        })


    }
}