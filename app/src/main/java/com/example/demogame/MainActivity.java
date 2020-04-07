package com.example.demogame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.ContentView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        final PaintView paintView = new PaintView(this);

        setContentView(R.layout.first_activity);

        setupLaunchButton();
    }

    private void setupLaunchButton(){

        Button change_to_log_in = findViewById(R.id.log_in);

        change_to_log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LogInActivity.class));
            }
        });

        Button change_to_create_account = findViewById(R.id.create_account);

        change_to_create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CreateAccountMain.class));
            }
        });
    }

    public void logout(){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), CreateAccountMain.class));
        finish();
    }

}
