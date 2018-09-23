package com.example.pc.android_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    EditText etEmail, etPw;
//    String email = "zz@zz";
//    String password = "zzzz";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void signin(final View view) throws JSONException {
//        String url = "https://hunminjeongeum.ga/login";
        String url = "http://43.224.34.55/login";
//        String url = "http://10.0.2.2:3000/test";
//        String url = "http://hunminjeongeum.ga/test";

        etEmail = (EditText)findViewById(R.id.etEmail);
        etPw = (EditText)findViewById(R.id.etPw);

        JSONObject userData = new JSONObject();
        userData.put("userid",etEmail.getText().toString());
        userData.put("password",etPw.getText().toString());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,userData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("json", response.toString());
                try {
                    SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("token", response.getString("token"));
                    editor.apply();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Intent i = new Intent(Login.this, MainActivity.class);
                startActivity(i);
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
/*
        if(email.equals(etEmail.getText().toString())&& password.equals(etPw.getText().toString())) {
            Toast.makeText(getApplicationContext(),"로그인성공", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }else{
            Toast.makeText(getApplicationContext(),"로그인실패", Toast.LENGTH_LONG).show();
        }
        */
    }


}