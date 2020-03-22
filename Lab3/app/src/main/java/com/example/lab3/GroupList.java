package com.example.lab3;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

class GroupList {
    @SerializedName("grupper")
    private List<String> grupper = new ArrayList<>();

    GroupList() {
    }

    List<String> getGrupper() {
        return grupper;
    }

    void addGroup(String group) {
        grupper.add(group);
    }
}
