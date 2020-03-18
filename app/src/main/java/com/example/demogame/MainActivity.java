package com.example.demogame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(R.layout.activity_main);

        final PaintView paintView = new PaintView(this);

        setContentView(R.layout.activity_main);

        Button change_to_game = (Button)findViewById(R.id.button2);

        change_to_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                setContentView(paintView);
            }
        });

    }
}
