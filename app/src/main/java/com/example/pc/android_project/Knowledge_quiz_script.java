package com.example.pc.android_project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Knowledge_quiz_script extends AppCompatActivity {

    TextView quizTxt;
    Button answer, wrong;
    String qu = "";
    String an = "";
    String wr = "";
    Integer i = 1;
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
        cursor = db.rawQuery("SELECT question, answer  FROM script_01TB,script_02TB,script_03TB order by random()", null);

        while (cursor.moveToNext()) {
            qu = cursor.getString(0);
            an = cursor.getString(1);
        }

        cursor = db.rawQuery("SELECT answer FROM script_01TB,script_02TB,script_03TB order by random()", null);
        while (cursor.moveToNext()) {
            wr = cursor.getString(0);
        }

        if(answer.equals(an)&& wrong.equals(wr)) {
           next_quiz();
        }
        quizTxt.setText(qu);
        answer.setText(an);
        wrong.setText(wr);

        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next_quiz();
                show_an();
            }
        });

        wrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                show_wr();
            }
        });
    }

    void show_an() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("정답입니다.");
        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        builder.show();
    }
    void show_wr() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("오답입니다.\n다시한번 고민해보세요.");
        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        builder.show();
    }

    void next_quiz(){
        db = helper.getWritableDatabase();

        cursor = db.rawQuery("SELECT question, answer FROM script_01TB order by random()", null);
        while (cursor.moveToNext()) {
            qu = cursor.getString(0);
            an = cursor.getString(1);
        }

        cursor = db.rawQuery("SELECT answer FROM script_01TB order by random()", null);
        while (cursor.moveToNext()) {
            wr = cursor.getString(0);
        }

        quizTxt.setText(qu);
        answer.setText(an);
        wrong.setText(wr);

        cursor.close();
        db.close();

    }

}
