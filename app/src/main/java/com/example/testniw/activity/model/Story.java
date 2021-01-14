package com.example.testniw.activity.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmField;

public class Story extends RealmObject {

    @PrimaryKey
    private String name;

    public String  getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Story(String name) {
        this.name=name;
    }

    public Story() {
    }
}
