package com.example.pc.android_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Knowledge_quiz_control extends AppCompatActivity {

    Button quiz_word, quiz_script;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.knowledge_quiz_conrtrol);

        quiz_word = (Button)findViewById(R.id.quiz_word);
        quiz_script = (Button)findViewById(R.id.quiz_script);



        quiz_word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(Knowledge_quiz_control.this, Knowledge_quiz.class);
                startActivity(intent);
            }
        });

        quiz_script.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(Knowledge_quiz_control.this, Knowledge_quiz_script.class);
                startActivity(intent);
            }
        });


    }
}
