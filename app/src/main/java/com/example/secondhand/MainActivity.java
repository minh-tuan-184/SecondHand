package com.example.secondhand;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.secondhand.adapter.ProductAdapter;
import com.example.secondhand.product.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView productRecycler;
    ProductAdapter pAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Product> productList = new ArrayList<>();

        productList.add(new Product("Nha mat pho gan cay xang 99m2", "4 ty 990 trieu", 4.5, "Nha dep ma co ma am can thanh ly gap", "Bat dong san", R.drawable.nha, 1, "0931488542"));
        productList.add(new Product("Xe moto yamaha", "49 trieu", 3.8, "Xe chay sieu em dung de chay duong truong", "Xe may", R.drawable.moto_yamaha, 19, "0963566882"));
        productList.add(new Product("Sua bot Ensure Gold", "149 nghin", 4.2, "Nhieu duong chat, danh cho be 3-6 tuoi", "Sua bot", R.drawable.sua_bot, 25, "0964255014"));
        productList.add(new Product("Ao thun chat choi nguoi doi", "249 nghin", 3.9, "Ao thun cuc mat cho ngay dai nang dong", "Ao", R.drawable.ao_thun_1, 347, "0908863636"));
        productList.add(new Product("Ao thun tay lo", "150 nghin", 4.1, "Ao thun de di choi ngay he", "Ao", R.drawable.ao_thun_2,134, "0956335681"));
        productList.add(new Product("Ao thun nhieu mau sac", "195 nghin", 3.7, "Ao thun mat me - Tim lai tuoi tre", "Ao", R.drawable.ao_thun_3, 705, "0909631035"));
        productList.add(new Product("Dao nia ban tiec", "135 nghin", 4.0, "Dao nia theo phong cach an tiec phuong Tay", "Vat dung", R.drawable.dao_nia, 208, "0975266348"));
    }

    private void setProductRecycler(List<Product> productList) {

        productRecycler = findViewById(R.id.product_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        productRecycler.setLayoutManager(layoutManager);
        productAdapter = new productAdapter(this, productList);
        productRecycler.setAdapter(productAdapter);

    }
}