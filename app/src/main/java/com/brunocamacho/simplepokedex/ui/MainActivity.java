package com.brunocamacho.simplepokedex.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import com.brunocamacho.simplepokedex.data.ApiData;
import com.brunocamacho.simplepokedex.databinding.ActivityMainBinding;
import com.brunocamacho.simplepokedex.domain.useCase.GetPokemons;
import com.brunocamacho.simplepokedex.domain.entity.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {

    private ActivityMainBinding binding;
    private MainAdapter adapter;
    private List<Pokemon> pokemons;

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        presenter = new MainPresenter(this,
                new GetPokemons(new ApiData()));

        pokemons = new ArrayList<>();

        adapter = new MainAdapter(pokemons);

        binding.recycler.setAdapter(adapter);
        binding.recycler.setHasFixedSize(true);
        binding.recycler.setLayoutManager(new GridLayoutManager(this, 3, GridLayout.VERTICAL, false));
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons.clear();
        this.pokemons.addAll(pokemons);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress(boolean show) {
        binding.progress.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}