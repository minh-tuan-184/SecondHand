package com.example.secondhand.otherActivities;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.secondhand.R;

public class About_Us extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Back");
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
