package com.example.pc.android_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText etEmail, etPw;
    String email = "zz@zz";
    String password = "zzzz";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        etEmail = (EditText)findViewById(R.id.etEmail);
        etPw = (EditText)findViewById(R.id.etPw);




    }

    public void signin(View view) {
/*
        if(email.equals(etEmail.getText().toString())&& password.equals(etPw.getText().toString())) {
            Toast.makeText(getApplicationContext(),"로그인성공", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }else{
            Toast.makeText(getApplicationContext(),"로그인실패", Toast.LENGTH_LONG).show();
        }
        */
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }


}