package com.example.pokemonprueba

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.pokemonprueba.databinding.ItemPokemonBinding

class PokemonAdapter(
    private var pokemons : MutableList<Pokemon?>,
    private val listener : (Pokemon?) -> Unit


): RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = pokemons[position]
        holder.bind(pokemon)

        holder.itemView.setOnClickListener {
            listener(pokemon)
        }

    }


    override fun getItemCount(): Int {
        return pokemons.size
    }


    class ViewHolder(private val binding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) { //vinculamos la vista al adaptador

            fun bind(pokemon: Pokemon?){
                with(binding){
                    tvPokemon.text = pokemon?.name
                    Glide.with(tvPokemon.context)
                        .load(pokemon?.sprites?.frontDefault)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop()
                        .centerInside()
                        .into(imgPokemon)
                }
            }
    }


}