package com.example.pc.android_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class wordmenu_control extends AppCompatActivity {

    Button[] numbtn = new Button[5];
    Integer[] numbtnId = {R.id.day_btn1, R.id.day_btn2, R.id.day_btn3, R.id.day_btn4, R.id.day_btn5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_menu);

        for (int i = 0; i < numbtnId.length; i++) {
            numbtn[i] = (Button) findViewById(numbtnId[i]);
        }

        for (int i = 0; i < numbtnId.length; i++) {
            final int index;
            index = i;

            numbtn[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(wordmenu_control.this, word_control.class);
                    String numday = numbtn[index].getText().toString();
                    intent.putExtra("numDay", numday);
                    intent.putExtra("numDayIndex", index+1);
                    startActivity(intent);
                }
            });
        }


    }
}
