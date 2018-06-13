package com.cronlogy.charan.laalsa.Models;

public class ExploreFoodCollectionModel {

    private String title;
    private String imgUrl;

    public ExploreFoodCollectionModel(String title, String imgUrl) {
        this.title = title;
        this.imgUrl = imgUrl;
    }

    public ExploreFoodCollectionModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
