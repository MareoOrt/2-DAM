package com.example.newbuilding;

public class Usuario {

    private String nombre;

    private int edad;

    private String contrsena;

    public Usuario(String nombre, int edad, String contrsena) {
        this.nombre = nombre;
        this.edad = edad;
        this.contrsena = contrsena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getContrsena() {
        return contrsena;
    }

    public void setContrsena(String contrsena) {
        this.contrsena = contrsena;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", contrsena='" + contrsena + '\'' +
                '}';
    }
}
