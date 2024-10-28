package com.pokemonreview.api.service;

import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.dto.PokemonResponse;

public interface PokemonService {
    PokemonResponse getAllPokemon(int pageNo, int pageSize);
    PokemonDto createPokemon(PokemonDto pokemonDto);
    PokemonDto getPokemonById(int id);
    PokemonDto updatePokemon(PokemonDto pokemonDto , int id);

    void deletePokemon(int id);
}
