package com.codex.namazdiary.counterapp;

public class CounterDetails {
    private String note, counts;
    private int userId;
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getNote() {

        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public String getCounts() {
        return counts;
    }
    public void setCounts(String counts) {
        this.counts = counts;
    }
}
