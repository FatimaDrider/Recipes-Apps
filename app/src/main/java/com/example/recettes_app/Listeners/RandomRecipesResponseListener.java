package com.example.recettes_app.Listeners;

import com.example.recettes_app.Models.RandomRecipeApiResponse;

public interface RandomRecipesResponseListener {
    void didFetch(RandomRecipeApiResponse response, String message);
    void didError(String message);


}
