package com.example.recettes_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recettes_app.Models.InstructionResponse;
import com.example.recettes_app.R;
import com.example.recettes_app.recettesDetail;

import java.util.List;

public class AdapteurInscuctions extends RecyclerView.Adapter<InscructionsViewHolder> {
    Context context;
    List<InstructionResponse> list;

    public AdapteurInscuctions(Context context,  List<InstructionResponse> list) {
        this.context= context;
        this.list=list;
    }


    @NonNull
    @Override
    public InscructionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InscructionsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_inscructions,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull InscructionsViewHolder holder, int position) {
holder.textView_instructions_nom.setText(list.get(position).name);
holder.recycler_inscructions_etapes.setHasFixedSize(true);
   holder.recycler_inscructions_etapes.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
   Adaptateur_inscructions_etapes stepAdaptateur = new Adaptateur_inscructions_etapes(context,list.get(position).steps);
holder.recycler_inscructions_etapes.setAdapter(stepAdaptateur);
    }
//retourner liste des size
    @Override
    public int getItemCount() {
        return list.size();
    }
}


class InscructionsViewHolder extends RecyclerView.ViewHolder{
  TextView textView_instructions_nom;
  RecyclerView recycler_inscructions_etapes;
    public InscructionsViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_instructions_nom = itemView.findViewById(R.id.textView_instructions_nom);
        recycler_inscructions_etapes= itemView.findViewById(R.id.recycler_inscructions_etapes);
    }
}