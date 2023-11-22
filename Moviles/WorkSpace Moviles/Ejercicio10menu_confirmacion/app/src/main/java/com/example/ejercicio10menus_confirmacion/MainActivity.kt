package com.example.ejercicio10menus_confirmacion

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.ejercicio10menus_confirmacion.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerForContextMenu(binding.lLayout)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        confirmar(item.title.toString())
        return true
    }

    private fun confirmar(color:String) {
        var colorSeleccionado: Int =0
        when(color){
            "Rojo" -> colorSeleccionado=Color.RED;
            "Verde" -> colorSeleccionado=Color.GREEN;
            "Amarillo" -> colorSeleccionado=Color.YELLOW;
            "Blanco" -> colorSeleccionado=Color.WHITE;
        }
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Cambio de color")
        builder.setMessage("Vas a cambiar el color de fondo a "+color+", estas seguro??")
        builder.setPositiveButton("Si"){dialog, which ->
            binding.lLayout.setBackgroundColor(colorSeleccionado)
            dialog.cancel()
        }
        builder.setNegativeButton("no"){dialog, which->
            Snackbar.make(binding.root, "Pues vale", Snackbar.LENGTH_SHORT).show()
            dialog.cancel()
        }
        builder.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mi_menu, menu)
        return true
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.menu_contextual, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        confirmar(item.title.toString())
        return true
    }
}