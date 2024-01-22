package com.example.controlgastos

import java.util.Date

class GastosProvider {

    data class Gastos(var categoria: String, var concepto: String, var fecha: Date, var precio: Int, var userName:String)

    companion object {
        val gastos: ArrayList<Gastos> = arrayListOf(
            Gastos("Comida", "Pizza", Date(), 100, "juan"),
            Gastos("Comida", "Hamburguesa", Date(), 10, "juan"),
            Gastos("Comida", "Tacos", Date(), 30, "juan"),
            Gastos("Estudios", "Libro", Date(), 200, "juan"),
            Gastos("Ocio", "Cine", Date(), 7, "juan"),
            Gastos("Ocio", "Cine", Date(), 7, "pedro"),
            Gastos("Comida", "Pizza", Date(), 100, "pedro"),
            Gastos("Comida", "Hamburguesa", Date(), 20, "pedro")
        )
    }
}