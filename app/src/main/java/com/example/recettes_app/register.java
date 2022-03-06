package com.example.recettes_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class register extends AppCompatActivity {
EditText editTextEmail,editTextPassword,editTextRepassword,editTextphone,editTextusername;
Button register_user;
Helper myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myDatabase= new Helper(this);
editTextusername=findViewById(R.id.username);
editTextphone= findViewById(R.id.phone);
        editTextEmail=findViewById(R.id.email);
        editTextPassword=findViewById(R.id.password);
        editTextRepassword=findViewById(R.id.repassword);
        register_user= findViewById(R.id.register1);
        Register_user();

    }
    public void Register_user(){
register_user.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String username= editTextEmail.getText().toString();
        String email=editTextEmail.getText().toString();
        String phone= editTextEmail.getText().toString();

        String password=editTextPassword.getText().toString();
        String repassword= editTextRepassword.getText().toString();


        if(!password.equals(repassword)){
            Toast.makeText(register.this,"the password you insert not the same ",Toast.LENGTH_SHORT).show();
        }else if(!isEmailValid(email)){
            Toast.makeText(register.this,"Is not valid EMAIl try again ",Toast.LENGTH_SHORT).show();

        }else if(!isPasswordValid(password)){
            Toast.makeText(register.this,"Is not valid PASSWORD try again ",Toast.LENGTH_SHORT).show();

        }else if(email.isEmpty()){
            Toast.makeText(register.this,"Mail filed Required ",Toast.LENGTH_SHORT).show();

        }else if(password.isEmpty()){
            Toast.makeText(register.this,"password filed Required ",Toast.LENGTH_SHORT).show();
        }else
        {
            boolean isInsert= myDatabase.insertData(username,email,phone,password);
            new SweetAlertDialog(register.this,SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("MEssage")
                    .setContentText("You are Registered")
                    .setConfirmText("Ok")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            Intent i = new Intent(register.this, Login.class);
                            startActivity(i);
                        }
                    })
                    .show();
        }
    }

});    }

public boolean isEmailValid(String email){
        return email.contains("@");
}

public boolean isPasswordValid(String password){
        return password.length() >5;
}

}