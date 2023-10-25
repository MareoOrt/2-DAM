package com.example.ejercicio2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button) findViewById(R.id.id_guardar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = ((EditText) findViewById(R.id.id_nombre)).getText().toString();
                String estado = opcionEscogida();
                String provincia = ((Spinner) findViewById(R.id.id_provincias)).getSelectedItem().toString();
                boolean futbol = ((CheckBox) findViewById(R.id.id_futbol)).isActivated();
                boolean baloncesto = ((CheckBox) findViewById(R.id.id_balocensto)).isActivated();
                boolean tenis = ((CheckBox) findViewById(R.id.id_tenis)).isActivated();

                String mensaje = "";

                if (!nombre.isEmpty()) {
                    mensaje = "Tu nombre es " + nombre + " estas " + estado + " vives en " +
                            provincia + " juegas a";
                } else if (!estado.isEmpty()) {
                    mensaje = "Estas " + estado + " vives en " +
                            provincia + " juegas a";
                } else {
                    mensaje = "Vives en " +
                            provincia + " y juegas a";
                }
                if (futbol) {
                    mensaje = mensaje + " futbol";
                }
                if (baloncesto) {
                    mensaje = mensaje + " baloncesto";
                }
                if (tenis) {
                    mensaje = mensaje + " tenis";
                }
                if (!futbol && !baloncesto && !tenis) {
                    mensaje = mensaje + " nada";
                }

                Log.println(Log.DEBUG, "Datos", mensaje);
                Snackbar.make(view,"Revisa el Logcat ;)", Snackbar.LENGTH_LONG).show();
            }
        });

    }

    public String opcionEscogida() {
        String opcionSeleccionada = "";
        int id = ((RadioGroup) findViewById(R.id.id_radioGroup)).getCheckedRadioButtonId();

        RadioButton radioButton = findViewById(id);
        if (radioButton != null) {
            opcionSeleccionada = radioButton.getText().toString();
        }
        return opcionSeleccionada;
    }
}