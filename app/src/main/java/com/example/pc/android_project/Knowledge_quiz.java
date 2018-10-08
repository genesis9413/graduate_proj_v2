package com.example.pc.android_project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;

public class Knowledge_quiz extends AppCompatActivity {

    TextView quizTxt;
    Button answer, wrong;

    String qu = "";
    String an = "";
    String wr = "";

    String[] anArr = new String[5];
    String[] wrArr = new String[5];

    String id;

    int i = 0;  // right num
    int j = 0; //wrong num
    int sum;

    DBHelper helper = new DBHelper(this);
    Cursor cursor;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.knowledge_quiz);
        quizTxt = (TextView) findViewById(R.id.quizTxt);
        answer = (Button) findViewById(R.id.answer);
        wrong = (Button) findViewById(R.id.wrong);

        db = helper.getWritableDatabase();
        cursor = db.rawQuery("SELECT mean, word, _id FROM wordTB order by random()", null);

        while (cursor.moveToNext()) {
            qu = cursor.getString(0);
            an = cursor.getString(1);
            id = cursor.getString(2);
        }

        cursor = db.rawQuery("SELECT word FROM wordTB order by random()", null);
        while (cursor.moveToNext()) {
            wr = cursor.getString(0);
        }

        if (answer == wrong) {
            next_quiz();
        }
        quizTxt.setText(qu);
        answer.setText(an);
        wrong.setText(wr);

        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                sum = i + j;
                show_an();
                next_quiz();
            }
        });


        wrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                j++;
                sum = i + j;
                show_wr();
                next_quiz();

            }
        });


    }


    void in() {
        if (sum == 5) {
            Intent intent = new Intent(Knowledge_quiz.this, Knowledge_quiz_end.class);
            intent.putExtra("anArr", anArr);
            intent.putExtra("wrArr", wrArr);
            intent.putExtra("anCnt", i);
            startActivity(intent);
            finish();
        }
    }


    void show_an() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("(" + sum + "/5)");
        builder.setMessage("정답입니다.");
        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        anArr[i - 1] = id;
                        Log.v("arr", "" + Arrays.toString(anArr));
                        in();

                    }

                });

        builder.show();
    }

    void show_wr() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("(" + sum + "/5)");
        builder.setMessage("오답입니다.");
        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        wrArr[j - 1] = id;
                        Log.v("arr", "" + Arrays.toString(wrArr));
                        in();

                    }
                });

        builder.show();
    }

    void next_quiz() {


        db = helper.getWritableDatabase();

        cursor = db.rawQuery("SELECT mean, word,_id FROM wordTB order by random()", null);
        while (cursor.moveToNext()) {
            qu = cursor.getString(0);
            an = cursor.getString(1);
            id = cursor.getString(2);
        }

        cursor = db.rawQuery("SELECT word FROM wordTB order by random()", null);
        while (cursor.moveToNext()) {
            wr = cursor.getString(0);
        }

        quizTxt.setText(qu);
        answer.setText(an);
        wrong.setText(wr);

        Log.v("id", "" + id);
        cursor.close();
        db.close();


    }

}
