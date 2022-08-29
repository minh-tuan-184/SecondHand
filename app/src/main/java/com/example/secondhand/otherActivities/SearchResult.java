package com.example.secondhand.otherActivities;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondhand.R;
import com.example.secondhand.adapter.ProductAdapter;
import com.example.secondhand.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchResult extends AppCompatActivity {
    String s;
    ArrayList<Product> productArrayList = new ArrayList<>();
    RecyclerView productRecycler;
    ProductAdapter pAdapter;
    TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result);

        s = getIntent().getStringExtra("SearchString");
        productArrayList = getIntent().getExtras().getParcelableArrayList("listResult");

        textView = findViewById(R.id.notfound_text);

        searchString();
        setProductRecycler(productArrayList);
    }

    private void searchString() {
        if (s == null || s.equals("")) {
            Toast.makeText(this, "You must input something to find", Toast.LENGTH_SHORT).show();
            finish();
        }

        for(int i =0; i<productArrayList.size(); ++i) {
            if (!productArrayList.get(i).getNameProduct().contains(s) && i >= 0) {
                productArrayList.remove(i);
                --i;
            }
        }
    }

    private void setProductRecycler(List<Product> productList) {
        productRecycler = findViewById(R.id.result_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        productRecycler.setLayoutManager(layoutManager);
        if (productList.size() != 0) {
            pAdapter = new ProductAdapter(this, productList);
            productRecycler.setAdapter(pAdapter);
            textView.setText("Results for " + (char)34 + s + (char)34);
        }
        else {
            textView.setText("No results!");
        }
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        productRecycler.addItemDecoration(dividerItemDecoration);
    }
}
