package com.example.testniw.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testniw.R;
import com.example.testniw.activity.DetailActivity;
import com.example.testniw.activity.hoder.DataHoder;
import com.example.testniw.activity.model.Example;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataHoder> {
    Context context;
    List<Example> list;

    public DataAdapter(Context context, List<Example> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DataHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_data, parent, false);
        DataHoder dataHoder = new DataHoder(view);
        return dataHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull DataHoder holder, int position) {
        holder.tvName.setText(list.get(position).getName());
        holder.tvFullname.setText(list.get(position).getFullName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String avatar=list.get(position).getOwner().getAvatarUrl();
                String type=list.get(position).getOwner().getType();
                String des=list.get(position).getDescription();
                Bundle bundle=new Bundle();
                bundle.putString("avatar",avatar);
                Log.e("Â",avatar+"");
                bundle.putString("type",type);
                bundle.putString("des",des);
                Intent intent=new Intent(context, DetailActivity.class);
                intent.putExtra("data",bundle);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateList(List<Example> exampleList){
        list=new ArrayList<>();
        list.addAll(exampleList);
        notifyDataSetChanged();
    }
}
