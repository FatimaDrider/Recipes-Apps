package com.example.recettes_app;

import android.content.Context;

import com.example.recettes_app.Listeners.InscructionsLisntener;
import com.example.recettes_app.Listeners.RandomRecipesResponseListener;
import com.example.recettes_app.Listeners.RecetteDetailListener;
import com.example.recettes_app.Models.RandomRecipeApiResponse;
import com.example.recettes_app.Models.RecettedetaiResponse;
import com.example.recettes_app.Models.InstructionResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }


    public   void getRandomRecipes(RandomRecipesResponseListener listener,List<String> tags){
        CallRandomRecipes callRandomRecipes = retrofit.create(CallRandomRecipes.class);
         Call<RandomRecipeApiResponse> call =callRandomRecipes.callRandomRecipes(context.getString(R.string.api_key),"10",tags);
         call.enqueue(new Callback<RandomRecipeApiResponse>() {
             @Override
             public void onResponse(Call<RandomRecipeApiResponse> call, Response<RandomRecipeApiResponse> response) {
                 if(!response.isSuccessful()){
                     listener.didError(response.message());
                     return;
                 }
                 listener.didFetch(response.body(),response.message());
             }

             @Override
             public void onFailure(Call<RandomRecipeApiResponse> call, Throwable t) {
                 listener.didError(t.getMessage());

             }
         });
    }
    public void getDetailRecettes(RecetteDetailListener listener, int id){
        CallDetailrecette callDetailrecette = retrofit.create(CallDetailrecette.class);
        Call<RecettedetaiResponse> call = callDetailrecette.CallDetailrecette(id ,context.getString(R.string.api_key));
         call.enqueue(new Callback<RecettedetaiResponse>() {
             @Override
             public void onResponse(Call<RecettedetaiResponse> call, Response<RecettedetaiResponse> response) {
                 if(!response.isSuccessful()){
                     listener.didError(response.message());
                     return;
                 }

                 listener.didFetch(response.body(),response.message());
             }

             @Override
             public void onFailure(Call<RecettedetaiResponse> call, Throwable t) {
              listener.didError(t.getMessage());
              return;
             }


         });

    }

    public  void getInstricutions(InscructionsLisntener lisntener, int id){
        AppelInscructions appelInscructions = retrofit.create(AppelInscructions.class);

        Call<List<InstructionResponse>> call =appelInscructions.AppelInsctructions(id,context.getString(R.string.api_key));
        call.enqueue(new Callback<List<InstructionResponse>>() {
            @Override
            public void onResponse(Call<List<InstructionResponse>> call, Response<List<InstructionResponse>> response) {
                if(!response.isSuccessful()){
                    lisntener.didError(response.message());
                    return;
                }
                lisntener.didFetch(response.body(),response.message());
            }

            @Override
            public void onFailure(Call<List<InstructionResponse>> call, Throwable t) {
                  lisntener.didError(t.getMessage());
            }
        });
    }



    private interface  CallRandomRecipes{
        @GET("recipes/random")
        Call<RandomRecipeApiResponse> callRandomRecipes(
                @Query("apiKey") String apiKey,
                @Query("number") String number,
                @Query("tags") List<String> tags
                );

    }

    private interface  CallDetailrecette{
        @GET("recipes/{id}/information")
        Call<RecettedetaiResponse> CallDetailrecette(
                @Path("id") int id ,
                @Query("apiKey") String apiKey

        );

    }


    private interface AppelInscructions{
        @GET("recipes/{id}/analyzedInstructions")
        Call<List<InstructionResponse>> AppelInsctructions(
          @Path("id") int id ,
          @Query("apiKey") String apiKey
        );
    }


}
