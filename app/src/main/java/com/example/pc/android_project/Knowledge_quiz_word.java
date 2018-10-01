package com.example.pc.android_project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Knowledge_quiz_word extends Fragment {
    TextView quizTxt;
    Button answer, wrong;
    String qu = "";
    String an = "";
    String wr = "";
    Integer i = 1;
    DBHelper helper = new DBHelper(getActivity());
    Cursor cursor;
    SQLiteDatabase db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.knowledge_quiz, container, false);

        quizTxt = (TextView)v.findViewById(R.id.quizTxt);
        answer = (Button)v.findViewById(R.id.answer);
        wrong = (Button)v.findViewById(R.id.wrong);


        db = helper.getWritableDatabase();
        cursor = db.rawQuery("SELECT mean, word FROM wordTB order by random()", null);

        while (cursor.moveToNext()) {
            qu = cursor.getString(0);
            an = cursor.getString(1);
        }

        cursor = db.rawQuery("SELECT word FROM wordTB order by random()", null);
        while (cursor.moveToNext()) {
            wr = cursor.getString(0);
        }

        if( answer == wrong)
        {
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

        return v;
    }

    void show_an() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("정답입니다.");
        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        builder.show();
    }
    void show_wr() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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

        cursor = db.rawQuery("SELECT mean, word FROM wordTB order by random()", null);
        while (cursor.moveToNext()) {
            qu = cursor.getString(0);
            an = cursor.getString(1);
        }

        cursor = db.rawQuery("SELECT word FROM wordTB order by random()", null);
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