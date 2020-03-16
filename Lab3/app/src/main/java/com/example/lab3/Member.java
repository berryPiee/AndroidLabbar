package com.example.lab3;

import com.google.gson.annotations.SerializedName;

public class Member {
    @SerializedName("namn")
    private String name = "Not assigned";
    @SerializedName("epost")
    private String email = "Not assigned";
    @SerializedName("svarade")
    private String answered = "Not assigned";

    public Member() {
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAnswered() {
        return answered;
    }
}
