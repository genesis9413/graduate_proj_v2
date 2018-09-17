package com.example.pc.android_project;

import android.content.Intent;
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

        final String[] ary = {"1 인사", "2 대중교통", "3 마트(시장)"};

        script_listview = (ListView) findViewById(R.id.script_listview);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ary);
        script_listview.setAdapter(adapter);

        script_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                /** int i 는 클릭한 항목의 순번
                 * long l 는 항목의 아이디  */

                Integer list_ary = i;
                Intent intent = new Intent(getApplicationContext(), script_control.class);

                intent.putExtra("list_menu", list_ary);

                startActivity(intent);

            }
        });

    }
}
