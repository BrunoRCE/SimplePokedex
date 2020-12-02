package com.brunocamacho.simplepokedex.domain.useCase;

import com.brunocamacho.simplepokedex.domain.entity.Pokemon;
import com.brunocamacho.simplepokedex.domain.repository.PokemonRepo;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class GetPokemons {

    private PokemonRepo pokemonRepo;

    public GetPokemons(PokemonRepo pokemonRepo) {
        this.pokemonRepo = pokemonRepo;
    }

    public Observable<List<Pokemon>> execute(){
        return pokemonRepo.findAllPokemon();
    }
}
