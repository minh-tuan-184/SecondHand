package com.example.secondhand.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondhand.R;
import com.example.secondhand.otherActivities.CategoryActivity;
import com.example.secondhand.otherActivities.DetailActivity;
import com.example.secondhand.product.Category;
import com.example.secondhand.product.Product;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryAdapterViewHolder> {
    Context context;
    List<Category> categoryList;
    ArrayList<Product> productList;

    public CategoryAdapter(Context context, List<Category> categoryList, List<Product> productList) {
        this.context = context;
        this.categoryList = categoryList;
        this.productList = new ArrayList<Product>(productList);
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
                if (!productList.get(i).getCategoryProduct().equals(tmp) && i >= 0) {
                    productList.remove(i);
                    --i;
                }
            }
            Intent i = new Intent(context, CategoryActivity.class);
            i.putParcelableArrayListExtra("category", productList);
            i.putExtra("nameCategory", tmp);
            context.startActivity(i);
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
