package com.example.demogame;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class GameActivity extends Activity {

    ListView listView;
    Button create_r;
    Button start;
    TextView user_name;

    List<String> roomsList;

    String playerName = "";
    String roomName = "";

    FirebaseDatabase database;
    DatabaseReference roomRef;
    DatabaseReference roomsRef;
    DatabaseReference myRef;
    DatabaseReference reff;

    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

//        final PaintView paintView = new PaintView(this);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        listView = findViewById(R.id.list_view);
        create_r = findViewById(R.id.create_room);

        GetName();

        CreateRoom();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GameActivity.this, PaintActivityTest.class));
            }
        });
    }

    public void CreateRoom(){

        roomsList = new ArrayList<>();
        AddRoomEventListener();
        create_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fAuth = FirebaseAuth.getInstance();


                String user_id = fAuth.getCurrentUser().getUid();

                reff = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String name = dataSnapshot.child("name").getValue().toString();

                        create_r.setEnabled(false);
                        roomName = name;
                        roomRef = database.getReference("rooms/" + roomName); //+ "/player1"
                        AddRoomEventListener();
                        roomRef.setValue(name);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    private void AddRoomEventListener(){

        roomsRef = database.getReference("rooms");

        roomsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                roomsList.clear();
                Iterable<DataSnapshot> rooms = dataSnapshot.getChildren();
                for (DataSnapshot snapshot : rooms){
                    roomsList.add(snapshot.getKey());

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(GameActivity.this,
                            android.R.layout.simple_list_item_1, roomsList);

                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void GetName(){

        user_name = findViewById(R.id.user_name);

        fAuth = FirebaseAuth.getInstance();

        String user_id = fAuth.getCurrentUser().getUid();

        reff = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("name").getValue().toString();

                user_name.setText(name);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
