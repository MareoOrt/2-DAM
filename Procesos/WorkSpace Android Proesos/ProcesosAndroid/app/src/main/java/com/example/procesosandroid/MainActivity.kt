package com.example.procesosandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.procesosandroid.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pbHorizontal.max = 100
        binding.pbHorizontal.setProgress(0, true)
        //var gestorInterfaz: Handler = Handler()
        var gestorInterfaz: Handler = Handler(Looper.getMainLooper())

        binding.bIniciarProgreso.setOnClickListener {
            Snackbar.make(it, "Iniciando Progreso", Snackbar.LENGTH_SHORT).show()
            binding.pbCircular.visibility = android.view.View.VISIBLE
            binding.pbHorizontal.incrementProgressBy(10)


            var proceso: Thread = Thread(Runnable {
                for (i in 1..10) {
                    gestorInterfaz.post(Runnable {
                        binding.pbHorizontal.incrementProgressBy(10)
                        binding.tvPorcentaje.text = binding.pbHorizontal.progress.toString() + " %"
                    })
                    Thread.sleep(1000)
                }
            })
            proceso.start()
        }

    }
}
