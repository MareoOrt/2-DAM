package com.example.ejemplo_07_menucontexto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerForContextMenu(findViewById(R.id.lyPrincipal));
    }

    // Este metodo identifica el metodo para gestionar la posición del elemento seleccionado
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

//        if (item.getItemId()==R.id.menu_opcion1){
//            Log.d("Depurando","Opcion 1 pulsado");
//
//        }
            Snackbar.make(findViewById(R.id.lyPrincipal), item.getTitle().toString(), Snackbar.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

    // Este metodo se encarga de visualizar el menu de aplicación crea el xml que le ha dado
    // del menu y visualizarlo cuando le des a los 3 puntitos
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    // Este metodo es para el menu contextual
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        return super.onContextItemSelected(item);
    }
}