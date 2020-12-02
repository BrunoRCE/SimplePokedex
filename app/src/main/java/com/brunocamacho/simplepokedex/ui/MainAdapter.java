package com.brunocamacho.simplepokedex.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brunocamacho.simplepokedex.R;
import com.brunocamacho.simplepokedex.domain.entity.Pokemon;
import com.bumptech.glide.Glide;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<Pokemon> pokemons;

    public MainAdapter(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setPokemon(pokemons.get(position));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pokemon_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView name;

        public ViewHolder(View view) {
            super(view);

            image = view.findViewById(R.id.image);
            name = view.findViewById(R.id.name);
        }

        public void setPokemon(Pokemon pokemon) {

            Glide.with(image.getContext())
                    .load(pokemon.getImageUrl())
                    .error(R.drawable.ic_android)
                    .into(image);

            name.setText(pokemon.getName());
        }
    }
}
