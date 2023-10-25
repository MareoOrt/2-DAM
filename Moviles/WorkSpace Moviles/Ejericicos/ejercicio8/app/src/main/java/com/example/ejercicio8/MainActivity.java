package com.example.ejercicio8;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.ejercicio8.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);
    }

    public void imprimirDorsales() {
        for (int i = 4; i < 14; i++) {
            Button boton = new Button();

            boton.setText(i);
            boton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (binding.rgPuntos.isEnabled()) {
                        int puntosSumar = recuperarPuntos();

                        if (boton.getParent().equals(binding.trLocales)) {
                            int marcador = Integer.parseInt(binding.tvLocalM.getText().toString());
                            binding.tvLocalM.setText((marcador + puntosSumar));
                        } else {
                            int marcador = Integer.parseInt(binding.tvVisitM.getText().toString());
                            binding.tvVisitM.setText((marcador + puntosSumar));
                        }
                    }
                }
            });

            binding.trLocales.addView(boton);
            binding.trVisitantes.addView(boton);
        }
    }

    public void funcionesPuntos(){
        boolean seleccionDorsal = false;
        for (int i = 0; i < ; i++) {
            RadioButton rb = (RadioButton) binding.rgPuntos.getChildAt(i);
            if(binding.rgPuntos.getCheckedRadioButtonId() == rb.getId()){

            }
        }
    }

    public int recuperarPuntos() {
        if (binding.rgPuntos.getCheckedRadioButtonId() == binding.rbLibre.getId()) {
            return 1;
        } else if (binding.rgPuntos.getCheckedRadioButtonId() == binding.rb2p.getId()) {
            return 2;
        } else {
            return 3;
        }
    }


}