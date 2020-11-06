package com.brunocamacho.simplepokedex.interactor;

import com.brunocamacho.simplepokedex.data.PokeService;
import com.brunocamacho.simplepokedex.data.model.PokeData;
import com.brunocamacho.simplepokedex.data.model.Result;
import com.brunocamacho.simplepokedex.model.Pokemon;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetPokemons {

    private PokeService service;

    public GetPokemons() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(PokeService.class);
    }

    public Observable<List<Pokemon>> execute() {
        return Observable.create(emitter -> {

            Call<PokeData> repos = service.getData(103);
            repos.enqueue(new Callback<PokeData>() {

                @Override
                public void onResponse(Call<PokeData> call, Response<PokeData> response) {

                    List<Pokemon> pokemons = new ArrayList<>();
                    int i = 1;

                    for (Result result : response.body().getResults()) {
                        Pokemon pokemon = new Pokemon();
                        pokemon.setName(result.getName());
                        pokemon.setImageUrl("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + i + ".png");
                        pokemons.add(pokemon);
                        i++;
                    }

                    emitter.onNext(pokemons);
                    emitter.onComplete();
                }

                @Override
                public void onFailure(Call<PokeData> call, Throwable t) {
                    emitter.onError(t);
                }
            });
        });
    }
}
