package com.example.ortunezmario;

import java.util.List;

public class Alumno {

    private String nombre;

    private List<Integer> listNotas;

    public Alumno(String nombre, List<Integer> listNotas) {
        this.nombre = nombre;
        this.listNotas = listNotas;
    }

    public String getNombre() {
        return nombre;
    }

    public void addNotas(int nota) {
        listNotas.add(nota);
    }

    public int sacarSuspensos(){
        int nSus = 0;
        for (double nota: listNotas) {
            if(nota == 1){
                nSus ++;
            }
        }
        return nSus;
    }

    public int sacarAprobados(){
        int nApro = 0;
        for (double nota: listNotas) {
            if(nota == 2){
                nApro ++;
            }
        }
        return nApro;
    }

    public int sacarSobresalientes(){
        int nSob = 0;
        for (double nota: listNotas) {
            if(nota == 4){
                nSob ++;
            }
        }
        return nSob;
    }

}
