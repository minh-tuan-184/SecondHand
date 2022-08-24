package com.example.secondhand.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondhand.R;
import com.example.secondhand.product.Category;
import com.example.secondhand.product.Product;

import org.w3c.dom.Text;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryAdapterViewHolder> {
    Context context;
    List<Category> categoryList;
    List<Product> productList;
    public CategoryAdapter(Context context, List<Category> categoryList, List<Product> productList) {
        this.context = context;
        this.categoryList = categoryList;
        this.productList = productList;
    }


    @NonNull
    @Override
    public CategoryAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false);
        return new CategoryAdapter.CategoryAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryAdapterViewHolder holder, int position) {
        holder.nameCate.setText(categoryList.get(position).getNameCategory());
        holder.imageView.setImageResource(categoryList.get(position).getImageUrl());
        holder.itemView.setOnClickListener(view -> {
            String tmp = categoryList.get(position).getNameCategory();
            for(int i = 0; i < productList.size(); ++i) {
                if (productList.get(i).getCategoryProduct() == tmp) {
                    productList.remove(i);
                }
            }
        });
}

    @Override
    public int getItemCount() {
        return categoryList.size();
    }


    public static final class CategoryAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView nameCate;
        ImageView imageView;
        public CategoryAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.image_category);
            nameCate = (TextView) itemView.findViewById(R.id.name_category);
        }
    }
}
