package com.example.testniw.activity.hoder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testniw.R;

public class RealmHoder extends RecyclerView.ViewHolder {
    public TextView tvStory;
    public RealmHoder(@NonNull View itemView) {
        super(itemView);
        tvStory=itemView.findViewById(R.id.tvStory);
    }
}
