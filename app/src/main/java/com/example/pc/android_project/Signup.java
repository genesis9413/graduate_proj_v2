package com.example.pc.android_project;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;

public class Signup extends AppCompatActivity {

    EditText etEmail, etPw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        etEmail = (EditText)findViewById(R.id.etEmail);
        etPw = (EditText)findViewById(R.id.etPw);


    }

    public void signup(View view) {

           Intent i = new Intent(this, Login.class);
            startActivity(i);

    }


}