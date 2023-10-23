package com.example.ejercicio3;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ejercicio3.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private boolean visible = false;
    private ArrayList<String> array = new ArrayList<>();

    private String itemSeleccionado = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        inicializarBotonAnadir();
        inicializarBotonAnadir();
        binding.idLista.setOnItemClickListener((adapterView, view, i, l) -> {
            recuperarItem(i);
        });

    }

    private void inicializarBotonAnadir() {
        binding.idBotonAnadir.setOnClickListener(view -> {

            String dato = binding.idDatosET.getText().toString();
            array.add(dato);

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item, array);

            binding.idLista.setAdapter(adapter);

            if(!visible){
                binding.idLista.setVisibility(View.VISIBLE);
                visible = true;
            }
        });
    }

    private void inicializarBotonBorrar() {
        binding.idBotonBorrar.setOnClickListener(view -> {

            if(!itemSeleccionado.isEmpty()){
                array.remove(array.indexOf(Integer.parseInt(itemSeleccionado)));

                ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, array);

                binding.idLista.setAdapter(adapter);

                if(array.isEmpty()){
                    binding.idLista.setVisibility(View.INVISIBLE);
                    visible = false;
                }
            }else{
                Snackbar.make(view,
                        "No has seleccionado ningun item de la lista",
                        Snackbar.LENGTH_LONG).show();
            }

        });
    }


    private void recuperarItem(int indiceSelec){
        this.itemSeleccionado = (String) binding.idLista.getItemAtPosition(indiceSelec);
    }
}