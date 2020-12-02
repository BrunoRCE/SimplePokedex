package com.brunocamacho.simplepokedex.ui;

import com.brunocamacho.simplepokedex.domain.entity.Pokemon;

import java.util.List;

public interface MainView {

    void setPokemons(List<Pokemon> pokemons);

    void showProgress(boolean show);

    void showError(String error);
}
