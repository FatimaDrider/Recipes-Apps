package com.example.recettes_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recettes_app.Models.ExtendedIngredient;
import com.example.recettes_app.Models.Ingredient;
import com.example.recettes_app.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class IndrediantsAdapter extends  RecyclerView.Adapter<IngrediantsViewHolder> {
   Context context;
   List<ExtendedIngredient> list;

    public IndrediantsAdapter(Context context, List<ExtendedIngredient> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public IngrediantsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngrediantsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_ingredient,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull IngrediantsViewHolder holder, int position) {
holder.textView_ingredients_name.setText(list.get(position).name);
holder.text_ingrediant_quantite.setText(list.get(position).original);
      holder.textView_ingredients_name.setSelected(true);


        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/"+list.get(position).image).into(holder.imageView_ingrediants);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}


class IngrediantsViewHolder extends RecyclerView.ViewHolder {
TextView text_ingrediant_quantite,textView_ingredients_name;
ImageView imageView_ingrediants;
    public IngrediantsViewHolder(@NonNull View itemView) {
        super(itemView);
        text_ingrediant_quantite= itemView.findViewById(R.id.text_ingrediant_quantite);

        textView_ingredients_name= itemView.findViewById(R.id.textView_ingredients_name);
        imageView_ingrediants= itemView.findViewById(R.id.imageView_ingrediants);

    }
}