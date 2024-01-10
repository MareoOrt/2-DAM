package com.example.fastejlistfragment.placeholder

import com.example.fastejlistfragment.Usuario
import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object PlaceholderContent {

    val usuarios: ArrayList<Usuario> = arrayListOf(
        Usuario("Juan", 25, "+34 612345678"),
        Usuario("María", 30, "+34 655432109"),
        Usuario("Carlos", 22, "+34 600112233"),
        Usuario("Ana", 28, "+34 677888999"),
        Usuario("Pedro", 35, "+34 611223344"),
        Usuario("Laura", 27, "+34 633444555"),
        Usuario("Miguel", 32, "+34 688777888"),
        Usuario("Isabel", 26, "+34 622333444"),
        Usuario("Javier", 29, "+34 666555444"),
        Usuario("Sofía", 31, "+34 633222111"),
        Usuario("Diego", 24, "+34 688777666"),
        Usuario("Elena", 33, "+34 611333555"),
        Usuario("Hugo", 28, "+34 644666888"),
        Usuario("Carmen", 36, "+34 655444333"),
        Usuario("Ricardo", 23, "+34 699777555"),
        Usuario("Patricia", 34, "+34 666333111"),
        Usuario("Alejandro", 27, "+34 622555888"),
        Usuario("Silvia", 30, "+34 677333999"),
        Usuario("Fernando", 25, "+34 611444666"),
        Usuario("Luisa", 29, "+34 688222555")
    )


    /**
     * An array of sample (placeholder) items.
     */
    val ITEMS: MutableList<PlaceholderItem> = ArrayList()

    /**
     * A map of sample (placeholder) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, PlaceholderItem> = HashMap()

    private val COUNT = usuarios.size

    init {
        // Add some sample items.
        for (i in 1..COUNT) {
            addItem(createPlaceholderItem(i))
        }
    }

    private fun addItem(item: PlaceholderItem) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id, item)
    }

    private fun createPlaceholderItem(position: Int): PlaceholderItem {
        return PlaceholderItem(
            position.toString(), usuarios.get(position).nombre.toString(),
            makeDetails(position)
        )
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0..position - 1) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }

    /**
     * A placeholder item representing a piece of content.
     */
    data class PlaceholderItem(val id: String, val content: String, val details: String) {
        override fun toString(): String = content
    }
}