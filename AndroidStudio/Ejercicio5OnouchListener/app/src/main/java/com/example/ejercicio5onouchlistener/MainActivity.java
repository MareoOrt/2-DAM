package com.example.ejercicio5onouchlistener;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ejercicio5onouchlistener.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //    Binding
    ActivityMainBinding binding;

    //    Lista de los usuarios
    ArrayList<String> nombresArray= new ArrayList<>();
    ArrayList<String> contrasArray= new ArrayList<>();

    //    Adaptador del array
    ArrayAdapter adaptador = new ArrayAdapter<>(
            getApplicationContext(),
            android.R.layout.simple_list_item_1,
            nombresArray);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);

//        Llamada al método para inicializar
        inicilizar();
    }

    //    Metodo para inicializar componentes
    public void inicilizar() {

//        Añadimos el adaptadoral Spinner
        binding.spUsuarios.setAdapter(adaptador);

//        OnTouchListener del LinearLayout
        binding.linearConfirm.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                Si el usuario arrastro y metio todo los datos
                if (MotionEvent.ACTION_MOVE == event.getAction()) {
                    if (!binding.etNombre.getText().toString().isEmpty() &&
                            !binding.etContrasena.getText().toString().isEmpty()) {

                        Log.d(
                                "Accion realizada",
                                "EL usuario introdujo los datos");

                        nombresArray.add(binding.etNombre.getText().toString());
                        contrasArray.add(binding.etContrasena.getText().toString());

                        ((ArrayAdapter) binding.spUsuarios.getAdapter()).notifyDataSetChanged();

                        return true;

//                    Si el usuario arrastro y pero le falto el nombre
                    } else if (binding.etNombre.getText().toString().isEmpty() &&
                            !binding.etContrasena.getText().toString().isEmpty()) {

                        Log.d(
                                "Accion realizada",
                                "AL usuario le falto de poner el nombre");

                        Snackbar.make(v, "No has puesto el nombre", Snackbar.LENGTH_LONG).show();
                        return false;

//                    Si el usuario arrastro y pero le falto el nombre
                    } else if (binding.etNombre.getText().toString().isEmpty() &&
                            !binding.etNombre.getText().toString().isEmpty()) {

                        Log.d(
                                "Accion realizada",
                                "Al usuario le falto de poner la contraseña");

                        Snackbar.make(v, "No has puesto la contraseña", Snackbar.LENGTH_LONG).show();
                        return false;

                    }

//                    Si el usuario arrastro y pero le falto el nombre y la contraseña
                    else {

                        Log.d(
                                "Accion realizada",
                                "El usuario no introdujo ningun dato");

                        Snackbar.make(v, "No has puesto ningún dato", Snackbar.LENGTH_LONG).show();
                        return false;

                    }
                }else {
                    return false;
                }

            }
        });

//        OnClickListener del Spinner
        binding.spUsuarios.setOnClickListener(v -> {
            String usuarioSel= binding.spUsuarios.getSelectedItem().toString();
            int indiceUs = nombresArray.indexOf(usuarioSel);

            String nombreUs =  nombresArray.get(indiceUs);
            String contraUs = contrasArray.get(indiceUs);

            
        });

    }
}
