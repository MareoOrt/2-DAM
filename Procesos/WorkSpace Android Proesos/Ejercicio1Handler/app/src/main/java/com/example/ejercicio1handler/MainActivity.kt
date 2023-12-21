package com.example.ejercicio1handler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.ejercicio1handler.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.btParar.setOnClickListener{
//            var txt = binding.tvContador.text.toString()
//
//            binding.lvLista.adapter
//        }

        var flag = true

        var handler = Handler(Looper.getMainLooper())
        var contador = Thread {
            handler.post {
                Log.d("depurando", "Inicio del contador")
                while (flag) {
                    var txt = binding.tvContador.text.toString()
                    var time = txt.split(":")
                    Log.d("depurando", "Minutos: $txt")
                    for (i in 1..59) {
                        Thread.sleep(1000)
                        if (i in 1..9) {
                            txt = time[0] + ":0" + i
                        } else {
                            txt = time[0] + ":" + i
                        }
                        Log.d("depurando", "Segundos: $txt")
                        binding.tvContador.text = txt
                    }
                    txt = (time[0] + 1) + ":00"
                    binding.tvContador.text = txt
                }
            }
        }
        contador.start()
    }
}