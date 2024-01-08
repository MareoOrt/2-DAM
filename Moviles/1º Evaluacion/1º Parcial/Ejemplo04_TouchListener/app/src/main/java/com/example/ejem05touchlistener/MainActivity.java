package com.example.ejem05touchlistener;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //FORMA UNO
        ((LinearLayout)findViewById(R.id.ly_fondo)).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Snackbar.make(view, "Tocaste el layout Fondo", Snackbar.LENGTH_LONG).show();
                return false;
            }
        });
        ((LinearLayout)findViewById(R.id.ly_superiior)).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //Snackbar.make(view, "Tocaste el layout Superior", Snackbar.LENGTH_LONG).show();
                TextView coordenadas = (TextView)findViewById(R.id.tv_coordenadas);
                coordenadas.setText("X:"+motionEvent.getX()+" Y:"+motionEvent.getY());
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Snackbar.make(view, "Tocaste el layout Superior.ActionDOWN", Snackbar.LENGTH_LONG).show();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Snackbar.make(view, "Tocaste el layout Superior.Action_MOVE", Snackbar.LENGTH_LONG).show();
                        break;
                    case MotionEvent.ACTION_UP:
                        Snackbar.make(view, "Tocaste el layout Superior.Action_UP", Snackbar.LENGTH_LONG).show();
                        break;
                    default:
                        return false;
                }
                return true; //Si hemos gestionado el evento no lo propagamos.
                //return false; //No hemos gestionado el evento no lo propagamos.
            }
        });
        ((LinearLayout)findViewById(R.id.ly_central)).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Snackbar.make(view, "Tocaste el layout Central", Snackbar.LENGTH_LONG).show();
                return true; //Si hemos gestionado el evento no lo propagamos.
            }
        });

        /* FORMA 2
        ((LinearLayout)findViewById(R.id.ly_fondo)).setOnTouchListener((view, motionEvent) -> {
            return  false;
        });
         ((LinearLayout)findViewById(R.id.ly_superiior)).setOnTouchListener((view, motionEvent) -> {
            return  false;
        });
        */
    }
}