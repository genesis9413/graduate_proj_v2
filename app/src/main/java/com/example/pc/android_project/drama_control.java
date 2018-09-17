package com.example.pc.android_project;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;

public class drama_control extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drama_view);

        final VideoView video = (VideoView) findViewById(R.id.video);
        ListView listView = (ListView) findViewById(R.id.list);
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

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
        String uriPath = "android.resource://" + getPackageName() + "/" + R.raw.tayo;
        Uri uri = Uri.parse(uriPath);
        video.setVideoURI(uri);
        video.seekTo(100);
        video.requestFocus();
        video.stopPlayback();


        Intent intent = getIntent();
        Integer ary = intent.getIntExtra("list_menu", 0);


        /** DBHelper.java 에서 불러온 DB의 SQL문 작성 */
        Cursor cursor = db.rawQuery("SELECT * FROM dialog_01TB", null);

        ArrayList<DriveVO> datas = new ArrayList<>();

        /** SQL문에서 출력된 값들을 하나씩 요소에 집어넣어 ListView에 뿌릴 준비 */
        while (cursor.moveToNext()) {
            DriveVO vo = new DriveVO();

            /** 출력 값들이 들어간 요소들을 ArrayList에 저장 */
            datas.add(vo);
        }

        db.close();
        helper.close();

        /** 저장된 ArrayList를 ListView에 뿌리기 */
        DialogAdapter adapter = new DialogAdapter(this, R.layout.dialog_item, datas);

        listView.setAdapter(adapter);
    }


}
