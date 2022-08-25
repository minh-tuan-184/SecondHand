package com.example.secondhand;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.secondhand.adapter.CategoryAdapter;
import com.example.secondhand.adapter.ProductAdapter;
import com.example.secondhand.product.Category;
import com.example.secondhand.product.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView productRecycler, categoryRecycler;
    ProductAdapter pAdapter;
    CategoryAdapter cAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("20125104 - 20125122");

        List<Product> productList = new ArrayList<>();

        productList.add(new Product("Nhà mặt phố cây xăng 99m^2", "4.990.000.000đ", "4.5", "Nhà đẹp mã, có ma ám cần thanh lý cho những người can đảm", "Real Estate", R.drawable.nha, 1, "0931488542"));
        productList.add(new Product("Xe moto Yamaha", "32.560.000đ", "3.8", "Xe chạy đường trường siêu êm", "Transport", R.drawable.moto_yamaha, 19, "0963566882"));
        productList.add(new Product("Sữa bột Ensure Gold", "200.000đ", "4.2", "Nhiều dưỡng chất thiết yếu dành cho trẻ sơ sinh", "Food", R.drawable.sua_bot, 25, "0964255014"));
        productList.add(new Product("Áo thun chất chơi người dơi", "352.200đ", "3.9", "Áo thun mát mẻ cho những ngày hè năng động", "Clothes", R.drawable.ao_thun_1, 347, "0908863636"));
        productList.add(new Product("Áo thun tay lỗ", "150.000đ", "4.1", "Áo thun để đi chơi ngày hè", "Clothes", R.drawable.ao_thun_2,134, "0956335681"));
        productList.add(new Product("Áo thun nhiều màu sắc", "195.000đ", "3.7", "Áo thun mát mẻ - tìm lại tuổi thanh xuân", "Clothes", R.drawable.ao_thun_3, 705, "0909631035"));
        productList.add(new Product("Dao nĩa bàn tiệc", "58.750đ", "4.0", "Dao nĩa theo phong cách phương tây", "Item", R.drawable.dao_nia, 208, "0975266348"));
        productList.add(new Product("Tivi màn hình phẳng full HD 4k", "30.640.300đ", "4.5", "Thiết kế sang trọng, đẹp mắt, chất lượng hình ảnh cực tốt, có kết nối mạng có thể xem YouTube", "Item", R.drawable.tivi, 3, "0797751743"));
        productList.add(new Product("Guitar Acoustic", "1.500.000đ", "4.7", "Đàn đã mua nhưng chỉ sử dụng được vài lần, nay em muốn pass lại ạ!", "Musical Instruments", R.drawable.guitaracoustic, 3, "01214531748"));
        productList.add(new Product("Piano", "36.170.300đ", "3.8", "Đàn piano du dương êm dịu", "Musical Instruments", R.drawable.piano, 1, "0903127754"));

        List<Category> categoryList = new ArrayList<>();

        categoryList.add(new Category("Real Estate", R.drawable.batdongsan));
        categoryList.add(new Category("Transport", R.drawable.xe));
        categoryList.add(new Category("Clothes", R.drawable.quanao));
        categoryList.add(new Category("Item", R.drawable.daonia));
        categoryList.add(new Category("Musical Instruments", R.drawable.nhaccu));
        categoryList.add(new Category("Pet", R.drawable.pet));
        categoryList.add(new Category("Food", R.drawable.thucan));


        setProductRecycler(productList);
        setCategoryRecycler(productList, categoryList);
    }

    private void setProductRecycler(List<Product> productList) {
        productRecycler = findViewById(R.id.product_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        productRecycler.setLayoutManager(layoutManager);
        pAdapter = new ProductAdapter(this, productList);
        productRecycler.setAdapter(pAdapter);
    }

    private void setCategoryRecycler(List<Product> productList, List<Category> categoryList) {
        categoryRecycler = findViewById(R.id.category_view_recylcer);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        categoryRecycler.setLayoutManager(layoutManager);
        cAdapter = new CategoryAdapter(this, categoryList, productList);
        categoryRecycler.setAdapter(cAdapter);
    }
}