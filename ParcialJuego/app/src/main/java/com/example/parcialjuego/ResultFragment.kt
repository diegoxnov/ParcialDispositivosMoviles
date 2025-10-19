package com.example.parcialjuego

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parcialjuego.adapter.PuntajeAdapter
import com.example.parcialjuego.viewmodel.GameViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ResultFragment : Fragment(R.layout.fragment_result) {

    private lateinit var txtPuntajeFinal: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnReintentar: FloatingActionButton
    private lateinit var adapter: PuntajeAdapter

    private val viewModel: GameViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txtPuntajeFinal = view.findViewById(R.id.txt_puntaje_final)
        recyclerView = view.findViewById(R.id.recycler_puntajes)
        btnReintentar = view.findViewById(R.id.btn_seguir_jugando)

        val puntajeFinal = arguments?.getInt("puntajeFinal") ?: 0
        txtPuntajeFinal.text = puntajeFinal.toString()

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.puntajes.observe(viewLifecycleOwner) { lista ->
            val listaOrdenada = lista.sortedDescending() // mayor a menor
            adapter = PuntajeAdapter(listaOrdenada)
            recyclerView.adapter = adapter
        }

        btnReintentar.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_gameFragment)
        }

    }
}
