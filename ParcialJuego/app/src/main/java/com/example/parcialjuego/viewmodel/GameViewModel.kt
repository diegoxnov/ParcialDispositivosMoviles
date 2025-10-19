package com.example.parcialjuego.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    val puntajes = MutableLiveData<MutableList<Int>>(mutableListOf())

    fun agregarPuntaje(puntaje: Int) {
        puntajes.value?.add(puntaje)
        puntajes.value = puntajes.value // fuerza actualización
    }
}