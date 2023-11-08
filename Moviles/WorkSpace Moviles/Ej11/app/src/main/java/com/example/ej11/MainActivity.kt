package com.example.ej11

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.ej11.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    class Producto(val descripcion: String, val precio: Double)

    val listProducto = ArrayList<Producto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lvListaCompra.adapter = ArrayAdapter(
            this,
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
            listProducto
        )

    }

    fun clickAdd(v: Button) {
        var desc = binding.tilEtNombre.text.toString()
        var precio = binding.tilEtPrecio.text.toString()

        if (comporbarDou(precio) != 0.0) {
            listProducto.add(Producto(desc, precio.toDouble()))
            (binding.lvListaCompra.adapter as ArrayAdapter<Producto>).notifyDataSetChanged()
            binding.tilEtNombre.setText("")
            binding.tilEtPrecio.setText("")
        }
    }

    fun comporbarDou(precio: String): Double {
        var dou = 0.0
        try {
            dou = precio.toDouble()
        } catch (e: Exception) {
            try {
                var cadena = precio.split(",")
                var t = cadena[0] + "." + cadena[1]
                dou = t.toDouble()
            } catch (e: Exception) {
                Snackbar.make(
                    parent,
                    binding.clPrincipal,
                    "No se escribio un valor de dinero correcto",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
        return dou
    }

    fun clickBorrar(v: Button) {
        val selectedItem = binding.lvListaCompra.selectedItem as Producto

        val index = listProducto.indexOf(selectedItem)
        if (index != -1) {
            listProducto.removeAt(index)
        }

        (binding.lvListaCompra.adapter as ArrayAdapter<Producto>).notifyDataSetChanged()
    }

    fun clickView(v: Button) {
        val selectedItem = binding.lvListaCompra.selectedItem as Producto

        binding.tilEtNombre.setText(selectedItem.descripcion)
        binding.tilEtPrecio.setText(selectedItem.precio.toString())
    }
}