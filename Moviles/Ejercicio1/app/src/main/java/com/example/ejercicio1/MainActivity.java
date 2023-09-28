package com.example.ejercicio1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int sumando1 = 0;
    int sumando2 = 0;

    TextView calculo = findViewById(R.id.calculo);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarBotones();
    }

    public void inicializarBotones() {
        ArrayList<Button> listaBotones = new ArrayList<>();
        

    }
}