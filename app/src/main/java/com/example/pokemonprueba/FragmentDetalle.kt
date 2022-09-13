package com.example.pokemonprueba

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.pokemonprueba.databinding.FragmentDetalleBinding
import java.util.*


class FragmentDetalle : Fragment() {

    private lateinit var binding : FragmentDetalleBinding
    private var mainActivity : MainActivity? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetalleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity = activity as MainActivity

        val imagen = arguments?.getString("pokemonImagen", "")
        val nombre = arguments?.getString("pokemonNombre", "")

        binding.nombrePok.text = nombre
        binding.nombrePok.text.toString().uppercase(Locale.getDefault())
        Glide.with(this)
            .load(imagen)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(binding.imagenPok)

        binding.ivBack.setOnClickListener {
            volver()
        }
    }

    private fun volver() {
        activity?.onBackPressed()
        mainActivity?.ocultarRecycler(false)
    }


}