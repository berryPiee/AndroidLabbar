package com.example.lab3;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String groupName = "empty";
    private List<Member> members;

    public Group(String groupName, List<Member> members) {
        this.groupName = groupName;
        this.members = members;
    }

    public void setGroupName(String groupName){
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public void resetMemebers(){
        members = new ArrayList<>();
    }

    public List<Member> getMembers() {
        return members;
    }
}
