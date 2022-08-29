package com.example.secondhand.otherActivities;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.secondhand.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User implements Parcelable {
    String email, name = null;
    Map<String, Object> loveList;

    public User () {

    }


    public User(String email) {
        this.email = email;
        this.name = email.substring(0, this.email.length() - 10);
        loveList = new HashMap<>();
        loveList.put("null", 0);
    }

    protected User(Parcel in) {
        email = in.readString();
        name = in.readString();
    }

    public void addLove(String nameP) {
        loveList.put(nameP, 0);
    }

    public boolean elementLoveList(String i) {
        for (Map.Entry<String, Object> entry : loveList.entrySet()) {
            if (entry.getKey().equals(i))
                return true;
        }
        return false;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getLoveList() {
        return loveList;
    }

    public void setLoveList(Map<String, Object> loveList) {
        this.loveList = loveList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(name);
    }
}
