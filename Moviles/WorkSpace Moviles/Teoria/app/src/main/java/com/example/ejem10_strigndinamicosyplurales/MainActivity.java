package com.example.ejem10_strigndinamicosyplurales;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ejem10_strigndinamicosyplurales.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate((getLayoutInflater()));
        setContentView(binding.getRoot());

        binding.btRecuperar.setOnClickListener(view -> {
            String nombre = binding.etNombre.getText().toString();
            int edad = binding.etEdad.getText().toString().equals("")?0:Integer.parseInt(binding.etEdad.getText().toString());

            String cadenaPlural = getResources().getQuantityString(R.plurals.edad, edad, edad);
            String cadenaDinamica = getResources().getString(R.string.saludo, nombre);
            String cadeanFinal = cadenaDinamica + cadenaPlural;
            binding.tvSaludar.setText(cadeanFinal);
        });

    }
}