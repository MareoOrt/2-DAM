package com.example.serrudo_michael;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.serrudo_michael.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private Integer suspensos=0;
    private ArrayList<String> lista_nombre;
    private ArrayList<Persona> lista_persona;
    ArrayAdapter<String> adapter;
    private Persona persona;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerForContextMenu(findViewById(R.id.ll_principal));
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        adapter= new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,lista_nombre);
        lista_nombre=new ArrayList<>();
        lista_persona= new ArrayList<>();
        persona=new Persona("Michael","Aprobado");
        binding.setPersona(persona);
        AsignarEventos();

    }
    private void AsignarEventos(){

        binding.lvLista.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("depurando",lista_persona.get(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        persona= new Persona(binding.etNombre.getText().toString(),
                binding.spCalificacion.getSelectedItem().toString());
        if(item.getTitle().equals("Guardar")){
            binding.lvLista.setAdapter(adapter);
            adapter.add(persona.getNombre());
            if(persona.getCalificacion().equals("Suspenso")){
                suspensos +=suspensos+1;
                binding.tvNumerosuspensos.setText(Integer.toString(suspensos)+" suspensos");
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }
}