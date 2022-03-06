package com.example.recettes_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {
Button button_update,button_logout;
TextView  phone,email;
Helper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        button_update = findViewById(R.id.button_update);
        button_logout = findViewById(R.id.button_logOut);
        phone= findViewById(R.id.phone);
        email=findViewById(R.id.email);
        helper = new Helper(this);
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Profile.this, UpdateProfile.class));
            }
        });
        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }



    public String ReadData() {
        StringBuffer buffer = new StringBuffer();

        Cursor cursor = helper.getAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                buffer.append(" \n Username : " + cursor.getString(1));
                buffer.append(" \n Email:" + cursor.getString(2));
                buffer.append(" \n Phone: " + cursor.getString(3));
                buffer.append(" \n Password : " + cursor.getString(4));
            }

        }
        return  buffer.toString();
    }







}