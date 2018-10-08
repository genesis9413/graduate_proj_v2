package com.example.pc.android_project;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ScriptAdapter extends RecyclerView.Adapter<ScriptAdapter.ViewHolder> {
    ArrayList<DriveVO> mdatas;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView wordView;
        public TextView meanView;

        public ViewHolder(View view) {
            super(view);
            wordView = (TextView) view.findViewById(R.id.tv_word);
            meanView = (TextView) view.findViewById(R.id.tv_mean);
        }
    }

    public ScriptAdapter(ArrayList<DriveVO> mydatas) {
        mdatas = mydatas;
    }

    @Override
    public ScriptAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.script_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.wordView.setText(mdatas.get(position).word);
        holder.meanView.setText(mdatas.get(position).mean);
    }

    @Override
    public int getItemCount() {
        return mdatas.size();

    }
}
