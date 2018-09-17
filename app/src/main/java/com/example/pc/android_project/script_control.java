package com.example.pc.android_project;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class script_control extends AppCompatActivity implements TextToSpeech.OnInitListener {

    TextView question, answer;
    Button btnNext, a_record, q_record;

    DBHelper helper = new DBHelper(this);
    Cursor cursor;
    SQLiteDatabase db;

    private TextToSpeech tts;
    String qu = "";
    String an = "";

    /**
     * DB 테이블 id 증가시켜주는 변수
     */
    Integer i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.script_view);

        question = (TextView) findViewById(R.id.question);
        answer = (TextView) findViewById(R.id.answer);

        btnNext = (Button) findViewById(R.id.btnNext);
        a_record = (Button) findViewById(R.id.a_record);
        q_record = (Button) findViewById(R.id.q_record);

        tts = new TextToSpeech(this, this);

        Bundle bundle = getIntent().getExtras();
        Integer num = bundle.getInt("list_menu");

        db = helper.getWritableDatabase();

        /** 리스트 <1. 인사>를 선택했을 경우 */
        if (num == 0) {
            cursor = db.rawQuery("SELECT question, answer FROM script_01TB WHERE _id == 1", null);
            while (cursor.moveToNext()) {
                qu = cursor.getString(0);
                an = cursor.getString(1);
            }
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db = helper.getWritableDatabase();
                    ++i;
                    if (i > 4)
                        i = 1;
                    cursor = db.rawQuery("SELECT question, answer FROM script_01TB WHERE _id == " + i, null);
                    while (cursor.moveToNext()) {
                        qu = cursor.getString(0);
                        an = cursor.getString(1);
                    }
                    question.setText(qu);
                    answer.setText(an);

                    cursor.close();
                    db.close();
                }
            });

            /** 리스트 <2. 대중교통>을 선택했을 경우 */
        } else if (num == 1) {
            cursor = db.rawQuery("SELECT question, answer FROM script_02TB WHERE _id == 1", null);
            while (cursor.moveToNext()) {
                qu = cursor.getString(0);
                an = cursor.getString(1);
            }
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db = helper.getWritableDatabase();
                    ++i;
                    if (i > 4)
                        i = 1;
                    cursor = db.rawQuery("SELECT question, answer FROM script_02TB WHERE _id == " + i, null);
                    while (cursor.moveToNext()) {
                        qu = cursor.getString(0);
                        an = cursor.getString(1);
                    }
                    question.setText(qu);
                    answer.setText(an);

                    cursor.close();
                    db.close();
                }
            });
            /** 리스트 <3. 마트(시장)>을 선택했을 경우 */
        } else {
            cursor = db.rawQuery("SELECT question, answer FROM script_03TB WHERE _id == 1", null);
            while (cursor.moveToNext()) {
                qu = cursor.getString(0);
                an = cursor.getString(1);
            }
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db = helper.getWritableDatabase();
                    ++i;
                    if (i > 2)
                        i = 1;
                    cursor = db.rawQuery("SELECT question, answer FROM script_03TB WHERE _id == " + i, null);
                    while (cursor.moveToNext()) {
                        qu = cursor.getString(0);
                        an = cursor.getString(1);
                    }
                    question.setText(qu);
                    answer.setText(an);

                    cursor.close();
                    db.close();
                }
            });
        }

        /** 첫 대화화면 출력 */
        question.setText(qu);
        answer.setText(an);

        cursor.close();
        db.close();

        q_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q_speakOut();
            }
        });

        a_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a_speakOut();
            }
        });

    }

    /**
     * TTS 부분
     */
    @Override
    public void onDestroy() {
        // Don't forget to shutdown!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {
        // TODO Auto-generated method stub

        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.KOREA);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "Language is not supported");
            } else {
                a_record.setEnabled(true);
                q_record.setEnabled(true);
                a_speakOut();
                q_speakOut();
            }

        } else {
            Log.e("TTS", "Initilization Failed");
        }

    }

    private void a_speakOut() {
        String a_text = answer.getText().toString();

        tts.speak(a_text, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    private void q_speakOut() {
        String q_text = question.getText().toString();

        tts.speak(q_text, TextToSpeech.QUEUE_FLUSH, null, null);

    }
}