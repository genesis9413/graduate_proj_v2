package com.example.pc.android_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Review_word extends AppCompatActivity {

    Button word, script;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_page);

        word = (Button)findViewById(R.id.word);
        script = (Button)findViewById(R.id.script);




    }

    public void word(View view) {



    }

    public void script(View view) {

    }


}