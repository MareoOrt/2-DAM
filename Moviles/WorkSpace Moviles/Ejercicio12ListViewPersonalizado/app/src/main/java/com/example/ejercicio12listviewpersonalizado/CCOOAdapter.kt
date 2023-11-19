package com.example.ejercicio12listviewpersonalizado

import android.database.DataSetObserver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class CCOOAdapter (val datos: ArrayList<CCOO>) : RecyclerView.Adapter<CCOOAdapter.PersonasViewHolder>(),
    ListAdapter {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonasViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        return PersonasViewHolder(inflater.inflate(R.layout.view_castilla_y_leon, parent, false))
    }


    override fun getItemCount():Int = datos.size
    /*override fun getItemCount(): Int {
        return PersonasProvider.datos.size
    }*/

    override fun onBindViewHolder(holder: CCOOAdapter.PersonasViewHolder, position: Int) {
        val ccoo: CCOO = datos[position]
        holder.bind(ccoo)
    }

    class PersonasViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = CCOOAdapter.bind(itemView)

        fun bind(ccoo : CCOO) {
            binding.ccoo = ccoo
        }
    }

    override fun registerDataSetObserver(p0: DataSetObserver?) {
        TODO("Not yet implemented")
    }

    override fun unregisterDataSetObserver(p0: DataSetObserver?) {
        TODO("Not yet implemented")
    }

    override fun getCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getItem(p0: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        TODO("Not yet implemented")
    }

    override fun getViewTypeCount(): Int {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun areAllItemsEnabled(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isEnabled(p0: Int): Boolean {
        TODO("Not yet implemented")
    }

}