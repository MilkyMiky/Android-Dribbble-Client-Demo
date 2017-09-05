package com.miky.dev.dribbbleapp.data.db.entity;


import java.util.List;

public class Shot {

    private long id;
    private String title;
    private String description;
    private int width;
    private int height;
    private Image images;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Image getImages() {
        return images;
    }
}
