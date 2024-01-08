package com.example.ejemplo08_stringdinamicosyplurales;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.ejemplo08_stringdinamicosyplurales.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = binding.etNombre.getText().toString();
                int edad = binding.etEdad.getText().toString().equals("") ? 0 : Integer.parseInt(binding.etEdad.getText().toString());

                // la cadena dinamica, pasa por el parametro R.string (archivo string.xml) el saludo
                // y le pasas el nombre y la edad
                String cadenaDinamica = getResources().getString(R.string.Saludo, nombre, edad);
                binding.tvSaludar.setText(cadenaDinamica);
                String cadenaDinamica2 = getResources().getQuantityString(R.plurals.edad, edad, edad);
                binding.tvCadenaplural.setText(cadenaDinamica2);
            }
        });
    }
}