package com.example.ejemcomunicacionsockets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;

import com.example.ejemcomunicacionsockets.databinding.ActivityMainBinding;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private OutputStream os = null;
    private ObjectOutputStream oos = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        binding.btConectar.setOnClickListener(view -> {
            try{
                Socket socket = new Socket("localhost", 4444);
                os = socket.getOutputStream();
                oos = new ObjectOutputStream(os);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            binding.btEnviar.setEnabled(true);
        });

        binding.btEnviar.setOnClickListener(view -> {
            try {
                String msj = binding.etMensaje.getText().toString();
                oos.writeObject(msj);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }
}