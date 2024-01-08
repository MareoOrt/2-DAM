package com.example.ejem01componentesbasicos;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class Usuario {
    private String nombre;
    private String comunidadAutonoma;
    private String ciudadNacimiento;
    private String profesion;
    private boolean casado;
    private ArrayList<String> deportes;

    public Usuario() {

    }

    //TODO: funcion para buscar un deporte en la lista de deportes del usuario
    public Usuario(String nombre, String comunidadAutonoma, String ciudadNacimiento, String profesion, boolean casado, ArrayList<String> deportes) {
        this.nombre = nombre;
        this.comunidadAutonoma = comunidadAutonoma;
        this.ciudadNacimiento = ciudadNacimiento;
        this.profesion = profesion;
        this.casado = casado;
        this.deportes = deportes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getComunidadAutonoma() {
        return comunidadAutonoma;
    }

    public void setComunidadAutonoma(String comunidadAutonoma) {
        this.comunidadAutonoma = comunidadAutonoma;
    }

    public String getCiudadNacimiento() {
        return ciudadNacimiento;
    }

    public void setCiudadNacimiento(String ciudadNacimiento) {
        this.ciudadNacimiento = ciudadNacimiento;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public boolean isCasado() {
        return casado;
    }

    public void setCasado(boolean casado) {
        this.casado = casado;
    }

    public ArrayList<String> getDeportes() {
        return deportes;
    }

    public void setDeportes(ArrayList<String> deportes) {
        this.deportes = deportes;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + "\n" +
                "Comunidad Autonoma " + comunidadAutonoma + "\n" +
                "Ciudad de nacimiento " + ciudadNacimiento + "\n" +
                "Profesion: " + profesion + "\n" +
                "Casado: " + casado + "\n" +
                "Deportes: " + deportes;
    }
}
