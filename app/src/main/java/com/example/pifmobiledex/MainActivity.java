package com.example.pifmobiledex;


import static utils.PokeAPI.fetchDataAsync;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import utils.PokeAPI.*;
import utils.PokemonCollection;

public class MainActivity extends AppCompatActivity {
    String defaultPoke = "bulbasaur";
    ArrayList<PokemonCollection> pokemonCollections;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView stuff = (TextView) findViewById(R.id.pokemonName);
        //PokemonCollections goes to 419; 419 is Stunfisk. 30 new pokemon unaccounted for.
        try {
            pokemonCollections = getPokemonStats();
            Log.i("MainActivity", pokemonCollections.size() + "");
            stuff.setText(pokemonCollections.get(3).getName());

        } catch (ExecutionException | InterruptedException e) {
            Log.e("MainActivity", e.toString(), e);
        }
    }

    ArrayList<PokemonCollection> getPokemonStats() throws ExecutionException, InterruptedException {
       CompletableFuture<ArrayList<PokemonCollection>> pokemonCollection = fetchDataAsync();
       return pokemonCollection.get();

    }
}