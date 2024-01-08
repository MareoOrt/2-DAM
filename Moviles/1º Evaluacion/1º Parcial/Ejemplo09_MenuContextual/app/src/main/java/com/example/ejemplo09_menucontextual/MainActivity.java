package com.example.ejemplo09_menucontextual;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

        //Registramos el menu contextual en el layout principal
        registerForContextMenu(findViewById(R.id.ly_principal));
    }
    //Se encarga de visualizar el menu de aplicacion y de construir el menu a partir del xml que
    //le indiquemos
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mi_menu,  menu);
        return true;
    }

    //Metodo para gestionar la opción seleccionada
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        /* No funciona con switch
        if(item.getItemId()== R.id.menu_opcion1){
           Log.d("depurando","Opcion 1 pulsada");
        }*/
        Snackbar.make(findViewById(R.id.ly_principal), item.getTitle().toString(),Snackbar.LENGTH_LONG).show();
        return super.onOptionsItemSelected(item);
    }

    // IMPLEMENTAR MENU CONTEXTUAL
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.mi_menu, menu);
    }
    //Recuperamos el metodo de las opciones seleccionadas del otro menu creado
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        return onOptionsItemSelected(item);
    }
    }