package com.example.ejercicio18sharedpreferencesqliteyfragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AlertDialogLayout
import com.example.ejercicio18sharedpreferencesqliteyfragments.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

//    fromJson
//    toJson

    var gson = Gson()

    data class User(val nombre: String, var telf: String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = getSharedPreferences("datos", MODE_PRIVATE)

        var userList = HashSet<String>()

        binding.btGuardar.setOnClickListener() {
            val nombre = binding.tvName.text.toString()
            val telf = binding.tvTelf.text.toString()
            val us = User(nombre, telf)

            userList.add(gson.toJson(us))

            val editor = pref.edit()
            editor.putStringSet("Usuarios", userList)
            editor.commit()
        }

        binding.btFin.setOnClickListener() {
            finish()
        }

        binding.btHistorico.setOnClickListener() {
            val users = pref.getStringSet("users", HashSet<String>())
        }
    }

    fun checkNum(n: String): Boolean {
        var b = true
        try {
            n.toInt()
        } catch (e: Exception) {
            Toast.makeText(this, "No introduciste un valor numerico valido", Toast.LENGTH_SHORT)
                .show()
            b = false
        }
        if (n.length != 9) {
            Toast.makeText(this, "No introduciste un numero de 9 caracteres", Toast.LENGTH_SHORT)
                .show()
            b = false
        }
        return b
    }


    fun showUsers(list: HashSet<String>) {// TODO
        //var users = gson.fromJson(gson, list.)
    }
}