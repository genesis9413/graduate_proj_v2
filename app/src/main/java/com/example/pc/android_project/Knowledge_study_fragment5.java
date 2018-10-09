package com.example.pc.android_project;

import android.graphics.Color;
import android.graphics.Typeface;
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
import android.widget.TextView;

public class Knowledge_study_fragment5 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.knowledge_study_fragment5, container, false);

        String korea = getResources().getString(R.string.korea);
        TextView song = (TextView)v.findViewById(R.id.korea);
        SpannableStringBuilder ssb = new SpannableStringBuilder(korea);
        ssb.setSpan(new ForegroundColorSpan(Color.parseColor("#B40431")),0,3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
     //   ssb.setSpan(new ForegroundColorSpan(Color.parseColor("#B40431")),20,23, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.setSpan(new StyleSpan(Typeface.BOLD),0,3,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    //    ssb.setSpan(new StyleSpan(Typeface.BOLD),20,23,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        song.setText(ssb);
        return v;
    }

}
