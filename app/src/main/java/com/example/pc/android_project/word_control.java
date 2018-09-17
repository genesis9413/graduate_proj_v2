package com.example.pc.android_project;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class word_control extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<DriveVO> mydatas;

    Cursor cursor;
    TextView day_num;
    //all_btn, word_btn, mean_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_view);

        Bundle bundle = getIntent().getExtras();
        String numday = bundle.getString("numDay");

        mRecyclerView = (RecyclerView) findViewById(R.id.listview);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mydatas = new ArrayList<>();
        mAdapter = new DriveAdapter(mydatas);
        mRecyclerView.setAdapter(mAdapter);

        day_num = (TextView) findViewById(R.id.day_num);
        /*all_btn = (TextView) findViewById(R.id.all_btn);
        word_btn = (TextView) findViewById(R.id.word_btn);
        mean_btn = (TextView) findViewById(R.id.mean_btn);*/

        /** DAY NUM 들어갈 곳 */
        day_num.setText(numday);

        DBHelper helper = new DBHelper(this);

        SQLiteDatabase db = helper.getWritableDatabase();

        /** DBHelper.java 에서 불러온 DB의 SQL문 작성 */
        switch (numday) {
            case "DAY 1":
                cursor = db.rawQuery("SELECT word,mean FROM wordTB WHERE 0<_id AND _id<=10 ", null);
                break;
            case "DAY 2":
                cursor = db.rawQuery("SELECT word,mean FROM wordTB  WHERE 10<_id AND _id<=20", null);
                break;
            case "DAY 3":
                cursor = db.rawQuery("SELECT word,mean FROM wordTB  WHERE 20<_id AND _id<=30", null);
                break;
            case "DAY 4":
                cursor = db.rawQuery("SELECT word,mean FROM wordTB  WHERE 30<_id AND _id<=40", null);
                break;
            case "DAY 5":
                cursor = db.rawQuery("SELECT word,mean FROM wordTB  WHERE 40<_id AND _id<=50", null);
                break;
            default:
        }

        /** SQL문에서 출력된 값들을 하나씩 요소에 집어넣어 뿌릴 준비 */
        while (cursor.moveToNext()) {
            DriveVO vo = new DriveVO();
            vo.word = cursor.getString(0);
            vo.mean = cursor.getString(1);

            /** 출력 값들이 들어간 요소들을 ArrayList에 저장 */
            mydatas.add(vo);
        }

        db.close();
        helper.close();

    }

}





