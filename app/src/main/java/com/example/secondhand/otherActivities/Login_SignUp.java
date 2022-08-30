package com.example.secondhand.otherActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.secondhand.MainActivity;
import com.example.secondhand.otherActivities.User;
import com.example.secondhand.R;
import com.example.secondhand.product.Product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Login_SignUp extends AppCompatActivity {
    TextView register;
    TextView textLog_Sign, email, password;
    Button btnLog;
    ArrayList<Product> productArrayList = new ArrayList<>();
    List<User> userList = new ArrayList<>();
    DatabaseReference ref = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle("Back");
        actionBar.setDisplayHomeAsUpEnabled(true);


        FirebaseAuth database = FirebaseAuth.getInstance();
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();


        register = findViewById(R.id.register);
        textLog_Sign = findViewById(R.id.log_sign);
        email = findViewById(R.id.Email);
        password = findViewById(R.id.password);
        btnLog = findViewById(R.id.login);


        if (checkLogInOrNot(database) == false) {
            btnLog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (btnLog.getText().toString().trim().equals("Register")) {
                        //nay la dang ky
                        database.createUserWithEmailAndPassword(email.getText().toString().trim(), password.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Login_SignUp.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                                    //getUser(database1);
                                    createUseronFB(database1, new User(email.getText().toString()));
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                } else {
                                    Toast.makeText(Login_SignUp.this, "Error register! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else if (btnLog.getText().toString().equals("Log in")) {
                        //nay la dang nhap
                        if (email.getText().equals(null) || password.getText().equals(null)) {
                            Toast.makeText(Login_SignUp.this, "Your email and password can not be null", Toast.LENGTH_SHORT).show();
                        } else {
                            database.signInWithEmailAndPassword(email.getText().toString().trim(), password.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Login_SignUp.this, "Log in successfully", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                    } else {
                                        Toast.makeText(Login_SignUp.this, "Error log in! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    } else {
                        Toast.makeText(Login_SignUp.this, "Error and error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else {
            productArrayList = getIntent().getExtras().getParcelableArrayList("pListtoLogin");
            Intent intent = new Intent(this, LogOut.class);
            intent.putParcelableArrayListExtra("pListtoLogout", productArrayList);
            startActivity(intent);
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (register.getText() == "Log in") {
                    email.setText(null);
                    password.setText(null);
                    textLog_Sign.setText("Log In Good Market");
                    register.setText("Register");
                    btnLog.setText("Log in");

                } else {
                    email.setText(null);
                    password.setText(null);
                    textLog_Sign.setText("Sign Up Good Market");
                    register.setText("Log in");
                    btnLog.setText("Register");

                }
            }
        });
    }
    private boolean checkLogInOrNot(FirebaseAuth database) {
        if(database.getCurrentUser() != null){
            return true;
        }
        return false;
    }

    private void createUseronFB(FirebaseDatabase database1, User user) {
        ref = database1.getReference("User");
        ref.child(user.getName()).setValue(user);
    }

    private void getUser(FirebaseDatabase database1) {
        ref = database1.getReference("User");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    User newUser = dataSnapshot.getValue(User.class);
                    userList.add(newUser);
                }
                Toast.makeText(Login_SignUp.this, "For loop end", Toast.LENGTH_SHORT).show();
                userList.add(new User(email.getText().toString()));
                //createUseronFB(database1, userList);
                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Login_SignUp.this, "Can not get data of user!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
