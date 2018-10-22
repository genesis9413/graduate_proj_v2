package com.example.pc.android_project;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class script_control extends AppCompatActivity implements TextToSpeech.OnInitListener {

    TextView question, answer, menu;
    Button btnNext;
    ImageView a_record, q_record;

    DBHelper helper = new DBHelper(this);
    Cursor cursor;
    SQLiteDatabase db;

    private TextToSpeech tts;
    String qu = "";
    String an = "";

    Integer a = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.script_view);

        question = (TextView) findViewById(R.id.question);
        answer = (TextView) findViewById(R.id.answer);
        menu = (TextView) findViewById(R.id.menu);

        btnNext = (Button) findViewById(R.id.btnNext);
        a_record = (ImageView) findViewById(R.id.a_record);
        q_record = (ImageView) findViewById(R.id.q_record);

        tts = new TextToSpeech(this, this);

        Bundle bundle = getIntent().getExtras();
        final String ary_name = bundle.getString("menu_name");

        menu.setText(ary_name);

        db = helper.getWritableDatabase();

        cursor = db.rawQuery("SELECT _id, question, answer FROM scriptTB WHERE sort == '" + ary_name + "'", null);

        cursor.moveToLast();
        final int clength = cursor.getCount();
        cursor.moveToFirst();

        final String[] id_arry = new String[clength];

        for (int i = 0; i < clength; i++) {
            id_arry[i] = cursor.getString(0);
            cursor.moveToNext();
        }

        cursor.moveToFirst();

        while (cursor.moveToNext()) {
            qu = cursor.getString(1);
            an = cursor.getString(2);
        }
        question.setText(qu);
        answer.setText(an);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursor = db.rawQuery("SELECT _id, question, answer FROM scriptTB WHERE sort == '" + ary_name + "' AND _id ==" + id_arry[a], null);

                while (cursor.moveToNext()) {
                    qu = cursor.getString(1);
                    an = cursor.getString(2);
                }
                question.setText(qu);
                answer.setText(an);

                ++a;

                if (a >= clength)
                    a = 0;

            }
        });

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