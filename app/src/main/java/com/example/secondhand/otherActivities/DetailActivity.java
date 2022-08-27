package com.example.secondhand.otherActivities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.secondhand.MainActivity;
import com.example.secondhand.R;
import com.example.secondhand.product.Product;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailActivity extends AppCompatActivity {
    TextView textView;
    Integer tmp;
    ImageView imageView;
    ImageView call, sms, love;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle("Back");
        actionBar.setDisplayHomeAsUpEnabled(true);

        Product prod = (Product) getIntent().getParcelableExtra("product");
        if (prod != null) {
            textView = (TextView) findViewById(R.id.nameProduct_detail);
            textView.setText(prod.getNameProduct());

            textView = (TextView) findViewById(R.id.priceProduct_detail);
            textView.setText(prod.getPriceProduct());

            textView = (TextView) findViewById(R.id.ratingProduct_detail);
            textView.setText(prod.getRatingProduct());

            textView = (TextView) findViewById(R.id.detailProduct_detail);
            textView.setText(prod.getDetailProduct());

            textView = (TextView) findViewById(R.id.category_detail);
            textView.setText(prod.getCategoryProduct());

            imageView = (ImageView) findViewById(R.id.image_detail);
            imageView.setImageResource(prod.getImageUrl());

            textView = (TextView) findViewById(R.id.remain_detail);
            textView.setText(String.valueOf(prod.getRemainProduct()));

            textView = (TextView) findViewById(R.id.phone_detail);
            textView.setText(prod.getPhoneNumber());
        }

        call = findViewById(R.id.phonecall);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialPhoneNumber(prod.getPhoneNumber());
            }
        });

        sms = findViewById(R.id.mess);
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", prod.getPhoneNumber(), null));
                intent.putExtra("sms_body", "Hello, I would like to buy your product!");
                startActivity(intent);
            }
        });

        love = findViewById(R.id.love_list);
        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this,
                    "Cannot find any app to handle this action!!",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}
