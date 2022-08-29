package com.example.secondhand.otherActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.secondhand.MainActivity;
import com.example.secondhand.R;
import com.example.secondhand.product.Product;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RatingActivity extends AppCompatActivity {
    ImageView s1,s2,s3,s4,s5;
    String name = null;
    Product product;
    Integer integer, check = 0;
    Button cancel, submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating_activity);
        //ActionBar actionBar = getSupportActionBar();
        FirebaseAuth database = FirebaseAuth.getInstance();
        //actionBar.setTitle("Back");

        if(database.getCurrentUser() != null && database.getCurrentUser().getEmail().length() > 11) {
            name = database.getCurrentUser().getEmail().substring(0, database.getCurrentUser().getEmail().length() - 10);
        }
        else {
            Toast.makeText(this, "You have to sign in to rate", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RatingActivity.this, MainActivity.class);
            startActivity(intent);
        }

        s1 = findViewById(R.id.star1);
        s2 = findViewById(R.id.star2);
        s3 = findViewById(R.id.star3);
        s4 = findViewById(R.id.star4);
        s5 = findViewById(R.id.star5);
        cancel = findViewById(R.id.btn_cancel);
        submit = findViewById(R.id.btn_submit);

        product = (Product) getIntent().getParcelableExtra("addStar");
        integer = getIntent().getIntExtra("pos", 0);


        s1.setImageResource(R.drawable.star_off);
        s2.setImageResource(R.drawable.star_off);
        s3.setImageResource(R.drawable.star_off);
        s4.setImageResource(R.drawable.star_off);
        s5.setImageResource(R.drawable.star_off);

        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1.setImageResource(R.drawable.ic_baseline_star_rate_24);
                s2.setImageResource(R.drawable.star_off);
                s3.setImageResource(R.drawable.star_off);
                s4.setImageResource(R.drawable.star_off);
                s5.setImageResource(R.drawable.star_off);
                check = 1;
            }
        });

        s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1.setImageResource(R.drawable.ic_baseline_star_rate_24);
                s2.setImageResource(R.drawable.ic_baseline_star_rate_24);
                s3.setImageResource(R.drawable.star_off);
                s4.setImageResource(R.drawable.star_off);
                s5.setImageResource(R.drawable.star_off);
                check = 2;
            }
        });

        s3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1.setImageResource(R.drawable.ic_baseline_star_rate_24);
                s2.setImageResource(R.drawable.ic_baseline_star_rate_24);
                s3.setImageResource(R.drawable.ic_baseline_star_rate_24);
                s4.setImageResource(R.drawable.star_off);
                s5.setImageResource(R.drawable.star_off);
                check = 3;
            }
        });

        s4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1.setImageResource(R.drawable.ic_baseline_star_rate_24);
                s2.setImageResource(R.drawable.ic_baseline_star_rate_24);
                s3.setImageResource(R.drawable.ic_baseline_star_rate_24);
                s4.setImageResource(R.drawable.ic_baseline_star_rate_24);
                s5.setImageResource(R.drawable.star_off);
                check = 4;
            }
        });

        s5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1.setImageResource(R.drawable.ic_baseline_star_rate_24);
                s2.setImageResource(R.drawable.ic_baseline_star_rate_24);
                s3.setImageResource(R.drawable.ic_baseline_star_rate_24);
                s4.setImageResource(R.drawable.ic_baseline_star_rate_24);
                s5.setImageResource(R.drawable.ic_baseline_star_rate_24);
                check = 5;
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RatingActivity.this, MainActivity.class);
                Toast.makeText(RatingActivity.this, "Cancel Rating", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.addStarList(name, check);
                updateList(integer, product);
                Intent i = new Intent(RatingActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void updateList(Integer pos, Product product) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("productList");
        ref.child(String.valueOf(pos)).child("star").updateChildren(product.getStar());
        Toast.makeText(this, "Rating Successfully", Toast.LENGTH_SHORT).show();
    }
}
