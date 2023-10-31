package com.example.ejercicio_exam2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ejercicio_exam2.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    // Lista de personas almacenadas
    List<Persona> personas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);
    }

    // Método click del botón añadir
    public void clickBoton() {
        if (comporbarDatos()) {
            Persona p = new Persona(
                    binding.etNombre.getText().toString(),
                    (byte) ((binding.rgSexo.getCheckedRadioButtonId() == binding.rbHombre.getId()) ? 1 : 0));

            personas.add(p);

            ArrayAdapter adaptador = new ArrayAdapter<String>(
                    MainActivity.this,
                    R.layout,
                    personas);

            binding.lvUsuarios.setAdapter(adaptador);

        } else {
            Toast.makeText(
                    MainActivity.this,
                    "No se insertaron los datos necesarios",
                    Toast.LENGTH_SHORT).show();
        }
    }

    // Método clickar elemento de la lista
    public void pulsarElementList() {
        // TODO
        binding.btAdd.setEnabled(false);
    }

    // Metodo clickar menuItem
    public void clickMenu() {
        Toast.makeText(
                MainActivity.this,
                R.string.total + personas.size(),
                Toast.LENGTH_SHORT).show();
    }

    // Método comporbar si los datos fueron seleccionados
    public boolean comporbarDatos() {
        if (binding.etNombre.getText().toString().isEmpty() ||
                binding.etNombre.getText().toString().equals("")) {
            binding.tvNombre.setTextColor(Color.red(1));
            return false;
        } else if (binding.rgSexo.getCheckedRadioButtonId() == -1) {
            binding.tvSexo.setTextColor(Color.red(1));
            return false;
        } else {
            return true;
        }
    }

    // Clase Persona
    public class Persona {

        private String nombre;
        private byte sexo;

        public Persona(String nombre, byte sexo) {
            this.nombre = nombre;
            this.sexo = sexo;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public byte getSexo() {
            return sexo;
        }

        public void setSexo(byte sexo) {
            this.sexo = sexo;
        }
    }
}
