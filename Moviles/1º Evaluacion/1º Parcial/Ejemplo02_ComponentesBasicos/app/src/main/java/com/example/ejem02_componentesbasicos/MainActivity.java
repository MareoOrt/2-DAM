package com.example.ejem02_componentesbasicos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

        inicializarSpinnerComunidad();
        inicializarRadioButton();
        inicializarSwitch();







    }

    private void inicializarSwitch() {
        ((android.widget.Switch)findViewById(R.id.s_casado)).setOnCheckedChangeListener((compoundButton, casado) -> {
            Log.d("depurando",""+casado);
            if (casado)
                Toast.makeText(getApplicationContext(),"Casado",Toast.LENGTH_SHORT).show();
            else
                Snackbar.make(compoundButton,"Soltero",Snackbar.LENGTH_SHORT).show();

        });
    }

    private void inicializarRadioButton() {
    /*    ((RadioGroup)findViewById(R.id.rg_profesion)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Log.d("depurando",((TextView)findViewById(i)).getText().toString());
            }
        });*/
        ((RadioGroup)findViewById(R.id.rg_profesion)).setOnCheckedChangeListener( (radioGroup, i) ->
        {
            Log.d("depurando",((TextView)findViewById(i)).getText().toString());
        });

    }

    private void inicializarSpinnerComunidad() {
        //String[] datos = {"Valladolid", "Zamora", "Soria", "Salamanca", "Segovia", "Avila", "Palencia", "Burgos", "Leon"};
        ArrayList<String> datos=new ArrayList<>();
        datos.add("Valladolid");
        datos.add("Zamora");
        datos.add("Soria");
        datos.add("Salamanca");
        datos.add("Segovia");
        Spinner desplegable_ComunidadAuto=((Spinner)findViewById(R.id.s_comunidadAutonoma));
        ArrayAdapter<String> adaptador_ComunidadAuto=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,datos);
        desplegable_ComunidadAuto.setAdapter(adaptador_ComunidadAuto);
        datos.add("Avila");

        desplegable_ComunidadAuto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("depurando",((TextView)view).getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}