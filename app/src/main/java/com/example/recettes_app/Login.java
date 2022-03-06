package com.example.recettes_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
Button register,button_login;

EditText editTextEmail,editTextPassword;
Helper myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myDatabase= new Helper(this);

        register= findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,register.class));
            }
        });
editTextEmail=findViewById(R.id.email1);
editTextPassword=findViewById(R.id.password1);
        button_login=findViewById(R.id.login);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myLogin();

            }
        });
    }

    public void myLogin() {
        String email=editTextEmail.getText().toString();
        String password =editTextPassword.getText().toString();

        if(!isEmailValid(email)){
            Toast.makeText(this,"Email invalid",Toast.LENGTH_SHORT).show();
        }

        if(!isPaswwordValid(password)){
            Toast.makeText(this,"Password invalid",Toast.LENGTH_SHORT).show();

        }

        Cursor res= myDatabase.login_user(email,password);

        if(res.getCount()>=1){
            final  Intent i = new Intent(Login.this,Profile.class);
            startActivity(i);
        }else
        {
            Toast.makeText(this,"Invalid Account",Toast.LENGTH_SHORT).show();

        }


    }
public   boolean isEmailValid(String email){
        return email.contains("@");
}

public boolean isPaswwordValid(String password){
        return password.length() >5;
}
}