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

import org.w3c.dom.Text;

public class Knowledge_study_fragment2 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.knowledge_study_fragment2, container, false);

        String roseofsharon = getResources().getString(R.string.roseofsharon);
        TextView sharon = (TextView)v.findViewById(R.id.sharon);
        SpannableStringBuilder ssb = new SpannableStringBuilder(roseofsharon);
        ssb.setSpan(new ForegroundColorSpan(Color.parseColor("#FF00FF")),0,3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.setSpan(new ForegroundColorSpan(Color.parseColor("#FF00FF")),42,45, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.setSpan(new StyleSpan(Typeface.BOLD),0,3,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.setSpan(new StyleSpan(Typeface.BOLD),42,45,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        sharon.setText(ssb);
        return  v;
    }

}
