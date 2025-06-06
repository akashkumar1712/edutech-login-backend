package com.SpringBootProject.LoginSignUp.dto.request;

import java.util.List;

public class QuestionRequest {
    private int id;
    private String text;
    private List<String> options;
    private String answer; // ✅ NEW FIELD

    public QuestionRequest() {}

    public QuestionRequest(int id, String text, List<String> options, String answer) {
        this.id = id;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
