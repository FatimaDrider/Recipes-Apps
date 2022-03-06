package com.example.recettes_app.Listeners;

import com.example.recettes_app.Models.InstructionResponse;

import java.util.List;

public interface InscructionsLisntener {
    void didFetch(List<InstructionResponse> response, String message);
    void didError(String message);
}
