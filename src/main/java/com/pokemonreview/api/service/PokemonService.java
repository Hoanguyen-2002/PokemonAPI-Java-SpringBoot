package com.pokemonreview.api.service;

import com.pokemonreview.api.dto.PokemonDto;

import java.util.List;

public interface PokemonService {
    List<PokemonDto> getAllPokemon();
    PokemonDto createPokemon(PokemonDto pokemonDto);
}
