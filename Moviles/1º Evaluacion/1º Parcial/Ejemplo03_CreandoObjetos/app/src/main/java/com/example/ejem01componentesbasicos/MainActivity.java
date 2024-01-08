package com.example.ejem01componentesbasicos;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarSpinnerComunidad();
        inicializarRadioButton();
        inicializarSwitch();
        inicializarDesplegable();
        inicializarDesplegableDoble();
        guardarDatosUsuario();
        /*Añadir al desplegable doble nuevos datos, Al pulsar cualquiera de los dos deplegables muestra en consola los datos del objeto usuario
         * que esta en esa posicion*/


    }

    private void inicializarDesplegable() {
        Spinner desplegableDatos = (Spinner) findViewById(R.id.s_datosUsuarios);
        ArrayList<String> datosDeplegable = new ArrayList<>();
        ArrayAdapter<String> adaptadorDatos = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, datosDeplegable);
        desplegableDatos.setAdapter(adaptadorDatos);
    }

    private void inicializarDesplegableDoble() {
        Spinner desplegableDatos2 = (Spinner) findViewById(R.id.s_datosUsuarios2);
        ArrayList<HashMap<String, String>> datosDesplegableDoble = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> item = new HashMap<String, String>();
        item.put("Nombre", "Juan");
        item.put("Profesion", "Informatico");
        datosDesplegableDoble.add(item);
        SimpleAdapter adaptadorDatos = new SimpleAdapter(
                this,
                datosDesplegableDoble,
                android.R.layout.simple_list_item_2,
                new String[]{"Nombre", "Profesion"},
                new int[]{android.R.id.text1, android.R.id.text2});
        desplegableDatos2.setAdapter(adaptadorDatos);




    }

    private void guardarDatosUsuario() {
        ((Button) findViewById(R.id.b_recuperarTexto)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario usuario = new Usuario();
                usuario.setNombre(((TextView) findViewById(R.id.et_nombre)).getText().toString());
                usuario.setComunidadAutonoma(((Spinner) findViewById(R.id.s_comunidadAutonoma)).getSelectedItem().toString());
                usuario.setCiudadNacimiento(((Spinner) findViewById(R.id.s_ciudadDeNacimiento)).getSelectedItem().toString());
                RadioButton rb_selecinado = findViewById((((RadioGroup) findViewById(R.id.rg_profesion)).getCheckedRadioButtonId()));
                usuario.setProfesion(rb_selecinado.getText().toString());
                usuario.setCasado(((Switch) findViewById(R.id.s_soltero)).isChecked());
                ArrayList<String> deportes = new ArrayList<>();
                if (((CheckBox) findViewById(R.id.cb_baloncesto)).isChecked()) {
                    deportes.add("Baloncesto");
                }
                if (((CheckBox) findViewById(R.id.cb_futbol)).isChecked()) {
                    deportes.add("Futbol");
                }
                if (((CheckBox) findViewById(R.id.cb_pingpong)).isChecked()) {
                    deportes.add("PingPong");
                }
                usuario.setDeportes(deportes);
                Log.d("DEPURANDO", usuario.toString());

                Spinner desplegableDatos = ((Spinner) findViewById(R.id.s_datosUsuarios));
                ((ArrayAdapter<String>) desplegableDatos.getAdapter()).add(usuario.toString());

            }
        });


    }

    private void inicializarSwitch() {
       /* ((Switch)findViewById(R.id.s_soltero)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.d("Depurando","Activado: "+b);
            }
        });*/
        ((Switch) findViewById(R.id.s_soltero)).setOnCheckedChangeListener((compoundButton, casado) -> {
            Log.d("Depurando", "Activado: " + casado);

            if (casado) {
                Toast.makeText(getApplicationContext(), "Casado", Toast.LENGTH_SHORT).show();
            } else {
                Snackbar.make(compoundButton, "Soltero", Snackbar.LENGTH_SHORT).show();
            }
        });


    }

    private void inicializarRadioButton() {
      /*  ((RadioGroup)findViewById(R.id.rg_profesion)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                 Log.d("Depurando",((TextView)findViewById(i)).getText().toString()));
            }
        });*/
        ((RadioGroup) findViewById(R.id.rg_profesion)).setOnCheckedChangeListener((radioGroup, i) ->
                Log.d("Depurando", ((TextView) findViewById(i)).getText().toString()));
    }

    private void inicializarSpinnerComunidad() {

        //Array de ciudades
        //String[] datos = {"Valladolid", "Zamora", "Soria", "Salamanca", "Segovia", "Avila", "Palencia", "Burgos", "Leon"};
        ArrayList<String> datos = new ArrayList<>();
        datos.add("Valladolid");
        datos.add("Zamora");
        datos.add("Soria");
        datos.add("Salamanca");
        datos.add("Segovia");
        datos.add("Avila");
        datos.add("Palencia");
        datos.add("Burgos");
        Spinner despleglable_comunidadAuto = (Spinner) findViewById(R.id.s_comunidadAutonoma);
        //Esta parametrizada , contexto objeto que se crea y persiste durante la ejecucion de la aplicacion y le podemos pedir datos.
        //Layouts por defecto  android.R.layout, tambien se pueden crear personalizados desde Layout
        ArrayAdapter<String> adaptadorComunidad = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, datos);
        despleglable_comunidadAuto.setAdapter(adaptadorComunidad);
        datos.add("León");

        despleglable_comunidadAuto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("Depurando", ((TextView) view).getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}

