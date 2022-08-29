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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    TextView textView;
    ImageView imageView;
    ImageView call, sms, love, rating;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    DatabaseReference ref = null;
    ArrayList<User> userArrayList = new ArrayList<>();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle("Back");
        actionBar.setDisplayHomeAsUpEnabled(true);

        Product prod = (Product) getIntent().getParcelableExtra("product");
        Integer pos = getIntent().getIntExtra("position", 0);
        User user = new User(firebaseAuth.getCurrentUser().getEmail());

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

            love = (ImageView) findViewById(R.id.love_list);
            love.setImageResource(R.drawable.redheart);
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
                addLoveList(prod, user);
            }
        });

        rating = findViewById(R.id.voteStar);
        rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, RatingActivity.class);
                intent.putExtra("addStar",prod);
                intent.putExtra("pos", pos);
                startActivity(intent);
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

    public void addLoveList (Product product, User user) {

        String subemail = firebaseAuth.getCurrentUser().getEmail().substring(0, firebaseAuth.getCurrentUser().getEmail().length() - 10);

        user.addLove(product.getNameProduct());
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("User");
        ref.child(subemail).child("loveList").updateChildren(user.getLoveList());

        Toast.makeText(this, "Add " + (char)34 + product.getNameProduct() + (char)34 + " to love list", Toast.LENGTH_SHORT).show();
    }
}
