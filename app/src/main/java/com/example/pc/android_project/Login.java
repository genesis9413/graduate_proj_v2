package com.example.pc.android_project;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText etEmail, etPw;
    DBHelper helper = new DBHelper(this);
    Cursor cursor;
    SQLiteDatabase db;
    String email, pw, dbemail, dbpw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        etEmail = (EditText)findViewById(R.id.etEmail);
        etPw = (EditText)findViewById(R.id.etPw);

        db = helper.getWritableDatabase();
        cursor = db.rawQuery("SELECT id,pw  FROM userTB ", null);

        while (cursor.moveToNext()) {
            dbemail = cursor.getString(0);
            dbpw = cursor.getString(1);
        }


        email = etEmail.getText().toString();
        pw = etPw.getText().toString();






    }

    public void signin(View view) {

        if(dbemail.equals(etEmail.getText().toString())&& dbpw.equals(etPw.getText().toString())) {
            Toast.makeText(getApplicationContext(),"로그인성공", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }else{
            Toast.makeText(getApplicationContext(),"로그인실패", Toast.LENGTH_LONG).show();
        }

    }

    public void signup(View view) {

        Intent i = new Intent(this, Signup.class);
        startActivity(i);
    }


}