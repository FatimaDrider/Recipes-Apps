package com.example.recettes_app.Listeners;

import com.example.recettes_app.Models.RecettedetaiResponse;

public interface RecetteDetailListener {
    void didFetch(RecettedetaiResponse response, String message) ;
        void didError(String message);

    }
