package com.example.secondhand.otherActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.secondhand.MainActivity;
import com.example.secondhand.R;
import com.example.secondhand.product.Product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class LogOut extends AppCompatActivity {
    Button btnOut, btnUpdate, btnAboutUs, btn;
    ArrayList<Product> productArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_user);
        ActionBar actionBar = getSupportActionBar();
        FirebaseAuth database = FirebaseAuth.getInstance();
        actionBar.setTitle("Back");
        actionBar.setDisplayHomeAsUpEnabled(true);
        btnOut = findViewById(R.id.logout);
        btnUpdate = findViewById(R.id.update);
        btnAboutUs = findViewById(R.id.about_us);

        btnOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (database.getCurrentUser() != null) {
                    database.getInstance().signOut();
                    Toast.makeText(LogOut.this, "Log out successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LogOut.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (database.getCurrentUser() != null) {
                    //Toast.makeText(LogOut.this, "Let's update!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LogOut.this, UpdateUser.class);
                    startActivity(intent);
                }
            }
        });

        btnAboutUs.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (database.getCurrentUser() != null) {
                    //Toast.makeText(LogOut.this, "About Us!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LogOut.this, About_Us.class);
                    startActivity(intent);
                }
            }
        });

        btn = findViewById(R.id.love_list_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (database.getCurrentUser() != null) {
                    productArrayList = getIntent().getExtras().getParcelableArrayList("pListtoLogout");
                    Intent intent = new Intent(v.getContext(), LoveList.class);
                    intent.putParcelableArrayListExtra("pListtoLoveList", productArrayList);
                    startActivity(intent);
                } else Toast.makeText(LogOut.this, "You should log in to do this action", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
