package com.example.recettes_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.recettes_app.Adapters.RandomRecipeAdapter;
import com.example.recettes_app.Listeners.RandomRecipesResponseListener;
import com.example.recettes_app.Listeners.RecetteClickListener;
import com.example.recettes_app.Models.RandomRecipeApiResponse;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
ProgressDialog dialog;
RequestManager manager;
RandomRecipeAdapter randomRecipeAdapter;
RecyclerView recyclerView;
Spinner spinner;
SearchView searchView;
List<String> tags = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
      dialog = new ProgressDialog(this);
       dialog.setTitle("Loading ...");
         searchView = findViewById(R.id.search_home);

         searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
             @Override
             public boolean onQueryTextSubmit(String query) {
                 tags.clear();
                 tags.add(query);
                 manager.getRandomRecipes(randomRecipesResponseListener,tags);
                 dialog.show();
                 return true;
             }

             @Override
             public boolean onQueryTextChange(String newText) {
                 return false;
             }
         });


       spinner = findViewById(R.id.spinner1);
ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(
        this,
        R.array.tags,
        R.layout.activity_menu
);
arrayAdapter.setDropDownViewResource(R.layout.menu_text);
spinner.setAdapter(arrayAdapter);

spinner.setOnItemSelectedListener(spinnerSelectedLisenter);
        manager= new RequestManager(this);

        //manager.getRandomRecipes(randomRecipesResponseListener);
        //dialog.show();

    }


    private  final RandomRecipesResponseListener randomRecipesResponseListener= new RandomRecipesResponseListener() {
        @Override
        public void didFetch(RandomRecipeApiResponse response, String message) {
           // dialog.dismiss();
   recyclerView = findViewById(R.id.recyleview_random);
   recyclerView.setHasFixedSize(true);
   recyclerView.setLayoutManager(new GridLayoutManager(Home.this,1));
 randomRecipeAdapter=new RandomRecipeAdapter(Home.this,response.recipes,recetteClickListener);
      recyclerView.setAdapter( randomRecipeAdapter);

        }

        @Override
        public void didError(String message) {
            Toast.makeText(Home.this,message,Toast.LENGTH_SHORT);
        }
    };


    private  final AdapterView.OnItemSelectedListener spinnerSelectedLisenter = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
      tags.clear();
      tags.add(adapterView.getSelectedItem().toString());
      manager.getRandomRecipes(randomRecipesResponseListener,tags);
      dialog.show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };


    private  final RecetteClickListener recetteClickListener = new RecetteClickListener() {
        @Override
        public void onRecetteClicked(String id) {
            startActivity(new Intent(Home.this,recettesDetail.class)
                    .putExtra("id",id));
        }
    };
}