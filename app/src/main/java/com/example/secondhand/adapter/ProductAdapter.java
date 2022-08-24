package com.example.secondhand.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductAdapterViewHolder> {

    @NonNull
    @Override
    public ProductAdapter.ProductAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductAdapterViewHolder holder, int position) {
        holder.nameProduct.setImageResource(asiaFoodList.get(position).getImageUrl());
        holder..setText(asiaFoodList.get(position).getName());
        holder.price.setText(asiaFoodList.get(position).getPrice());
        holder.rating.setText(asiaFoodList.get(position).getRating());
        holder.restorantName.setText(asiaFoodList.get(position).getRestorantname());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ProductAdapterViewHolder extends RecyclerView.ViewHolder {
        public ProductAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


    public static final class ProductViewHolder extends RecyclerView.ViewHolder{


        ImageView foodImage;
        TextView price, name, rating, restorantName;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            //foodImage = itemView.findViewById(R.id.food_image);
            price = itemView.findViewById(R.id.price);
            name = itemView.findViewById(R.id.name);
            //   rating = itemView.findViewById(R.id.rating);
            // restorantName = itemView.findViewById(R.id.restorant_name);
        }
    }
}
