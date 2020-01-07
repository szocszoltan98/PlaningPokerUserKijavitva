package com.example.user;

public class AnswerItem {
    public String name;
    public String anwer;
    public String groupId;
    public String question;

    public AnswerItem(String name, String anwer, String groupId, String question) {
        this.name = name;
        this.anwer = anwer;
        this.groupId = groupId;
        this.question = question;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnwer() {
        return anwer;
    }

    public void setAnwer(String anwer) {
        this.anwer = anwer;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}


