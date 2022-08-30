package com.example.secondhand.otherActivities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondhand.R;
import com.example.secondhand.adapter.ProductAdapter;
import com.example.secondhand.product.Product;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LoveList extends AppCompatActivity {
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference ref = null;
    RecyclerView productRecycler;
    ProductAdapter pAdapter;
    ArrayList<User> userArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.love_list);

        String subemail = firebaseAuth.getCurrentUser().getEmail().substring(0, firebaseAuth.getCurrentUser().getEmail().length() - 10);
        ArrayList<Product> productList = getIntent().getExtras().getParcelableArrayList("pListtoLoveList");
        getLoveListUser(subemail, productList);
    }

    private void getLoveListUser(String subemail, ArrayList<Product> productList) {
        ref = firebaseDatabase.getReference("User");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    User user = dataSnapshot.getValue(User.class);
                    userArrayList.add(user);
                }
                removeAllUser(subemail);
                removeAllproduct(productList);
                setProductRecycler(productList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LoveList.this, "Can not get data of user!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setProductRecycler(ArrayList<Product> productList) {
        productRecycler = findViewById(R.id.recycler_loveList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        productRecycler.setLayoutManager(layoutManager);
        if (productList.size() != 0) {
            pAdapter = new ProductAdapter(this, productList);
            productRecycler.setAdapter(pAdapter);
        }
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        productRecycler.addItemDecoration(dividerItemDecoration);
    }

    private void removeAllUser(String subemail) {
        for (int i = 0; i < userArrayList.size(); ++i) {
            if (!userArrayList.get(i).getName().equals(subemail)) {
                userArrayList.remove(i);
                --i;
            }
        }
    }

    private void removeAllproduct(ArrayList<Product> productList) {
        for (int i = 0; i < productList.size(); ++i) {
            if (!userArrayList.get(0).elementLoveList(productList.get(i).getNameProduct())) {
                productList.remove(i);
                --i;
            }
        }
    }
}