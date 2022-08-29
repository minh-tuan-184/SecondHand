package com.example.secondhand;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.secondhand.adapter.CategoryAdapter;
import com.example.secondhand.adapter.ProductAdapter;
import com.example.secondhand.otherActivities.Login_SignUp;
import com.example.secondhand.otherActivities.LoveList;
import com.example.secondhand.product.Category;
import com.example.secondhand.product.Product;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView productRecycler, categoryRecycler;
    ProductAdapter pAdapter;
    CategoryAdapter cAdapter;
    ImageView account;
    Button btn;
    public ArrayList<Product> productList = new ArrayList<>();
    List<Category> categoryList = new ArrayList<>();
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("20125104 - 20125122");

        if (true) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.BLACK);
        }

        getList();

        /*productList.add(new Product("Nhà mặt phố cây xăng 99m^2", "4.990.000.000đ", "Nhà đẹp mã, có ma ám cần thanh lý cho những người can đảm", "Real Estate", R.drawable.nha, 1, "0931488542"));
        productList.add(new Product("Xe moto Yamaha", "32.560.000đ", "Xe chạy đường trường siêu êm", "Transport", R.drawable.moto_yamaha, 19, "0963566882"));
        productList.add(new Product("Sữa bột Ensure Gold", "200.000đ", "Nhiều dưỡng chất thiết yếu dành cho trẻ sơ sinh", "Food", R.drawable.sua_bot, 25, "0964255014"));
        productList.add(new Product("Áo thun chất chơi người dơi", "352.200đ", "Áo thun mát mẻ cho những ngày hè năng động", "Clothes", R.drawable.ao_thun_1, 347, "0908863636"));
        productList.add(new Product("Áo thun tay lỗ", "150.000đ", "Áo thun để đi chơi ngày hè", "Clothes", R.drawable.ao_thun_2,134, "0956335681"));
        productList.add(new Product("Áo thun nhiều màu sắc", "195.000đ", "Áo thun mát mẻ - tìm lại tuổi thanh xuân", "Clothes", R.drawable.ao_thun_3, 705, "0909631035"));
        productList.add(new Product("Dao nĩa bàn tiệc", "58.750đ", "Dao nĩa theo phong cách phương tây", "Item", R.drawable.dao_nia, 208, "0975266348"));
        productList.add(new Product("Tivi màn hình phẳng full HD 4k", "30.640.300đ", "Thiết kế sang trọng, đẹp mắt, chất lượng hình ảnh cực tốt, có kết nối mạng có thể xem YouTube", "Item", R.drawable.tivi, 3, "0797751743"));
        productList.add(new Product("Guitar Acoustic", "1.500.000đ", "Đàn đã mua nhưng chỉ sử dụng được vài lần, nay em muốn pass lại ạ!", "Musical Instruments", R.drawable.guitaracoustic, 3, "01214531748"));
        productList.add(new Product("Piano", "36.170.300đ", "Đàn piano du dương êm dịu", "Musical Instruments", R.drawable.piano, 1, "0903127754"));
        //addList(productList);*/



        categoryList.add(new Category("Real Estate", R.drawable.batdongsan));
        categoryList.add(new Category("Transport", R.drawable.xe));
        categoryList.add(new Category("Clothes", R.drawable.quanao));
        categoryList.add(new Category("Item", R.drawable.daonia));
        categoryList.add(new Category("Musical Instruments", R.drawable.nhaccu));
        categoryList.add(new Category("Pet", R.drawable.pet));
        categoryList.add(new Category("Food", R.drawable.thucan));


        //getList();
        //setProductRecycler(productList);
        //setCategoryRecycler(productList, categoryList);

        account = findViewById(R.id.account);
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Login_SignUp.class);
                startActivity(intent);
                //addList(productList);
            }
        });

        btn = findViewById(R.id.btn_loveList);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firebaseAuth.getCurrentUser() != null) {
                    Intent intent = new Intent(v.getContext(), LoveList.class);
                    intent.putParcelableArrayListExtra("pList", productList);
                    startActivity(intent);
                } else Toast.makeText(MainActivity.this, "You should log in to do this action", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addList(List<Product> p) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("productList");
        ref.setValue(p, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(MainActivity.this, "Add complete", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getList() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //FirebaseAuth database1 = FirebaseAuth.getInstance();
        DatabaseReference ref = database.getReference("productList");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Product newProduct = dataSnapshot.getValue(Product.class);
                    productList.add(newProduct);
                }
                setProductRecycler(productList);
                setCategoryRecycler(productList, categoryList);
                //Toast.makeText(MainActivity.this, database1.getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Can not get data!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setProductRecycler(List<Product> productList) {
        productRecycler = findViewById(R.id.product_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        productRecycler.setLayoutManager(layoutManager);
        pAdapter = new ProductAdapter(this, productList);
        productRecycler.setAdapter(pAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        productRecycler.addItemDecoration(dividerItemDecoration);
    }

    private void setCategoryRecycler(List<Product> productList, List<Category> categoryList) {
        categoryRecycler = findViewById(R.id.category_view_recylcer);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        categoryRecycler.setLayoutManager(layoutManager);
        cAdapter = new CategoryAdapter(this, categoryList, productList);
        categoryRecycler.setAdapter(cAdapter);
    }


}