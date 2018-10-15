package com.example.pc.android_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;

public class Review_word extends AppCompatActivity {

    Button word;
    String[] wrArr;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<DriveVO> mydatas;
    Cursor cursor;
    TextView wrongtxt;
    int wrCnt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_word);

        saveShared();


        mRecyclerView = (RecyclerView) findViewById(R.id.listview);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mydatas = new ArrayList<>();
        mAdapter = new DriveAdapter(mydatas);
        mRecyclerView.setAdapter(mAdapter);

        DBHelper helper = new DBHelper(this);

        SQLiteDatabase db = helper.getWritableDatabase();

        wrArr = new HashSet<String>(Arrays.asList(wrArr)).toArray(new String[0]);

            for (int i = 0; i < wrArr.length; i++) {

                    cursor = db.rawQuery("SELECT word,mean FROM wordTB WHERE _id ==" + wrArr[i], null);
                    while (cursor.moveToNext()) {
                        DriveVO vo = new DriveVO();
                        vo.word = cursor.getString(0);
                        vo.mean = cursor.getString(1);

                        /** 출력 값들이 들어간 요소들을 ArrayList에 저장 */
                        mydatas.add(vo);
                    }
                }




            db.close();
            helper.close();




    }

    public void saveShared(){
        SharedPreferences prefs = getSharedPreferences("pref", MODE_PRIVATE);
        int size = prefs.getInt("Status_size", 0);
        wrCnt = prefs.getInt("num",0);
        wrArr = new String[wrCnt];
        for(int i=0;i<size;i++) {


            wrArr[i] = (prefs.getString("Status_" + i,""));

            Log.v("wrArr", ""+ Arrays.toString(wrArr));
            Log.v("wrArrll", ""+ wrArr.length);
            Log.v("wrArrll", ""+ wrCnt);
        }
    }
}