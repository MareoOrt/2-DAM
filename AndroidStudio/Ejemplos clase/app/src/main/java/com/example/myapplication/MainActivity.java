package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inializarSpinner();
        inicializarRadioButton();
        inicializarSwitch();

    }

    private void inicializarSwitch() {
        ((android.widget.Switch) findViewById(R.id.switch1)).setOnCheckedChangeListener((compoundButton, casado) -> {
                    Log.d("depuracion", "Seleccionado:" + casado);
                    if(casado){
                        Toast.makeText(getApplicationContext(), "casado", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Snackbar.make(compoundButton, "soltero", Snackbar.LENGTH_SHORT).show();
                    }
                }
                );
    }

    private void inicializarRadioButton() {
        ((RadioGroup) findViewById(R.id.radioGroup)).setOnCheckedChangeListener((radioGroup, i) ->
                Log.d("depuracion", ((TextView) findViewById(i)).getText().toString())
        );
    }

    private void inializarSpinner() {

        ArrayList<String> datos = new ArrayList<>();
        datos.add("Valladolid");
        datos.add("Zamora");
        datos.add("Soria");
        datos.add("Salamanca");
        datos.add("Segovia");
        datos.add("Avila");
        datos.add("Palencia");
        datos.add("Burgos");
        datos.add("Leon");

        Spinner desplegableCCAA = findViewById(R.id.spinner2);
        ArrayAdapter<String> adaptadorCCAA = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, datos);
        desplegableCCAA.setAdapter(adaptadorCCAA);

        datos.add("Burgos de Osma");
    }

}