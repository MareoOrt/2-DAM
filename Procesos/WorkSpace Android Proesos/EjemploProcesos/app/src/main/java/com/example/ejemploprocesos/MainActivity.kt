package com.example.ejemploprocesos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.ejemploprocesos.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Limite maximo de la progress bar
        binding.pbHorizontal.max = 100

        // Inicializar progreso
        binding.pbHorizontal.setProgress(0)

        binding.bIniciarTarea.setOnClickListener {
            Snackbar.make(it, "Iniciamos el progreso", Snackbar.LENGTH_LONG).show()

            // Hacer visible la progressbar circular
            binding.pbCircular.visibility = android.view.View.VISIBLE

            // Incremento de la progressbar
            binding.pbHorizontal.incrementProgressBy(10)

            var gestorInterfaz: Handler = Handler(Looper.getMainLooper())
//
//            for (i in 1..10) {
//                gestorInterfaz.post(
//                    {
//                        Thread.sleep(100)
//                        binding.pbHorizontal.incrementProgressBy(10)
//                        // Mostrar progreso en el TextView
//                        binding.tvProgreso.text = binding.pbHorizontal.progress.toString() + " %"
//                    }
//                )
//
//            // Si no se hace Handler, cuando el proceso esta activo no se puede modificar
//            // la pantalla, ejemplo (edittext)
//            }

            var process: Thread = Thread({
                for (i in 1..10) {
                    gestorInterfaz.post(
                        {
                            Thread.sleep(100)
                            binding.pbHorizontal.incrementProgressBy(10)
                            // Mostrar progreso en el TextView
                            binding.tvProgreso.text =
                                binding.pbHorizontal.progress.toString() + " %"
                        }
                    )
                }
            }
            )
            process.start()
        }
    }
}
