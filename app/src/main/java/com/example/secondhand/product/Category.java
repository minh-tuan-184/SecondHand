package com.example.secondhand.product;

import android.os.Parcel;
import android.os.Parcelable;

public class Category implements Parcelable {
    String nameCategory;
    Integer imageUrl;

    public Category(String nameCategory, Integer imageUrl) {
        this.nameCategory = nameCategory;
        this.imageUrl = imageUrl;
    }

    protected Category(Parcel in) {
        nameCategory = in.readString();
        if (in.readByte() == 0) {
            imageUrl = null;
        } else {
            imageUrl = in.readInt();
        }
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nameCategory);
        if (imageUrl == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(imageUrl);
        }
    }
}
