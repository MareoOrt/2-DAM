package com.example.ejercicioexampass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.ejercicioexampass.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
    // Botón OFF poner onclick...
    private boolean isButtonOn = false;

    public void btOffClicked(View view) {
        Button btOffOn = binding.btOffOn;
        Button btGrabar = binding.btGrabar;

        isButtonOn = !isButtonOn;
        btOffOn.setText(isButtonOn ? "ON" : "OFF");
        btGrabar.setEnabled(isButtonOn);
    }

    // Variable lista de personas
    private List<Persona> personList = new ArrayList<>();

    //Clase de personas
    public class Persona {
        private String nombre;
        private int edad;
        private String provincia;

        public Persona(String nombre, int edad, String provincia) {
            this.nombre = nombre;
            this.edad = edad;
            this.provincia = provincia;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public int getEdad() {
            return edad;
        }

        public void setEdad(int edad) {
            this.edad = edad;
        }

        public String getProvincia() {
            return provincia;
        }

        public void setProvincia(String provincia) {
            this.provincia = provincia;
        }
    }

    // Método grabar
    public void btGrabarClicked(View view) {
        EditText etNombre = binding.etNombret;
        EditText etEdad = binding.etEdad;
        Spinner spinnerProvincia = binding.spProvincias;

        String nombre = etNombre.getText().toString();
        int edad = Integer.parseInt(etEdad.getText().toString());
        String provincia = spinnerProvincia.getSelectedItem().toString();

        Persona persona = new Persona(nombre, edad, provincia);
        personList.add(persona);

        personAdapter.notifyDataSetChanged();
    }

    //Método listview
    public void onPersonSelected(int position) {
        Persona selectedPerson = personList.get(position);
        EditText etNombre = binding.etNombret;
        EditText etEdad = binding.etEdad;
        Spinner spinnerProvincia = binding.spProvincias;

        etNombre.setText(selectedPerson.getNombre());
        etEdad.setText(String.valueOf(selectedPerson.getEdad()));

        ArrayAdapter<String> spinnerAdapter = (ArrayAdapter<String>) spinnerProvincia.getAdapter();
        int provinciaIndex = spinnerAdapter.getPosition(selectedPerson.getProvincia());
        spinnerProvincia.setSelection(provinciaIndex);
    }

    public voidlistViewSel(){
        ListView listView = binding.lvUsuarios;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onPersonSelected(position);
            }
        });
    }

    public void
}