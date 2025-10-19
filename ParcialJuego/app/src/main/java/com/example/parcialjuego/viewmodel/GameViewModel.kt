package com.example.parcialjuego.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel(application: Application) : AndroidViewModel(application) {

    val puntajes = MutableLiveData<MutableList<Int>>(mutableListOf())

    private val prefs = application.getSharedPreferences("HistorialPuntajes", Context.MODE_PRIVATE)

    init {
        // Cargar los puntajes guardados al iniciar el ViewModel
        val listaGuardada = prefs.getString("lista", "")?.split(",")?.mapNotNull {
            it.toIntOrNull()
        } ?: emptyList()
        puntajes.value = listaGuardada.toMutableList()
    }

    fun agregarPuntaje(puntaje: Int) {
        val lista = puntajes.value ?: mutableListOf()
        lista.add(puntaje)

        // Guardar lista como cadena separada por comas
        prefs.edit().putString("lista", lista.joinToString(",")).apply()

        // Actualizar lista
        puntajes.value = lista
    }
}