package com.example.testniw.activity.presenter;

import com.example.testniw.activity.view.LoadDataView;

public class LoadDataPresenter {
    private LoadDataView loadDataView;
    private int page;

    public LoadDataPresenter(LoadDataView loadDataView,int page) {
        this.loadDataView = loadDataView;
        this.page = page;
    }

    public void loadData() {
        loadDataView.loadData(page);
    }
    public void showData(){
        loadDataView.showData();
    }
}
