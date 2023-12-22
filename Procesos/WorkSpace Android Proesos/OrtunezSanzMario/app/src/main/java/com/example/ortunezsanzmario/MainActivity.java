package com.example.ortunezsanzmario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.ortunezsanzmario.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private List<User> userList = new ArrayList<>();
    private ArrayAdapter adapter;
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new ArrayAdapter(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                userList);

        binding.lvUsers.setAdapter(adapter);

        binding.btAdd.setOnClickListener(v -> {
            String nombre = binding.etNombre.getText().toString();
            String sexo = (binding.rgSexo.getCheckedRadioButtonId() == binding.rbHombre.getId())
                    ? binding.rbHombre.getText().toString()
                    : binding.rbMujer.getText().toString();

            if (nombre.isEmpty() || sexo.isEmpty()) {
                Snackbar.make(
                        binding.getRoot()
                        , "Se requieren rellenar todos los datos"
                        , Snackbar.LENGTH_SHORT
                ).show();
            } else {

                User us = new User(nombre, (sexo.equals("Mujer")));
                userList.add(us);

                Snackbar.make(
                        binding.getRoot(),
                        us.toString() + " fuiste añadido/a",
                        Snackbar.LENGTH_SHORT
                ).show();

                adapter.notifyDataSetChanged();
            }
        });

        binding.tbtBolqueo.setOnClickListener(v -> {
            binding.btAdd.setEnabled(!(binding.btAdd.isEnabled()));
        });

        binding.lvUsers.setOnItemClickListener((adapterView, view, i, l) -> {
            User us = (User) binding.lvUsers.getItemAtPosition(i);
            Toast.makeText(this, us.getInfo(), Toast.LENGTH_SHORT).show();
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // AlertDialog alert = new AlertDialog.Builder
        return super.onOptionsItemSelected(item);
    }
}