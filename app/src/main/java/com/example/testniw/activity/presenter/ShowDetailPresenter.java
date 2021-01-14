package com.example.testniw.activity.presenter;

import com.example.testniw.activity.view.ShowDetail;

public class ShowDetailPresenter {
    protected ShowDetail showDetail;

    public ShowDetailPresenter(ShowDetail showDetail) {
        this.showDetail = showDetail;
    }
    public void showDescription(){
        showDetail.showDescription();
    }
}
