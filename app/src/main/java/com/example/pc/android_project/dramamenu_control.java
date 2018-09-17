package com.example.pc.android_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class dramamenu_control extends AppCompatActivity {

    ListView drama_listview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drama_menu);

        final String[] ary = {"1 미생", "2 ", "3 "};

        drama_listview = (ListView) findViewById(R.id.drama_listview);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ary);
        drama_listview.setAdapter(adapter);

        drama_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                /** int i 는 클릭한 항목의 순번
                 * long l 는 항목의 아이디  */

                Intent intent = new Intent(getApplicationContext(), drama_control.class);

                intent.putExtra("list_menu", i);

                startActivity(intent);

            }
        });

    }
}
