package com.example.parcialjuego.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.parcialjuego.R


class PuntajeAdapter(private val puntajes: List<Int>) :
    RecyclerView.Adapter<PuntajeAdapter.PuntajeViewHolder>() {

    inner class PuntajeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtPuntajeItem: TextView = itemView.findViewById(R.id.txt_item_puntaje)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PuntajeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_puntaje, parent, false)
        return PuntajeViewHolder(view)
    }

    override fun onBindViewHolder(holder: PuntajeViewHolder, position: Int) {
        holder.txtPuntajeItem.text = "Puntaje ${position + 1}: ${puntajes[position]}"
        //aplicamos animacion al item
        val anim = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.anim_item_historial)
        holder.itemView.startAnimation(anim)

    }

    override fun getItemCount(): Int = puntajes.size
}