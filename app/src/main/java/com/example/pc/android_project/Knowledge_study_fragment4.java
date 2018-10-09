package com.example.pc.android_project;

import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.Image;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Knowledge_study_fragment4 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.knowledge_study_fragment4, container, false);

        String sing = getResources().getString(R.string.song);
        TextView song = (TextView)v.findViewById(R.id.song);
        ImageButton musicon = (ImageButton)v.findViewById(R.id.musicon);

        final SoundPool sp = new SoundPool(1,         // 최대 음악파일의 개수
                AudioManager.STREAM_MUSIC, // 스트림 타입
                0);
        final int soundID = sp.load(getContext(), // 현재 화면의 제어권자
                R.raw.song,    // 음악 파일
                1);        // 우선순위
        musicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.play(soundID, // 준비한 soundID
                        1,         // 왼쪽 볼륨 float 0.0(작은소리)~1.0(큰소리)
                        1,         // 오른쪽볼륨 float
                        0,         // 우선순위 int
                        0,     // 반복회수 int -1:무한반복, 0:반복안함
                        1f);    // 재생속도 float 0.5(절반속도)~2.0(2배속)
                // 음악 재생
            }
        });

        SpannableStringBuilder ssb = new SpannableStringBuilder(sing);
        ssb.setSpan(new ForegroundColorSpan(Color.parseColor("#B40431")),0,3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.setSpan(new ForegroundColorSpan(Color.parseColor("#B40431")),20,23, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.setSpan(new StyleSpan(Typeface.BOLD),0,3,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.setSpan(new StyleSpan(Typeface.BOLD),20,23,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        song.setText(ssb);
        return v;
    }

}
