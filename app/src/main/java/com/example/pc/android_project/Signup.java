package com.example.pc.android_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Signup extends AppCompatActivity {

    EditText etEmail, etPw;

    String email, pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

//        etEmail = (EditText)findViewById(R.id.etEmail);
//        etPw = (EditText)findViewById(R.id.etPw);
//
//        email = etEmail.getText().toString();
//        pw = etPw.getText().toString();

    }

    public void signup(final View view) throws JSONException {
        String url = "http://43.224.34.55/register";

        etEmail = (EditText)findViewById(R.id.etEmail);
        etPw = (EditText)findViewById(R.id.etPw);

        JSONObject userData = new JSONObject();
        userData.put("userid",etEmail.getText().toString());
        userData.put("password",etPw.getText().toString());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,userData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("json", response.toString());
                String result;
                try {
                    result= (String) response.get("result");
                    if(result.equals("ok")){
                        Intent i = new Intent(Signup.this, Login.class);
                        startActivity(i);
                    }else{
                        Snackbar.make(view,"회원가입 실패",Snackbar.LENGTH_SHORT).show();
                    }
                    Intent i = new Intent(Signup.this, Login.class);
                    startActivity(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volly Error", error.toString());
                Snackbar.make(view,"로그인 실패",Snackbar.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);
    }


}