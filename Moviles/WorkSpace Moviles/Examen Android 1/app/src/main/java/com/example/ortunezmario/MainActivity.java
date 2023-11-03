package com.example.ortunezmario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.ortunezmario.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding vb;
    ActivityMainBinding db;

    List<Alumno> alumnosList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = DataBindingUtil.setContentView(this, R.layout.activity_main);
        vb = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(vb.getRoot());
        spClick();
    }

    public void spClick(){
        vb.spCalifiaciones.setOnItemClickListener((adapterView, view, i, l) -> {
            String nombre = vb.etNombre.getText().toString();
            boolean estAlumno = false;
            String calificacion = view.toString();
            Alumno alumno = null;

            int nota = (calificacion.contains("Suspenso"))? 1 :
                    (calificacion.contains("Aprobado"))? 2: 3;
            for (int j = 0; j < alumnosList.size(); j++) {
                if(alumnosList.get(j).getNombre().equals(nombre)){
                    alumnosList.get(j).addNotas(nota);
                    alumno = alumnosList.get(j);
                    estAlumno = true;
                    break;
                }
            }
            if(!estAlumno){
                List<Integer> notas = new ArrayList<>();
                notas.add(nota);
                alumno = new Alumno(nombre, notas);
                alumnosList.add(alumno);
            }
            if(alumno != null){
                int total = (nota == 1)? alumno.sacarSuspensos()
                        :(nota==2)? alumno.sacarAprobados():
                        alumno.sacarSobresalientes();
                // TODO vb.tvNotas.setText(R.plurals.calificaciones(total,calificacion));
                // TODO MenuAdapter adapter = new MenuAdapter(R.menu.menu);
            }

        });
    }

    /* TODO
    public void menuClick(){
    }
    */
}