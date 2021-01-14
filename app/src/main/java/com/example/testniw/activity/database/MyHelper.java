package com.example.testniw.activity.database;


import com.example.testniw.activity.model.Example;
import com.example.testniw.activity.model.Story;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class MyHelper {
    Realm realm;
    RealmResults<Story> stories;

    public MyHelper(Realm realm) {
        this.realm = realm;
    }

    public RealmResults<Story> selectFromDB(){

        stories=realm.where(Story.class).findAll();
        return stories;
    }
    public List<Story> getAll(){
        List<Story> storyList=new ArrayList<>();
        for (Story story: stories){
            storyList.add(story);
        }
        return storyList;
    }
}
