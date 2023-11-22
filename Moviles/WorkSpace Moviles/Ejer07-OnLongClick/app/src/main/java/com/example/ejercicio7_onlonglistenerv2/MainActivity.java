package com.example.ejercicio7_onlonglistenerv2;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import com.example.ejercicio7_onlonglistenerv2.databinding.ActivityMainBinding;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    Spinner sp_numeros;
    TextView tv_calificar;
    TextView tv_resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sp_numeros =binding.spNumeros;
        tv_calificar = binding.tvCalificar;
        tv_resultado = binding.tvResultado;

        sp_numeros.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (binding.lyBotones.getChildCount()>0)
                    binding.lyBotones.removeAllViews();
                for (int contador=0;contador<Integer.parseInt(((TextView)view).getText().toString());contador++){
                    binding.lyBotones.addView(new Button(MainActivity.this));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


       tv_calificar.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
            public boolean onLongClick(View view) {
                String elemento = (String) sp_numeros.getSelectedItem();
                tv_resultado.setText("Has sacado un " + elemento);
                return false;
            }

            @Override
            public boolean onLongClickUseDefaultHapticFeedback(@NonNull View v) {
                return View.OnLongClickListener.super.onLongClickUseDefaultHapticFeedback(v);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mi_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int idMenu = item.getItemId();
        if (idMenu==R.id.menu_limpiar) {
            tv_resultado.setText("");
        }
        else if (idMenu==R.id.menu_salir)
            finish();
        return super.onOptionsItemSelected(item);
    }

}