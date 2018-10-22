package com.example.pc.android_project;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;

public class drama_control2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drama_view);

        final VideoView video = (VideoView) findViewById(R.id.video);
           ListView listView = (ListView) findViewById(R.id.list);
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        int drama[] = {R.raw.ghost,R.raw.cookie,R.raw.clean, R.raw.bug, R.raw.color, R.raw.snake};
        String sort[] = {"ghost","cookie","clean","bug","color","snake"};

        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("id");

        final MediaController mMediaController = new MediaController(this) {
            //for not hiding
            @Override
            public void hide() {
            }

            //for 'back' key action
            @Override
            public boolean dispatchKeyEvent(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                    Activity a = (Activity) getContext();
                    a.finish();
                }
                return true;
            }
        };

        mMediaController.setAnchorView(video);
        mMediaController.setMediaPlayer(video);
        video.setMediaController(mMediaController);
        mMediaController.requestFocus();

        //only this showed the controller for me!!
        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mMediaController.show(900000000);
            }
        });

        //finish after playing
        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                finish();
            }
        });

        video.stopPlayback();
        int ur = drama[id];
        String dia = sort[id];
        Log.v("dd",ur+"");
        String uriPath = "android.resource://" + getPackageName() + "/" + ur;
        Log.v("uri", uriPath + "");
        Uri uri = Uri.parse(uriPath);
        video.setVideoURI(uri);
        video.seekTo(100);
        video.requestFocus();
        video.stopPlayback();


        /** DBHelper.java 에서 불러온 DB의 SQL문 작성 */
        Cursor cursor = db.rawQuery("SELECT speaker, dialog , speaker2 FROM dialogTB WHERE sort = " +"'"+ dia +"'", null);

        ArrayList<DriveVO> datas = new ArrayList<>();

        /** SQL문에서 출력된 값들을 하나씩 요소에 집어넣어 ListView에 뿌릴 준비 */
        while (cursor.moveToNext()) {
            DriveVO vo = new DriveVO();
            vo.speaker = cursor.getString(1);
            vo.dialog = cursor.getString(2);
            datas.add(vo);
        }



        db.close();
        helper.close();

        /** 저장된 ArrayList를 ListView에 뿌리기 */
        DialogAdapter adapter = new DialogAdapter(this, R.layout.drama_item, datas);

            listView.setAdapter(adapter);
    }




}
