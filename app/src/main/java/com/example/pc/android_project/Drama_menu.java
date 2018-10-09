package com.example.pc.android_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Drama_menu extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drama_menu);
        //recyclerview 참조
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        /* LayoutManager 생성 : 수직 레이아웃으로 배치할 수 있도록 LinearLayoutManager로 설정
           - ListView에서는 아이템 목록을 수직으로만 배치할 수 있지만
             RecyclerView에서는 LayoutManager를 이용해 아이템을 다양한 방법으로 배치할 수 있음.
             (GridLayoutManager)
         */
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);//RecyclerView 크기를 고정

        //recyclerView에 layoutManager 설정
        recyclerView.setLayoutManager(layoutManager);

        //데이터 설정(ArrayList)
        List<Drama_item> items = new ArrayList<>();
        Drama_item[] item = new Drama_item[6];
        item[0] = new Drama_item(R.drawable.ghost, "유령소동");
        item[1] = new Drama_item(R.drawable.cookie, "행운의 쿠기");
        item[2] = new Drama_item(R.drawable.clean, "깔끔쟁이 캡");
        item[3] = new Drama_item(R.drawable.bug, "폴리는 벌레가 무서워");
        item[4] = new Drama_item(R.drawable.color, "구조대 색칠하기");
        item[5] = new Drama_item(R.drawable.snake, "으악! 뱀이다!");

        //RecyclerView의 아이템(items)에 들어갈 데이터(item) 추가
        for (int i = 0; i < item.length; i++) {
            items.add(item[i]);
        }



        //recyclerView에 어댑터 설정
        recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(), items, R.layout.drama_card));

    }


}