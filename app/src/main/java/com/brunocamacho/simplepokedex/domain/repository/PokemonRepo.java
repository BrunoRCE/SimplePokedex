package com.brunocamacho.simplepokedex.domain.repository;

import com.brunocamacho.simplepokedex.domain.entity.Pokemon;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface PokemonRepo {

    Observable<List<Pokemon>> findAllPokemon();
}
