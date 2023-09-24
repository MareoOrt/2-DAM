package com.example.ejercicio1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button boton = (Button) findViewById(R.id.boton);

        boton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            EditText nombre = (EditText) findViewById(R.id.nombre);
            String nombreTxt = nombre.getText().toString();

            TextView texto = (TextView) findViewById(R.id.texto);
            String tesxtoTxt = texto.getText().toString();

            String txt = tesxtoTxt + "\nBuenos dias " + nombreTxt;
            texto.setText(txt);
        });

    }
}