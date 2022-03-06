package com.example.recettes_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recettes_app.Models.Step;
import com.example.recettes_app.R;

import java.util.List;

public class Adaptateur_inscructions_etapes extends RecyclerView.Adapter<InstructionsEtapeViewHolder> {
    Context context;
    List<Step> list;

    public Adaptateur_inscructions_etapes(Context context,List<Step> list) {
        this.context = context;
        this.list =list;
    }

    @NonNull
    @Override
    public InstructionsEtapeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionsEtapeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_inscructions_etapes,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionsEtapeViewHolder holder, int position) {
  holder.textView_inscructions_etapes_nombre.setText(String.valueOf(list.get(position).number));
  holder.textView_inscructions_etapes_titre.setText(list.get(position).step);
holder.recycler_instructions_ingredients.setHasFixedSize(true);
holder.recycler_instructions_ingredients.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        InscructionsIngredientsAdadateur inscructionsIngredientsAdadateur = new InscructionsIngredientsAdadateur(context,list.get(position).ingredients);
holder.recycler_instructions_ingredients.setAdapter(inscructionsIngredientsAdadateur);
   holder.recycler_instructions_equipements.setHasFixedSize(true);
   holder.recycler_instructions_equipements.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
   InscructionsEquipementsAdadateur inscructionsEquipementsAdadateur= new InscructionsEquipementsAdadateur(context,list.get(position).equipment);
  holder.recycler_instructions_equipements.setAdapter(inscructionsEquipementsAdadateur);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class InstructionsEtapeViewHolder extends RecyclerView.ViewHolder{
 TextView textView_inscructions_etapes_nombre,textView_inscructions_etapes_titre;
 RecyclerView recycler_instructions_equipements,recycler_instructions_ingredients;
    public InstructionsEtapeViewHolder(@NonNull View itemView) {
        super(itemView);

        textView_inscructions_etapes_nombre= itemView.findViewById(R.id.textView_inscructions_etapes_nombre);
        textView_inscructions_etapes_titre= itemView.findViewById(R.id.textView_inscructions_etapes_titre);
        recycler_instructions_equipements= itemView.findViewById(R.id.recycler_instructions_equipements);
        recycler_instructions_ingredients=itemView.findViewById(R.id.recycler_instructions_ingredients);
    }
}
