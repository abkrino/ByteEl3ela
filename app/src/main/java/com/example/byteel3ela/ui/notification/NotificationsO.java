package com.example.byteel3ela.ui.notification;

public class NotificationsO {
    String title;
    String body;
    String time;

    public NotificationsO(String title, String body, String time) {
        this.title = title;
        this.body = body;
        this.time = time;
    }

    public NotificationsO() {
    }

    public NotificationsO(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
