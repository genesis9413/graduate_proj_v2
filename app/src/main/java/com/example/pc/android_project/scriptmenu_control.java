package com.example.pc.android_project;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class scriptmenu_control extends AppCompatActivity {

    ListView script_listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.script_menu);


        //인사, 교통, 시장, 일상, 날씨, 도서관, 옷가게

        script_listview = (ListView) findViewById(R.id.script_listview);

        DBHelper helper = new DBHelper(this);

        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT distinct sort FROM scriptTB;", null);

        cursor.moveToLast();
        int clength = cursor.getCount();
        cursor.moveToFirst();

        final String[] arry = new String[clength];

        for(int i=0; i<clength; i++){
            arry[i] = cursor.getString(0);
            cursor.moveToNext();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arry);
        script_listview.setAdapter(adapter);

        script_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                /** int i 는 클릭한 항목의 순번
                 * long l 는 항목의 아이디  */

                String menu_name = arry[i].toString();
                Intent intent = new Intent(getApplicationContext(), script_control.class);

                intent.putExtra("menu_name",menu_name);

                startActivity(intent);

            }
        });

    }
}
