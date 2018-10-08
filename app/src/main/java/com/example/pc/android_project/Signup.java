package com.example.pc.android_project;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class Signup extends AppCompatActivity {

    EditText etEmail, etPw;
    DBHelper helper = new DBHelper(this);
    Cursor cursor;
    SQLiteDatabase db;

    String email, pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        etEmail = (EditText)findViewById(R.id.etEmail);
        etPw = (EditText)findViewById(R.id.etPw);

        db = helper.getWritableDatabase();

        email = etEmail.getText().toString();
        pw = etPw.getText().toString();

    }

    public void signup(View view) {
        try {
            db.execSQL("INSERT INTO userTB (id,pw) Values ('" + email + "','" + pw + "');");
            db.close();

            Intent i = new Intent(this, Login.class);
            startActivity(i);
        }catch (SQLException e){
            Toast.makeText(getApplicationContext(),"다시입력해주세요", Toast.LENGTH_LONG).show();
        }
    }


}