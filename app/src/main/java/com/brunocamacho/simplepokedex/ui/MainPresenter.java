package com.brunocamacho.simplepokedex.ui;

import com.brunocamacho.simplepokedex.interactor.GetPokemons;
import com.brunocamacho.simplepokedex.model.Pokemon;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainPresenter {

    private MainView view;
    private GetPokemons getPokemons;

    public MainPresenter(MainView view, GetPokemons getPokemons) {
        this.view = view;
        this.getPokemons = getPokemons;
    }

    public void onStart() {
        getPokemons.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Pokemon>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        view.showProgress(true);
                    }

                    @Override
                    public void onNext(@NonNull List<Pokemon> pokemons) {
                        view.setPokemons(pokemons);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.showError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        view.showProgress(false);
                    }
                });
    }
}
