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
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondhand.R;
import com.example.secondhand.otherActivities.DetailActivity;
import com.example.secondhand.product.Product;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductAdapterViewHolder> {

    Context context;
    List<Product> productList;
    FirebaseAuth database = FirebaseAuth.getInstance();

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        return new ProductAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductAdapterViewHolder holder, int position) {
       holder.productImage.setImageResource(productList.get(position).getImageUrl());
       holder.name.setText(productList.get(position).getNameProduct());
       holder.price.setText(productList.get(position).getPriceProduct());
       holder.itemView.setOnClickListener(view -> {
           Intent i = new Intent(context, DetailActivity.class);
           Product newProd = new Product(productList.get(position).getNameProduct(),
                   productList.get(position).getPriceProduct(),
                   productList.get(position).getDetailProduct(),
                   productList.get(position).getCategoryProduct(),
                   productList.get(position).getImageUrl(),
                   productList.get(position).getRemainProduct(),
                   productList.get(position).getPhoneNumber(),
                   productList.get(position).getStar());
           i.putExtra("product", newProd);
           context.startActivity(i);
       });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static final class ProductAdapterViewHolder extends RecyclerView.ViewHolder{
        ImageView productImage;
        TextView price, name;

        public ProductAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage =itemView.findViewById(R.id.product_image);
            price = itemView.findViewById(R.id.priceProduct_pItem);
            name = itemView.findViewById(R.id.nameProduct_pItem);
        }
    }
}
