package com.example.serrudo_michael;

public class Persona {
    private String nombre;
    private String calificacion;

    public Persona(String nombre, String calificacion) {
        this.nombre = nombre;
        this.calificacion= calificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    @Override
    public String toString() {
        return nombre + calificacion;
    }
}
