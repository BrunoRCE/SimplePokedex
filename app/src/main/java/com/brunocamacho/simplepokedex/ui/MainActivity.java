package com.brunocamacho.simplepokedex.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.brunocamacho.simplepokedex.R;
import com.brunocamacho.simplepokedex.interactor.GetPokemons;
import com.brunocamacho.simplepokedex.model.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private MainAdapter adapter;
    private List<Pokemon> pokemons;

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        progressBar = findViewById(R.id.progress);

        presenter = new MainPresenter(this, new GetPokemons());

        pokemons = new ArrayList<>();

        adapter = new MainAdapter(pokemons);

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3, GridLayout.VERTICAL, false));
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
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}