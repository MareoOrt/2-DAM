package com.example.ejercicio_peliculas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicio_peliculas.databinding.CardviewPeliculaBinding
import java.util.ArrayList

class MovieAdapter(val datos: ArrayList<Movie>) : RecyclerView.Adapter<MovieAdapter.PersonasViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonasViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        return PersonasViewHolder(inflater.inflate(R.layout.cardview_pelicula, parent, false))
    }


    override fun getItemCount():Int = datos.size
    /*override fun getItemCount(): Int {
        return PersonasProvider.datos.size
    }*/

    override fun onBindViewHolder(holder: MovieAdapter.PersonasViewHolder, position: Int) {
        val persona: Movie = datos[position]
        holder.bind(persona)
    }

    class PersonasViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = CardviewPeliculaBinding.bind(itemView)

        fun bind(persona : Movie) {
            binding.pelicula = persona
        }
    }

}