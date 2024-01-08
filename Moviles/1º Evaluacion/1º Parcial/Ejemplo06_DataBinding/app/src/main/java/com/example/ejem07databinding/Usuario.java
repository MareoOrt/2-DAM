package com.example.ejem07databinding;

public class Usuario {
    private String nombre;
    private int edad;
    private String password;

    public Usuario(String nombre, int edad, String password) {
        this.nombre = nombre;
        this.edad = edad;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return " Nombre:" + nombre  + " Edad" + edad + " Password" + password;
    }
}
