package com.humorboy.mvpapplication.mvp.welcome.model;

public class WelcomeBean {
    private String text;
    private String img;

    public WelcomeBean(String text, String img) {
        this.text = text;
        this.img = img;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
