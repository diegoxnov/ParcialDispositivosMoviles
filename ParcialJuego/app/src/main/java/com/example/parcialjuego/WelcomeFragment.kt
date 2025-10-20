package com.example.parcialjuego

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController


class WelcomeFragment : Fragment(R.layout.fragment_welcome) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Referencias a los botones del layout
        val btnMostrarReglas = view.findViewById<Button>(R.id.btnMostrarReglas)
        val btnIniciarJuego = view.findViewById<Button>(R.id.btnIniciarJuego)

        // Botón para mostrar reglas
        btnMostrarReglas.setOnClickListener {
            mostrarReglas()
        }

        // Botón para ir  al juego
        btnIniciarJuego.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_gameFragment)
        }
    }

    private fun mostrarReglas() {//esta funcion contiene la info del alert
        AlertDialog.Builder(requireContext())
            .setTitle("Reglas del juego")
            .setMessage(
                """
                - Selecciona el boton con el color del recuadro no te confundas!
                - 1 acierto = 1 punto
                - El sonido te indica si es error o correcto
                - ¡Supera tu récord personal!
                """.trimIndent()
            )
            .setPositiveButton("Entendido") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}