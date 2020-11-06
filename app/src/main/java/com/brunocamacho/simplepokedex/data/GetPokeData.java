package com.brunocamacho.simplepokedex.data;

import com.brunocamacho.simplepokedex.data.model.PokeData;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetPokeData {

    private PokeService service;

    public GetPokeData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(PokeService.class);
    }

    public Observable<PokeData> getPokeData() {
        return Observable.create(emitter -> {

            Call<PokeData> repos = service.getData(5);
            repos.enqueue(new Callback<PokeData>() {

                @Override
                public void onResponse(Call<PokeData> call, Response<PokeData> response) {
                    emitter.onNext(response.body());
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
