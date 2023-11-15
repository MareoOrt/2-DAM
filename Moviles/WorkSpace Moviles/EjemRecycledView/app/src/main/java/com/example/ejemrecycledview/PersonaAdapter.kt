package com.example.ejemrecycledview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.ejemrecycledview.databinding.ViewPersonaBinding
import java.util.ArrayList

class PersonaAdapter(val datos: ArrayList<Persona>) : RecyclerView.Adapter<PersonaAdapter.PersonasViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonasViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        return PersonasViewHolder(inflater.inflate( R.layout.view_persona, parent, false))
    }

    override fun getItemCount(): Int = datos.size

    override fun onBindViewHolder(holder: PersonasViewHolder, position: Int) {
        val persona: Persona = datos[position]
    }

    class PersonasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ViewPersonaBinding.bind(itemView)

        fun bind(persona: Persona) {
            binding.persona = persona
        }
    }

}
