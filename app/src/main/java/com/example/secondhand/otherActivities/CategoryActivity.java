package com.example.secondhand.otherActivities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondhand.R;
import com.example.secondhand.adapter.ProductAdapter;
import com.example.secondhand.product.Product;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    TextView textView;
    ImageView imageView;
    RecyclerView productRecycler, categoryRecycler;
    ProductAdapter pAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();

        ArrayList<Product> productArrayList = getIntent().getParcelableArrayListExtra("category");
        String title = getIntent().getExtras().getString("nameCategory");

        actionBar.setTitle(title);
        actionBar.setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_category);

        setProductRecycler(productArrayList);

    }

    private void setProductRecycler(ArrayList<Product> productList) {
        productRecycler = findViewById(R.id.category_view_recylcer);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        productRecycler.setLayoutManager(layoutManager);
        pAdapter = new ProductAdapter(this, productList);
        productRecycler.setAdapter(pAdapter);
    }
}
