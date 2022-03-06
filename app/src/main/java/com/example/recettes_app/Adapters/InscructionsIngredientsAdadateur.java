package com.example.recettes_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recettes_app.Models.Ingredient;
import com.example.recettes_app.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InscructionsIngredientsAdadateur extends RecyclerView.Adapter<InscructionIngrdientsViewHolder>{
   Context context;
   List<Ingredient> list;

    public InscructionsIngredientsAdadateur(Context context,List<Ingredient> list) {
        this.context = context;
        this.list=list;
    }

    @NonNull
    @Override
    public InscructionIngrdientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InscructionIngrdientsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_inscructions_etapes_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull InscructionIngrdientsViewHolder holder, int position) {
  holder.textView_insctructions_step_item.setText(list.get(position).name);
  holder.textView_insctructions_step_item.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/"+list.get(position).image).into(holder.imageView_inscructions_etapes_item);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class InscructionIngrdientsViewHolder extends RecyclerView.ViewHolder{
ImageView imageView_inscructions_etapes_item;
TextView textView_insctructions_step_item;
    public InscructionIngrdientsViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView_inscructions_etapes_item=itemView.findViewById(R.id.imageView_inscructions_etapes_item);
        textView_insctructions_step_item=itemView.findViewById(R.id.textView_insctructions_step_item);
    }
}
