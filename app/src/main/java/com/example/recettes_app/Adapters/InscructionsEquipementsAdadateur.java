package com.example.recettes_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recettes_app.Models.Equipment;
import com.example.recettes_app.Models.Ingredient;
import com.example.recettes_app.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InscructionsEquipementsAdadateur extends RecyclerView.Adapter<InscructionEquipementsViewHolder>{
   Context context;
   List<Equipment> list;

    public InscructionsEquipementsAdadateur(Context context,List<Equipment> list) {
        this.context = context;
        this.list=list;
    }

    @NonNull
    @Override
    public InscructionEquipementsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InscructionEquipementsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_inscructions_etapes_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull InscructionEquipementsViewHolder holder, int position) {
  holder.textView_insctructions_step_item.setText(list.get(position).name);
  holder.textView_insctructions_step_item.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/equipment_100x100/"+list.get(position).image);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class InscructionEquipementsViewHolder extends RecyclerView.ViewHolder{
ImageView imageView_inscructions_etapes_item;
TextView textView_insctructions_step_item;
    public InscructionEquipementsViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView_inscructions_etapes_item=itemView.findViewById(R.id.imageView_inscructions_etapes_item);
        textView_insctructions_step_item=itemView.findViewById(R.id.textView_insctructions_step_item);
    }
}
