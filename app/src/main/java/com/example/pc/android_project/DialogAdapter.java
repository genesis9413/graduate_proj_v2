package com.example.pc.android_project;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DialogAdapter extends ArrayAdapter<DriveVO> {
    Context context;
    int resId;
    ArrayList<DriveVO> datas;

    public DialogAdapter(Context context, int resId, ArrayList<DriveVO> datas){
        super(context, resId);
        this.context = context;
        this.resId = resId;
        this.datas = datas;
    }

    @Override
    public int getCount(){
        return datas.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resId, null);

            DriverHolder holder = new DriverHolder(convertView);
            convertView.setTag(holder);
        }

        DriverHolder holder = (DriverHolder) convertView.getTag();

        /** 'item.xml'에 있는 요소들의 id 불러와 저장하는 과정 */
        TextView speakerView = holder.speakerView;
        TextView dialogView = holder.dialogView;

        final DriveVO vo = datas.get(position);

        speakerView.setText(vo.speaker);
        dialogView.setText(vo.dialog);

        return convertView;
    }
}
