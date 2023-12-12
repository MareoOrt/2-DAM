package com.example.ejem18_fragmentestaticos

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.ejem18_fragmentestaticos.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    var binding = ActivityMain2Binding.inflate(layoutInflater)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btVolver.setOnClickListener {
            setResult(RESULT_OK, intent)
            finish()
        }

    }
}