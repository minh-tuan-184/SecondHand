package com.example.secondhand.product;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Product implements Parcelable {
    String nameProduct;
    String priceProduct;
    String ratingProduct;
    String detailProduct;
    String categoryProduct;
    Integer imageUrl;
    Integer remainProduct;
    String phoneNumber;
    //List<Integer> star = new ArrayList<>();
    Map<String, Object> star = new HashMap<>();
    public Product () {

    }

    public void addStarList(String n, Object s) {
        //this.star.add(s);
        this.star.put(n,s);
    }

    public Product(String nameProduct, String priceProduct, String detailProduct, String categoryProduct, Integer imageUrl, Integer remainProduct, String phoneNumber, Map<String, Object> star) {
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.detailProduct = detailProduct;
        this.categoryProduct = categoryProduct;
        this.imageUrl = imageUrl;
        this.remainProduct = remainProduct;
        this.phoneNumber = phoneNumber;
        this.star = star;
        String sum = "0";
        /*for(int i=0;i<this.star.size();++i) {
            sum = sum + this.star.get(i);
        }
        if (this.star.size() > 1) {
            this.ratingProduct = String.valueOf(sum / this.star.size() - 1);
        }
        else this.ratingProduct = String.valueOf(0);*/
        Collection<Object> values = this.star.values();
        for(Object i : values) {
            sum = i + sum;
        }

        if (this.star.size() > 1) {
            this.ratingProduct = String.valueOf(Integer.parseInt(sum) / (this.star.size() - 1));
        } else this.ratingProduct = String.valueOf(0);
    }

    public Product(String nameProduct, String priceProduct, String detailProduct, String categoryProduct, Integer imageUrl, Integer remainProduct, String phoneNumber) {
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.detailProduct = detailProduct;
        this.categoryProduct = categoryProduct;
        this.imageUrl = imageUrl;
        this.remainProduct = remainProduct;
        this.phoneNumber = phoneNumber;
        /*this.star = new ArrayList<>();*/
        this.ratingProduct = String.valueOf(star.size());
        star.put("noname", 0);
    }

    protected Product(Parcel in) {
        nameProduct = in.readString();
        priceProduct = in.readString();
        ratingProduct = in.readString();
        detailProduct = in.readString();
        categoryProduct = in.readString();
        if (in.readByte() == 0) {
            imageUrl = null;
        } else {
            imageUrl = in.readInt();
        }
        if (in.readByte() == 0) {
            remainProduct = null;
        } else {
            remainProduct = in.readInt();
        }
        phoneNumber = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(String priceProduct) {
        this.priceProduct = priceProduct;
    }

    public String getRatingProduct() {
        return ratingProduct;
    }

    public void setRatingProduct(String ratingProduct) {
        this.ratingProduct = ratingProduct;
    }

    public String getDetailProduct() {
        return detailProduct;
    }

    public void setDetailProduct(String detailProduct) {
        this.detailProduct = detailProduct;
    }

    public String getCategoryProduct() {
        return categoryProduct;
    }

    public void setCategoryProduct(String categoryProduct) {
        this.categoryProduct = categoryProduct;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getRemainProduct() {
        return remainProduct;
    }

    public void setRemainProduct(Integer remainProduct) {
        this.remainProduct = remainProduct;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Map<String, Object> getStar() {
        return star;
    }

    public void setStar(Map<String, Object> star) {
        this.star = star;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nameProduct);
        dest.writeString(priceProduct);
        dest.writeString(ratingProduct);
        dest.writeString(detailProduct);
        dest.writeString(categoryProduct);
        if (imageUrl == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(imageUrl);
        }
        if (remainProduct == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(remainProduct);
        }
        dest.writeString(phoneNumber);
    }
}
