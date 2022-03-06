package com.example.recettes_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recettes_app.Adapters.Adaptateur_inscructions_etapes;
import com.example.recettes_app.Adapters.AdapteurInscuctions;
import com.example.recettes_app.Adapters.IndrediantsAdapter;
import com.example.recettes_app.Listeners.InscructionsLisntener;
import com.example.recettes_app.Listeners.RecetteDetailListener;
import com.example.recettes_app.Models.InstructionResponse;
import com.example.recettes_app.Models.RecettedetaiResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class recettesDetail extends AppCompatActivity {
int id;
TextView textView_meal_name,textView_meal_source_,textView_meal_summary;
ImageView imageView_meal_image;
RecyclerView Recyle_indredients,recyler_repas_insctructions;
RequestManager manager;
ProgressDialog dialog;
    AdapteurInscuctions adapteurInscuctions;

    IndrediantsAdapter indrediantsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recettes_detail);
        findViews();
        id =Integer.parseInt(getIntent().getStringExtra("id"));
        manager= new RequestManager(this);
        manager.getDetailRecettes(recetteDetailListener,id);
        manager.getInstricutions(inscructionsLisntener,id);


        dialog= new ProgressDialog(this);
        dialog.setTitle("Loading Detail.....");
        dialog.show();
    }

    private void findViews() {
       textView_meal_name=findViewById(R.id.textView_meal_name);
        textView_meal_source_= findViewById(R.id.textView_meal_source);
        textView_meal_summary= findViewById(R.id.textView_meal_summary);
        imageView_meal_image= findViewById(R.id.imageView_meal_image);
        Recyle_indredients= findViewById(R.id.recyle_indredients);
        recyler_repas_insctructions = findViewById(R.id.recyler_repas_insctructions);
    }




    private  final RecetteDetailListener recetteDetailListener = new RecetteDetailListener() {
        @Override
        public void didFetch(RecettedetaiResponse response, String message) {
            dialog.show();
            textView_meal_name.setText(response.title);
            textView_meal_source_.setText(response.sourceName);
            textView_meal_summary.setText(response.summary);
            Picasso.get().load(response.image).into(imageView_meal_image);
            Recyle_indredients.setHasFixedSize(true);
            Recyle_indredients.setLayoutManager(new LinearLayoutManager(recettesDetail.this, LinearLayoutManager.HORIZONTAL,false));
            //indrediantsAdapter = new IndrediantsAdapter(recettesDetail.this,response.);
            Recyle_indredients.setAdapter(indrediantsAdapter);

        }

        @Override
        public void didError(String message) {
            Toast.makeText(recettesDetail.this,message,Toast.LENGTH_SHORT).show();
        }
    };




    private  final InscructionsLisntener inscructionsLisntener= new InscructionsLisntener() {
        @Override
        public void didFetch(List<InstructionResponse> response, String message) {
        recyler_repas_insctructions.setHasFixedSize(true);
        recyler_repas_insctructions.setLayoutManager(new LinearLayoutManager(recettesDetail.this,LinearLayoutManager.VERTICAL,false));
        adapteurInscuctions  = new AdapteurInscuctions(recettesDetail.this,response);
        recyler_repas_insctructions.setAdapter(adapteurInscuctions);

        }

        @Override
        public void didError(String message) {

        }
    };

}