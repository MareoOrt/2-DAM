package com.example.ortunezsanzmario;

import androidx.annotation.NonNull;

public class User {

    private String name;
    private boolean sex;

    public User(String name, boolean sex) {
        this.name = name;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getInfo() {
        String s = (sex) ? "Mujer" : "Hombre";
        return "Nombre: " + this.name + ", Sexo: " + s;
    }

    @NonNull
    @Override
    public String toString() {
        return this.getName();
    }
}
