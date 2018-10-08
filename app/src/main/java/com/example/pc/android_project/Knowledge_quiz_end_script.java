package com.example.pc.android_project;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class Knowledge_quiz_end_script extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<DriveVO> mydatas;

    Cursor cursor;
    Intent intent;
    String[] anArr = null;
    String[] wrArr;
    int anCnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.knowledge_quiz_end);


        Bundle bundle = getIntent().getExtras();
        anArr = bundle.getStringArray("anArr");
        wrArr = bundle.getStringArray("wrArr");
        anCnt = bundle.getInt("anCnt");
        Log.v("dd",""+anCnt);

        TextView count = (TextView)findViewById(R.id.count);
        String str = String.valueOf(anCnt);
        count.setText(str);

        mRecyclerView = (RecyclerView) findViewById(R.id.listview);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mydatas = new ArrayList<>();
        mAdapter = new ScriptAdapter(mydatas);
        mRecyclerView.setAdapter(mAdapter);

        DBHelper helper = new DBHelper(this);

        SQLiteDatabase db = helper.getWritableDatabase();



            for(int i=0; i<5; i++){
                cursor = db.rawQuery("SELECT question, answer FROM scriptTB WHERE _id ==" + wrArr[i], null);
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



}