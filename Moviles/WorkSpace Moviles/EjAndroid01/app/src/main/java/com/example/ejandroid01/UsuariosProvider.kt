package com.example.controlgastos
class UsuariosProvider {

    data class Users( var nombre:String, var contrasena : String)

    companion object {
        val usuarios: ArrayList<Users> = arrayListOf(
            Users("juan", "hola_mundo"),
            Users("pedro", "hola_mundo2"),
            Users("maria", "hola_mundo3")
        )
    }
}