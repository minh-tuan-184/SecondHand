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
import java.util.List;

public class User implements Parcelable {
    String email, name = null;
    List<String> loveList;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String email) {
        this.email = email;
        loveList = new ArrayList<>();
        loveList.add("null");
        /*for(int i=0 ;i< this.email.length(); ++i) {
            if (this.email.charAt(i) == '@') break;
            name = name + this.email.charAt(i);
        }*/
        name = this.email.substring(0, this.email.length() - 10);
    }

    public void setData_User() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference(this.email);
        ref.setValue(loveList);
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getLoveList() {
        return loveList;
    }

    public void setLoveList(List<String> loveList) {
        this.loveList = loveList;
    }

    protected User(Parcel in) {
        email = in.readString();
        loveList = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeStringList(loveList);
    }

    @Override
    public int describeContents() {
        return 0;
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
}
