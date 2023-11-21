package com.example.ejercicio12listviewpersonalizado

class CCOOProvider {

    companion object {
        var datos:ArrayList<CCOO> = arrayListOf<CCOO>(
            CCOO("Castilla y León",
                listOf("Ávila", "Burgos", "León", "Palencia", "Salamanca", "Segovia", "Soria", "Valladolid", "Zamora")),
            CCOO("Galicia",
                listOf("Lugo", "Pontevedra", "Orense" ,"La Coruña")),
            CCOO("asdf",
                listOf("cvsedfvg", "cwfe", "cwerdvf"))
        )
    }
}