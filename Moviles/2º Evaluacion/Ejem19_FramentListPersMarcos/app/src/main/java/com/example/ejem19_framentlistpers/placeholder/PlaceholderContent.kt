package com.example.ejem19_framentlistpers.placeholder

import com.example.ejem19_framentlistpers.Usuario
import com.example.ejem19_framentlistpers.UsuariosProvider
import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object PlaceholderContent {

    /**
     * An array of sample (placeholder) items.
     */
    val ITEMS: MutableList<PlaceholderItem> = ArrayList()

    /**
     * A map of sample (placeholder) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, PlaceholderItem> = HashMap()

    private val listaUsuarios = UsuariosProvider.usuarios

    init {
        // Add some sample items.
        for (usuario in listaUsuarios) {
            addItem(createPlaceholderItem(usuario))
        }
    }

    private fun addItem(item: PlaceholderItem) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id, item)
    }

    private fun createPlaceholderItem(usuario: Usuario): PlaceholderItem {
        return PlaceholderItem(usuario.nombre, "Telefono " + usuario.telefono + "\tEdad:" + usuario.edad, makeDetails(usuario))
    }

    private fun makeDetails(usuario: Usuario): String {
        val builder = StringBuilder()
        builder.append("detalles del usuario: \n").append(usuario.toString())
        return builder.toString()
    }

    /**
     * A placeholder item representing a piece of content.
     */
    data class PlaceholderItem(val id: String, val content: String, val details: String) {
        override fun toString(): String = content
    }
}