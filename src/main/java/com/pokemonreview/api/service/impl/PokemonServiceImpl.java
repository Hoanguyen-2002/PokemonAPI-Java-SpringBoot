package com.pokemonreview.api.service.impl;

import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.models.Pokemon;
import com.pokemonreview.api.repository.PokemonRepository;
import com.pokemonreview.api.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService {
    // not recommend add autowired at field because of Unit Test
    private PokemonRepository pokemonRepository;

    @Autowired //Constructor injection
    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public List<PokemonDto> getAllPokemon() {
        List<Pokemon> pokemon = pokemonRepository.findAll();
        //map because it returns a list
        return pokemon.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
    }


    @Override
    public PokemonDto createPokemon(PokemonDto pokemonDto) {
        //Save new pokemonDto created to DB
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());

        Pokemon newPokemon = pokemonRepository.save(pokemon);

        //Response body for new pokemonDto created
        PokemonDto pokemonResponse = new PokemonDto();

        pokemonResponse.setId(pokemon.getId());
        pokemonResponse.setName(newPokemon.getName());
        pokemonResponse.setType(newPokemon.getType());
        return pokemonResponse;
    }


    //Using Mapper Utils is not common in real world project !
    //mapping Entity->DTO
    private PokemonDto mapToDto(Pokemon pokemon) {
        PokemonDto pokemonDto = new PokemonDto();
        pokemonDto.setId(pokemon.getId());
        pokemonDto.setName(pokemon.getName());
        pokemonDto.setType(pokemon.getType());
        return pokemonDto;
    }

    //mapping DTO->Entity
    private Pokemon mapToEntity(PokemonDto pokemonDto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());
        return pokemon;
    }
}
