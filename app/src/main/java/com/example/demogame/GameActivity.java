package com.example.demogame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class GameActivity extends Activity {

    Button create_r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        PaintView paintView = new PaintView(this);

        create_r = findViewById(R.id.create_room);

        create_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(GameActivity.this, PaintView.class));
//                setContentView(R.class.paintView);
            }
        });
    }


}
