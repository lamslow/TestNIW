package com.example.testniw.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.testniw.R;
import com.example.testniw.activity.hoder.RealmHoder;
import com.example.testniw.activity.model.Story;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RealmAdapter extends RecyclerView.Adapter<RealmHoder> {
    Context context;
    List<Story> list;

    public RealmAdapter(Context context, List<Story> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RealmHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.realm_item, parent, false);
        RealmHoder realmHoder=new RealmHoder(view);
        return realmHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull RealmHoder holder, int position) {
      holder.tvStory.setText(list.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
