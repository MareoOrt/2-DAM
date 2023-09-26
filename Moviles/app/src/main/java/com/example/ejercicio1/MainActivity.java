package com.example.ejercicio1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int sumando1 = 0;
    int sumando2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarBotones();
    }

    public void inicializarBotones() {
        TextView calculo = findViewById(R.id.calculo);

        Button boton1 = findViewById(R.id.boton1);
        Button boton2 = findViewById(R.id.boton2);
        Button boton3 = findViewById(R.id.boton3);
        Button boton4 = findViewById(R.id.boton4);
        Button boton5 = findViewById(R.id.boton5);
        Button boton6 = findViewById(R.id.boton6);
        Button boton7 = findViewById(R.id.boton7);
        Button boton8 = findViewById(R.id.boton8);
        Button boton9 = findViewById(R.id.boton9);
        Button botonMas = findViewById(R.id.botonSuma);
        Button botonMenos = findViewById(R.id.botonResta);
        Button botonIgual = findViewById(R.id.botonIgual);
        Button botonLimpiar = findViewById(R.id.botonlimpiar);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculo.append("1");
            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculo.append("2");
            }
        });

        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculo.append("3");
            }
        });

        boton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculo.append("4");
            }
        });

        boton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculo.append("5");
            }
        });

        boton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculo.append("6");
            }
        });

        boton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculo.append("7");
            }
        });

        boton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculo.append("8");
            }
        });

        boton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculo.append("9");
            }
        });

        botonMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sumando1 == 0) {
                    sumando1 = Integer.parseInt(calculo.getText().toString());
                }else if(sumando2 == 0){
                    try {
                    String[] sumando = calculo.getText().toString().split("+");
                   sumando2 = Integer.parseInt(sumando[1]);
                    }catch (Exception e){
                    }
                    try {
                        String[] sumando = calculo.getText().toString().split("-");
                        sumando2 = Integer.parseInt(sumando[1]);
                    }catch (Exception e){
                    }
                }
                }else{
                    sumando1 = sumando1+sumando2;
                    sumando2 = 0;
                    calculo.setText(sumando1 +"");
                }
                calculo.append("+");
            });

        botonMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if (sumando1 == 0) {
                        sumando1 = Integer.parseInt(calculo.getText().toString());
                    }else if(sumando2 == 0){
                        String[] sumando = calculo.getText().toString().split("-");
                        sumando2 = Integer.parseInt(sumando[1]);
                    }else{
                        sumando1 = sumando1+sumando2;
                        sumando2 = 0;
                        calculo.setText(sumando1 +"");
                    }
                    calculo.append("+");
            }
        });

        botonIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] resultado = calculo.getText().toString().split("");

                if (resultado.length == 3) {
                    int num1 = Integer.parseInt(resultado[0]);
                    int num2 = Integer.parseInt(resultado[2]);
                    int resultadoCalculo;

                    if (resultado[1].equals("+")) {
                        resultadoCalculo = num1 + num2;
                    } else if (resultado[1].equals("-")) {
                        resultadoCalculo = num1 - num2;
                    } else {
                        resultadoCalculo = 0;
                    }

                    calculo.setText(String.valueOf(resultadoCalculo));
                }
            }
        });
    }
}