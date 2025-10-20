package com.example.parcialjuego

import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ContentView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.parcialjuego.viewmodel.GameViewModel


class GameFragment : Fragment(R.layout.fragment_game) {

    private var colorName: String = ""
    private var score: Int = 0
    private lateinit var colorsList: List<Pair<String, Int>>

    private lateinit var contenedorColor: View
    private lateinit var valorTemporizador: TextView
    private  lateinit var puntaje: TextView
    private  lateinit var botones: List<Button>
    private val viewModel: GameViewModel by activityViewModels()


    // MediaPlayer para sonidos de error y correcto
    private lateinit var sonidoCorrecto: MediaPlayer
    private lateinit var sonidoIncorrecto: MediaPlayer

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //atributos del xml
        valorTemporizador = view.findViewById<TextView>(R.id.txt_temporizador_valor)
        contenedorColor = view.findViewById<View>(R.id.cntn_color)
        puntaje = view.findViewById<TextView>(R.id.txt_puntaje)
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

        botones = listOf(btnRed, btnBlack, btnBlue, btnYellow, btnGreen, btnOrange)//guardamos los botones en una lista

        // nicializamos los sonidos
        sonidoCorrecto = MediaPlayer.create(requireContext(), R.raw.correcto)
        sonidoIncorrecto = MediaPlayer.create(requireContext(), R.raw.incorrecto)

        // Configurar eventos de clic
        btnRed.setOnClickListener { verificarRespuesta("ROJO") }
        btnBlack.setOnClickListener { verificarRespuesta("NEGRO") }
        btnBlue.setOnClickListener { verificarRespuesta("AZUL") }
        btnYellow.setOnClickListener { verificarRespuesta("AMARILLO") }
        btnGreen.setOnClickListener { verificarRespuesta("VERDE") }
        btnOrange.setOnClickListener { verificarRespuesta("NARANJA") }

        // Iniciar el juego
        iniciarJuego(valorTemporizador)

    }
        //guncion para iniciar el juego
    fun iniciarJuego(valorTemp: TextView){
        valorTemp.text = "30"//30 segundos
        actualizarColorAleatorio()
        object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                valorTemporizador.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                finalizarJuego()//si acaban los 30 seg llamamos a la funcion
            }
        }.start()
    }

    fun actualizarColorAleatorio() {
        val (nombre, valorColor) = colorsList.random()//usamos desestructuración, con esto obtenemos el nombre del color y el valor
        colorName = nombre
        contenedorColor.setBackgroundColor(valorColor)//coloreamos el view al color del random

    }

    fun verificarRespuesta(colorSelect/*este valor lo obtenemos del boton*/: String){
        if (colorSelect == colorName){//se compara si el color es el correcto
            actualizarPuntaje()//si es correcto el color se actualiza el puntaje
            sonidoCorrecto.start()//lanzamos el sonido "correcto"
        }else {sonidoIncorrecto.start()}//en caso de no ser correcta la respuesta solo lsanzamos el sonido erroneo

        actualizarColorAleatorio()//se actualiza el color
    }

    fun actualizarPuntaje(){//se aumenta el puntaje
        score ++
        puntaje.text = score.toString()
    }

    fun finalizarJuego(){//
        botones.forEach { it.isEnabled = false }//deshabilitamos los botones, para que el usuario ya no sume puntos
        Toast.makeText(requireContext(), "Tiempo terminado", Toast.LENGTH_LONG).show()
        viewModel.agregarPuntaje(score)//llamamos al metodo agregar puntaje del view model para guardar

        findNavController().navigate(//cambiamos de fragmento
            R.id.action_gameFragment_to_resultFragment,//colocamos la direccion que da el nav graph
            bundleOf("puntajeFinal" to score)//pasamos el puntaje con la clave "puntajeFinal"
        )
    }

}