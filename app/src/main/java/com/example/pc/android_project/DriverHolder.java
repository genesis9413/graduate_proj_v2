package com.example.pc.android_project;

import android.app.Dialog;
import android.view.View;
import android.widget.TextView;

public class DriverHolder {
    public TextView dialogView;

    public DriverHolder(View root){
        dialogView = (TextView) root.findViewById(R.id.tv_dialog);

    }
}
