package com.example.ejemplo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //anadirBotonTiempoEjecucion();
        //metodosTouch();
        metodosTouchConMotionEvent();


    }

    public void anadirBotonTiempoEjecucion() {
        Button boton = new Button(this);
        boton.setText("Saludar");
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"Hola", Snackbar.LENGTH_LONG).show();

            }
        });
        ((LinearLayout)findViewById(R.id.id_inferior)).addView(boton);

    }

    public void metodosTouch(){
        ((LinearLayout)findViewById(R.id.id_fondo)).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Snackbar.make(view,"Tocaste fondo :(.ACTION_DOWN", Snackbar.LENGTH_LONG).show();
                return false;
            }
        });

        ((LinearLayout)findViewById(R.id.id_central)).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Snackbar.make(view,"Tocaste el layout central", Snackbar.LENGTH_LONG).show();
                return true;
            }
        });

        ((LinearLayout)findViewById(R.id.id_inferior)).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Snackbar.make(view,"Tocaste el layout superior", Snackbar.LENGTH_LONG).show();
                return true;
            }
        });
    }

    public void metodosTouchConMotionEvent(){
        ((LinearLayout)findViewById(R.id.id_fondo)).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Snackbar.make(view,"Tocaste fondo :(.ACTION_DOWN", Snackbar.LENGTH_LONG).show();
                return false;
            }
        });

        ((LinearLayout)findViewById(R.id.id_central)).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Snackbar.make(view,"Tocaste el layout central", Snackbar.LENGTH_LONG).show();
                return true;
            }
        });

        ((LinearLayout)findViewById(R.id.id_inferior)).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Snackbar.make(view,"Tocaste el layout superior", Snackbar.LENGTH_LONG).show();
                TextView coordenadas = ((TextView) findViewById(R.id.tvCoordenadas));
                coordenadas.setText("x: " + motionEvent.getX()+ ", y: " + motionEvent.getY());

                switch (motionEvent.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        Snackbar.make(view,"Tocaste con ACTION_DOWN", Snackbar.LENGTH_LONG).show();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Snackbar.make(view,"Tocaste con ACTION_MOVE", Snackbar.LENGTH_LONG).show();
                        break;
                    case MotionEvent.ACTION_UP:
                        Snackbar.make(view,"Tocaste con ACTION_UP", Snackbar.LENGTH_LONG).show();
                        break;
                }
                return true;
            }
        });
    }
}