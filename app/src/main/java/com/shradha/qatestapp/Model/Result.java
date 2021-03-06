package com.shradha.qatestapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
    @SerializedName("q")
    @Expose
    private String q;
    @SerializedName("options")
    @Expose
    private List<String> options = null;
    @SerializedName("correctIndex")
    @Expose
    private Integer correctIndex;
    @SerializedName("correctResponse")
    @Expose
    private String correctResponse;
    @SerializedName("incorrectResponse")
    @Expose
    private String incorrectResponse;

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public Integer getCorrectIndex() {
        return correctIndex;
    }

    public void setCorrectIndex(Integer correctIndex) {
        this.correctIndex = correctIndex;
    }

    public String getCorrectResponse() {
        return correctResponse;
    }

    public void setCorrectResponse(String correctResponse) {
        this.correctResponse = correctResponse;
    }

    public String getIncorrectResponse() {
        return incorrectResponse;
    }

    public void setIncorrectResponse(String incorrectResponse) {
        this.incorrectResponse = incorrectResponse;
    }
}
