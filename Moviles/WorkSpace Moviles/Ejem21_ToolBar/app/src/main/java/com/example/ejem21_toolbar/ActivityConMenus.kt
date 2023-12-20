package com.example.ejem21_toolbar

import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ejem21_toolbar.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

open class ActivityConMenus : AppCompatActivity() {
    // lateinit var binding : ActivityMainBinding
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_personalizado, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.mi_item1) {
            Toast.makeText(this, "Opcion 1", Toast.LENGTH_LONG).show()
            //Snackbar.make(binding.root, "Opcion 1", Snackbar.LENGTH_LONG).show()
        }
        if (item.itemId == R.id.mi_item2) {
            Toast.makeText(this, "Opcion 2", Toast.LENGTH_LONG).show()
            //Snackbar.make(binding.root, "Opcion 2", Snackbar.LENGTH_LONG).show()
        }

        return super.onOptionsItemSelected(item)
    }
}