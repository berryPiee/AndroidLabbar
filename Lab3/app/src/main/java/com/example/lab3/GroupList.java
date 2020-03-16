package com.example.lab3;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GroupList {
    @SerializedName("grupper")
    private List<String> grupper = new ArrayList<>();

    public GroupList() {
    }

    public List<String> getGrupper() {
        return grupper;
    }

    public void addGroup(String group) {
        grupper.add(group);
    }
}
