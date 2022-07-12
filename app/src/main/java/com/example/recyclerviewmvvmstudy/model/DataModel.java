package com.example.recyclerviewmvvmstudy.model;

import android.graphics.drawable.Drawable;

public class DataModel implements Comparable<DataModel> {
    public String name;
    public String value;
    public Drawable icon;
    public long time;

    public DataModel(String name, String value, Drawable icon, long time) {
        this.name = name;
        this.value = value;
        this.icon = icon;
        this.time = time;
    }

    @Override
    public String toString() {
        return "DataModel{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", icon=" + icon +
                '}';
    }

    @Override
    public int compareTo(DataModel o) {
        return -(int)(this.time - o.time);
    }
}
