package com.example.secondhand.otherActivities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.secondhand.R;
import com.example.secondhand.product.Product;

public class DetailActivity extends AppCompatActivity {
    TextView textView;
    Integer tmp;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //ActionBar actionBar = getSupportActionBar();

        Product prod = (Product)getIntent().getParcelableExtra("product");
        if (prod != null) {
            textView = (TextView)findViewById(R.id.nameProduct_pItem);
            textView.setText(prod.getNameProduct());

            textView = (TextView)findViewById(R.id.priceProduct_pItem);
            textView.setText(prod.getPriceProduct());

            textView = (TextView)findViewById(R.id.ratingProduct_detail);
            textView.setText(prod.getRatingProduct());

            textView = (TextView)findViewById(R.id.detailProduct_detail);
            textView.setText(prod.getDetailProduct());

            textView = (TextView)findViewById(R.id.category_detail);
            textView.setText(prod.getCategoryProduct());

            imageView = (ImageView) findViewById(R.id.image_detail);
            imageView.setImageResource(prod.getImageUrl());
            
            textView = (TextView)findViewById(R.id.remain_detail);
            textView.setText(prod.getRemainProduct());

            textView = (TextView)findViewById(R.id.phone_detail);
            textView.setText(prod.getPhoneNumber());
        }
        //actionBar.setTitle("Back");
    }
}
