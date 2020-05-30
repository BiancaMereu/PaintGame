package com.example.demogame;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class CreateAccountMain extends AppCompatActivity {

    EditText mUserName, mPassword, mEmail;
    Button mRegisterBtn;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    TextView mText;
    String name;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.create_account);

        mUserName = findViewById(R.id.Username_register);
        mPassword = findViewById(R.id.password_register);
        mRegisterBtn = findViewById(R.id.register);
        mEmail = findViewById(R.id.email);
        mText = findViewById(R.id.log_in_from_reg);

        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);



//        if (fAuth.getCurrentUser() != null) {
//            startActivity(new Intent(getApplicationContext(), GameActivity.class));
//            finish();
//        }

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required!");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is required!");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(CreateAccountMain.this, "User Created.", Toast.LENGTH_SHORT).show();

                            String user_id = fAuth.getCurrentUser().getUid();

                            DatabaseReference current_user = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);

                            name = mUserName.getText().toString();

                            Map newPost = new HashMap();
                            newPost.put("name", name);

                            current_user.setValue(newPost);


                            startActivity(new Intent(getApplicationContext(), GameActivity.class));
                        }
                        else{
                            Toast.makeText(CreateAccountMain.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        });

        mText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LogInActivity.class));
            }
        });

    }

}
