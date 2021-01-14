package com.example.testniw.activity.hoder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testniw.R;

public class DataHoder extends RecyclerView.ViewHolder {
    public TextView tvName;
    public TextView tvFullname;

    public DataHoder(@NonNull View itemView) {
        super(itemView);


        tvName = (TextView) itemView.findViewById(R.id.tvName);
        tvFullname = (TextView) itemView.findViewById(R.id.tvFullname);

    }
}
