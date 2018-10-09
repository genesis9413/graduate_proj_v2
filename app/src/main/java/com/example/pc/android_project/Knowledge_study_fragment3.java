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

public class Knowledge_study_fragment3 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.knowledge_study_fragment3, container, false);

        String flag = getResources().getString(R.string.flag);
        TextView sharon = (TextView)v.findViewById(R.id.flag);
        SpannableStringBuilder ssb = new SpannableStringBuilder(flag);
        ssb.setSpan(new ForegroundColorSpan(Color.parseColor("#A901DB")),0,3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.setSpan(new ForegroundColorSpan(Color.parseColor("#A901DB")),20,23, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.setSpan(new StyleSpan(Typeface.BOLD),0,3,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.setSpan(new StyleSpan(Typeface.BOLD),20,23,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        sharon.setText(ssb);
        return v;
    }

}
