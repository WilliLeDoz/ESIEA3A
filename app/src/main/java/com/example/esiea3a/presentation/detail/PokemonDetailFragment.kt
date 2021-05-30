package com.example.esiea3a.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.esiea3a.R
import com.example.esiea3a.presentation.Singletons
import com.example.esiea3a.presentation.api.PokemonDetailResponse
import com.example.esiea3a.presentation.api.PokemonListResponse
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback as Callback

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class PokemonDetailFragment : Fragment() {

    private lateinit var textViewName: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pokemon_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textViewName = view.findViewById(R.id.pokemon_detail_name)
        callApi()
    }


    private fun callApi() {
        Singletons.pokeApi.getPokemonDetail("1")
            .enqueue(object : retrofit2.Callback<PokemonDetailResponse> {
                override fun onFailure(call: Call<PokemonDetailResponse>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<PokemonDetailResponse>,
                    response: Response<PokemonDetailResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                       textViewName.text =response.body()!!.name
                    }
                }
            })

    }
}