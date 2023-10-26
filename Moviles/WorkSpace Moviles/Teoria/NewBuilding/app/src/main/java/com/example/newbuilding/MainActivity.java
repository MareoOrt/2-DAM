package com.example.newbuilding;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newbuilding.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    /*
    // Comoonentes
    private EditText etNombre;
    private EditText enEdad;
    private EditText epContrasena;
    private Button btGuardar;
     */
    Usuario usuario;
    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        //recuperarComponentesInterfaz();
        asignarEventos();

        // setContentView(R.layout.activity_main);
        setContentView(binding.getRoot());

    }

    /*
    //Metodo para recuperar componentes de la interfaz
    private void recuperarComponentesInterfaz(){
        etNombre = (EditText) findViewById(R.id.id_Nombre_EditText);
        enEdad = (EditText) findViewById(R.id.id_Edad_EditNumber);
        epContrasena = (EditText) findViewById(R.id.id_Contrasena_EditPassword);
        btGuardar = (Button) findViewById(R.id.id_Guardar_Button);

    }
    */

    private void asignarEventos() {
        binding.idGuardarButton.setOnClickListener(view -> {
            String nombre = binding.idNombreEditText.getText().toString();
            int edad = Integer.getInteger(binding.idEdadEditNumber.getText().toString());
            String contrasena = binding.idContrasenaEditPassword.getText().toString();

            binding.setPersona(new Usuario(nombre,edad,contrasena));

            /*
            asignarDatos(new Usuario(nombre, edad, contrasena));

            String nombre = etNombre.getText().toString();
            int edad = Integer.getInteger(enEdad.getText().toString());
            String contrasena = epContrasena.getText().toString();
            */
            Snackbar.make(binding.getRoot(), "Datos guardados", Snackbar.LENGTH_SHORT).show();
        });
    }

/*    private void asignarDatos(Usuario usuario) {
        binding.idNombreEditText.setText(usuario.getNombre());
        binding.idEdadEditNumber.setText(usuario.getEdad());
        binding.idContrasenaEditPassword.setText(usuario.getContrsena());
    }*/

}