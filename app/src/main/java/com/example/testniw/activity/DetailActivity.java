package com.example.testniw.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testniw.R;
import com.example.testniw.activity.presenter.ShowDetailPresenter;
import com.example.testniw.activity.view.ShowDetail;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity implements ShowDetail {
    private ImageView imgAvatar;
    private TextView tvType;
    private TextView tvDes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();
        ShowDetailPresenter showDetailPresenter=new ShowDetailPresenter(this);
        showDetailPresenter.showDescription();

    }
    private void init(){
        imgAvatar = (ImageView) findViewById(R.id.imgAvatar);
        tvType = (TextView) findViewById(R.id.tvType);
        tvDes = (TextView) findViewById(R.id.tvDes);

    }

    @Override
    public void showDescription() {
        Intent intent=getIntent();
        Bundle bundle= intent.getBundleExtra("data");
        String avatar=bundle.getString("avatar");
        String type=bundle.getString("type");
        String description=bundle.getString("des");
        tvDes.setText(description);
        tvType.setText(type);
        Picasso.get().load(avatar).into(imgAvatar);
    }
}