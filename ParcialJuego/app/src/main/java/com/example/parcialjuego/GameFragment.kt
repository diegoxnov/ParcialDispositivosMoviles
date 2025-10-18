package com.example.parcialjuego

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat


class GameFragment : Fragment(R.layout.fragment_game) {

    private var colorName: String = ""
    private var score: Int = 0
    private lateinit var colorsList: List<Pair<String, Int>>
    private val random = java.util.Random()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //atributos del xml
        val valorTemporizador = view.findViewById<TextView>(R.id.txt_temporizador_valor)
        val contenedorColor = view.findViewById<View>(R.id.cntn_color)
        val puntaje = view.findViewById<TextView>(R.id.txt_puntaje)
        val btnRed = view.findViewById<Button>(R.id.btn_red)
        val btnBlack = view.findViewById<Button>(R.id.btn_black)
        val btnBlue = view.findViewById<Button>(R.id.btn_blue)
        val btnYellow = view.findViewById<Button>(R.id.btn_yellow)
        val btnGreen = view.findViewById<Button>(R.id.btn_green)
        val btnOrange = view.findViewById<Button>(R.id.btn_orange)
        // Lista de colores, estos se estan capturando de colors.xml
        colorsList = listOf(
            "ROJO" to ContextCompat.getColor(requireContext(), R.color.red2),
            "AZUL" to ContextCompat.getColor(requireContext(), R.color.blue),
            "VERDE" to ContextCompat.getColor(requireContext(), R.color.green),
            "AMARILLO" to ContextCompat.getColor(requireContext(), R.color.yellow),
            "NARANJA" to ContextCompat.getColor(requireContext(), R.color.orange),
            "NEGRO" to ContextCompat.getColor(requireContext(), R.color.black)
        )



    }

    fun IniciarJuego(valorTemp: TextView){
        valorTemp.text = "30"

    }

    fun ActualizarColorAleatorio(contentColor: View, colorList: List<Pair<String, Int>>) {
        val (colorName, valorColor) = colorList.random()//usamos desestructuración, con esto obtenemos el nombre del color y el valor
        contentColor.setBackgroundColor(valorColor)

    }

    fun VerificarRespuesta(colorSelect: String,contentColor: View,colorList: List<Pair<String, Int>>){
        if (colorSelect == colorName){
            score = score + 1
            ActualizarColorAleatorio(contentColor,colorList)
        }else ActualizarColorAleatorio(contentColor,colorList)
    }

}