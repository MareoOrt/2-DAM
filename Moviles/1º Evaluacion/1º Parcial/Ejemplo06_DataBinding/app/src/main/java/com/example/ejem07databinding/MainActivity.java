package com.example.ejem07databinding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;


import com.example.ejem07databinding.databinding.ActivityMainBinding;
import com.example.ejem07databinding.databinding.ActivityMainBindingImpl;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    EditText etnombre, etpassword, etedad;
    Button bguardar;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main); //No nos va a servir con binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot()); //Asignamos la vista de binding permite
        // acceder a los componentes de la vista mas rapidamente
        Usuario usuario = new Usuario("Lucia",25,"333");
        //asignarDatosVista(usuario);
        //CON BINDING
        binding.setPersona(usuario);
        //recuperarComponentesInterfaz();

        asignarEventos();
    }

    private void asignarDatosVista(Usuario usuario) {
        binding.etNombre.setText(usuario.getNombre());
        binding.etEdad.setText(String.valueOf(usuario.getEdad()));
        binding.etPassword.setText(usuario.getPassword());
    }

    private void asignarEventos() {
        binding.bGuardar.setOnClickListener(view -> {
            String nombre= binding.etNombre.getText().toString();
            int edad= Integer.parseInt(binding.etEdad.getText().toString());
            String password= binding.etPassword.getText().toString();
            /*String nombre=etnombre.getText().toString();
            int edad = Integer.parseInt(et_edad.getText().toString());
            String password = etpassword.getText().toString();*/
            //Usuario usuario= new Usuario(nombre, edad, password);
            Usuario usuario= new Usuario("", 0, "");
            binding.setPersona(usuario);
            Snackbar.make(view,usuario.toString(),Snackbar.LENGTH_LONG).show();
        });
    }

    private void recuperarComponentesInterfaz() {
        etnombre = (EditText) findViewById(R.id.et_nombre);
        etpassword = (EditText) findViewById(R.id.et_password);
        etedad = (EditText) findViewById(R.id.et_edad);
        bguardar= (Button) findViewById(R.id.b_guardar);

    }
}