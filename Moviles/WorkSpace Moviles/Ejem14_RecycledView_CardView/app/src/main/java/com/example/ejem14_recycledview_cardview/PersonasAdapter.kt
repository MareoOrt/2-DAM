package com.example.recicledviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ejem14_recycledview_cardview.databinding.ViewPersonaBinding
import java.util.ArrayList

class PersonasAdapter(val datos: ArrayList<Persona>) : RecyclerView.Adapter<PersonasAdapter.PersonasViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonasViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        return PersonasViewHolder(inflater.inflate(R.layout.view_persona, parent, false))
    }


    override fun getItemCount():Int = datos.size
    /*override fun getItemCount(): Int {
        return PersonasProvider.datos.size
    }*/

    override fun onBindViewHolder(holder: PersonasAdapter.PersonasViewHolder, position: Int) {
        val persona: Persona = datos[position]
        holder.bind(persona)
    }

    class PersonasViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = ViewPersonaBinding.bind(itemView)

        fun bind(persona : Persona) {
            binding.persona = persona
        }
    }

}

