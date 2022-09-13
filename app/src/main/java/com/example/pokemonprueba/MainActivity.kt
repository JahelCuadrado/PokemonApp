package com.example.pokemonprueba

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonprueba.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), MainAux {


    private lateinit var binding : ActivityMainBinding
    private var pokemons = mutableListOf<Pokemon?>()
    private lateinit var pokemonAdapter: PokemonAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playAnimation()
        initRecycler()
        obtenerPokemons()
    }


    private fun playAnimation(){
        with(binding){
            lottie.setAnimation(R.raw.pokeball_animation)
            lottie.playAnimation()
            lottie.loop(true)
        }
    }


    private fun listener(pokemon: Pokemon?){
        val args = Bundle()
        args.putString("pokemonNombre", pokemon?.name)
        args.putString("pokemonImagen", pokemon?.sprites?.frontDefault)

        val fragment = FragmentDetalle()
        fragment.arguments = args

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.pantalla_principal, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

        ocultarRecycler(true)
    }


    private fun initRecycler(){
        pokemonAdapter = PokemonAdapter(pokemons, ::listener)
        linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerpokemon.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = pokemonAdapter
        }
    }


    private fun obtenerPokemons() {
        for (i in 1..30)  {
            searchByName(i)
        }

    }


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    override fun ocultarRecycler(hideRecycler : Boolean){
        if (hideRecycler) binding.recyclerpokemon.visibility = View.INVISIBLE
        else binding.recyclerpokemon.visibility = View.VISIBLE
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun searchByName(query:Int){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(PokemonService::class.java).getPokemon("pokemon/$query")
            val pokemonsResp = call.body()
            runOnUiThread {
                if(call.isSuccessful) {
                    pokemons.add(pokemonsResp)
                    pokemonAdapter.notifyDataSetChanged()
                }else{
                    Toast.makeText(this@MainActivity, "No encuentro eso", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}