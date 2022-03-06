package com.example.recettes_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recettes_app.Listeners.RecetteClickListener;
import com.example.recettes_app.Models.Recipe;
import com.example.recettes_app.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RandomRecipeAdapter extends  RecyclerView.Adapter<RandamRecipesViewHolder>{
Context context;
List<Recipe> list;
    RecetteClickListener listener;
    public RandomRecipeAdapter(Context context, List<Recipe> list, RecetteClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener =listener;
    }
    @NonNull
    @Override
    public RandamRecipesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandamRecipesViewHolder(LayoutInflater.from(context).inflate(R.layout.list_recipes,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RandamRecipesViewHolder holder, int position) {
  holder.textview_title.setText(list.get(position).title);
  holder.textview_title.setSelected(true);
  holder.text_icon1.setText(list.get(position).aggregateLikes+" Likes");
  holder.text_icon2.setText(list.get(position).servings+" Servings");
  holder.text_icon3.setText(list.get(position).readyInMinutes+" Minutes");
        Picasso.get().load(list.get(position).image).into(holder.imageView_repas);

        holder.list_container.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
listener.onRecetteClicked(String.valueOf(list.get(holder.getAdapterPosition()).id));
      }
  });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}


class RandamRecipesViewHolder extends RecyclerView.ViewHolder{
CardView list_container;
TextView textview_title,text_icon1,text_icon2,text_icon3;
ImageView imageView_repas;
    public RandamRecipesViewHolder(@NonNull View itemView) {
        super(itemView);
 list_container = itemView.findViewById(R.id.list_container);
        textview_title = itemView.findViewById(R.id.textview_title) ;
        text_icon1 = itemView.findViewById(R.id.text_icon1) ;
        text_icon2 = itemView.findViewById(R.id.text_icon2) ;
        text_icon3 = itemView.findViewById(R.id.text_icon3) ;
        imageView_repas = itemView.findViewById(R.id.imageView_repas);








    }
}
