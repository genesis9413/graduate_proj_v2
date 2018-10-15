package com.example.pc.android_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.ImageViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class UserPage extends AppCompatActivity {

    Button word, script;
    ImageView userImg;
    TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_page);

        word = (Button)findViewById(R.id.word);
 //       script = (Button)findViewById(R.id.script);
        email = (TextView)findViewById(R.id.email);

        SharedPreferences mPref = getSharedPreferences("pref",MODE_PRIVATE);
        String callValue = mPref.getString("id","");

        email.setText(callValue);

    }

    public void word(View view) {
        Intent intent = new Intent(this, Review_word.class);
        startActivity(intent);


    }
    public void member(View view) {
        Intent intent = new Intent(this, Member.class);
        startActivity(intent);


    }



    public void logout(View view){
        finish();
        Intent intent = new Intent(this, Start.class);
        startActivity(intent);
    }


}