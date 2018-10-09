package com.example.pc.android_project;

import android.app.Dialog;
import android.view.View;
import android.widget.TextView;

public class DriverHolder {
    public TextView speakerView;
    public TextView dialogView;

    public DriverHolder(View root){
        speakerView = (TextView) root.findViewById(R.id.tv_speaker);
        dialogView = (TextView) root.findViewById(R.id.tv_dialog);

    }
}
