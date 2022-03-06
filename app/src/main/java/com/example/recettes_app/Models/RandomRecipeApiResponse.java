package com.example.recettes_app.Models;

import com.example.recettes_app.Listeners.RandomRecipesResponseListener;

import java.util.ArrayList;

public class RandomRecipeApiResponse implements RandomRecipesResponseListener {
    public ArrayList<Recipe> recipes;


    @Override
    public void didFetch(RandomRecipeApiResponse response, String message) {

    }

    @Override
    public void didError(String message) {

    }
}
