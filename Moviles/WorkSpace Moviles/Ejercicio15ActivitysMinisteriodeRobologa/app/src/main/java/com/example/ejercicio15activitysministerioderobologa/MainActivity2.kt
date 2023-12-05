package com.example.ejercicio15activitysministerioderobologa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import com.example.ejercicio15activitysministerioderobologa.databinding.ActivityMain2Binding
import com.example.ejercicio15activitysministerioderobologa.databinding.ActivityMainBinding

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btVotacion.setOnClickListener {
            var id = binding.radioGroup.checkedRadioButtonId
            var voto = findViewById<RadioButton>(id).text.toString()

            intent.putExtra("voto", voto)
            setResult(RESULT_OK, intent)

            finish()
        }
    }
}