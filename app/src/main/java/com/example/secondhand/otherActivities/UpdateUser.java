package com.example.secondhand.otherActivities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.secondhand.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UpdateUser extends AppCompatActivity {
    EditText oldEmail, oldPassword;
    EditText changePassword;
    Button  Update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_pass);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Back");
        actionBar.setDisplayHomeAsUpEnabled(true);
        Update = findViewById(R.id.updatePassword);

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reAuthenticate();
                onClickUpdatePassword();
            }
        });
    }

    private void reAuthenticate() {
        oldEmail = findViewById(R.id.old_email);
        oldPassword = findViewById(R.id.old_pass);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        AuthCredential credential = EmailAuthProvider
                .getCredential(oldEmail.getText().toString().trim(), oldPassword.getText().toString().trim());
        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(UpdateUser.this, "Re-authenticate successful", Toast.LENGTH_SHORT).show();
                        } else{
                            Toast.makeText(UpdateUser.this, "Failed to re-authenticate", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void onClickUpdatePassword () {
        changePassword = findViewById(R.id.new_pass);
        String strNewEmail = changePassword.getText().toString().trim();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.updatePassword(strNewEmail)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(UpdateUser.this, "User password updated.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(UpdateUser.this, "Failed to update password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}
