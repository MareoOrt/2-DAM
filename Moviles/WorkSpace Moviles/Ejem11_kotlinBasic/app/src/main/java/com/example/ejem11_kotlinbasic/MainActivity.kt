package com.example.ejem11_kotlinbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Creacion de variable
        /*
        var nombre:String = "Juan"

        nombre:String = "Juan"

        nombre = "Juan"
        */
        var nombre: String = "Juan"

        // Concatenar en String
        nombre += " Pérez"

        // Log
        Log.d("depuración", "El nombre es " + nombre)
        Log.d("depuración", "y el $nombre y tiene ${nombre.length} digitos")

        // Existen dos tipos de variable los var o val, donde los var no vpueden cambiar de valor
        // Ya no hay tipos de datos de primitivos(int, boolean, etc.) , solo objetos

        // Fecha
        var fecha: String = "2021-10-20"

        // Subsequence
        // Para sacar una cadena de otras
        var y: CharSequence = fecha.subSequence(1, 5)

        // When
        // Estructura de control que sustituye al switch -> when
        when (y) {
            "2010" -> Log.d("depuarción del año", "Eres del 2010")
            else -> Log.d("depuración del año", "NO eres del 2010")
        }

        // Podemos hacer comparaiciones para establecer unvalor a una variable, y hacer comparaciones
        // con franjas de valor
        var nota = 7
        var calificacion = when {
            nota > 5 -> "Suspenso"
            nota in 5..6 -> "Aprobado"
            nota in 7..8 -> "Notable"
            nota in 9..10 -> "Sobresaliente"
            else -> "Nota no valida"
        }

        when (fecha.subSequence(5, 7).toString().toInt()) {
            1, 2, 3 -> Log.d("depuarcion del mes", "Primer Trimestre")
            4, 5, 6 -> Log.d("depuarcion del mes", "Segundo Trimestre")
            7, 8, 9 -> Log.d("depuarcion del mes", "Tercero Trimestre")
            10, 11, 12 -> Log.d("depuarcion del mes", "Cuarto Trimestre")
            else -> Log.d("depuarcion del mes", "Mes no valido")
        }

        // If
        if (fecha.subSequence(5, 7).toString().toInt() in 1..3) {

        }

        // Funciones
        // Podemos hacer esto donde se indica al final el tipo de dato que devuelve
        fun suma(a: Int, b: Int): Int {
            return a + b;
        }
        Log.d("depuarcion", "La suma de 2 y 3 es ${suma(2, 3)}")

        // o esto mas rapido
        fun resta(a: Int, b: Int) = a - b

        // Bucles
        // Bucle FOR
        // Donde ponemos la variable con su fraja y de cuanto en cuanto va
        for (i in 1..10 step 2) {
            Log.d("depuarcion con for", "AQui el contador es $i")
        }

        // Arrays
        // Donde lo rellenamos con arrayOf
        var nombres: Array<String> = arrayOf("Juan", "Pedro", "Luis")

        // Bucle FOREACH
        for (nombre in nombres) {
            Log.d("depuracion de nombres", "Esta $nombre")
        }

        var casillaNombre: TextInputEditText = findViewById(R.id.tifNombre)
        var botonPulsar: MaterialButton = findViewById(R.id.mbPulsar)

        // OnClickListener con lambda, donde para referirnos al boton que presionadmos como it
        // Ademas de que el casteo es con as
        botonPulsar.setOnClickListener {
            Log.d(
                "depuracion con el materail buton", "El nombre es: ${casillaNombre.text}"
                        + (it as MaterialButton).text.toString()
            )
        }

    }
}