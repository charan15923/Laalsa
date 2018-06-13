package com.cronlogy.charan.laalsa.Models;

public class ExploreFoodNormalModel {

    private String title;
    private String price;
    private String imgUrl;


    public ExploreFoodNormalModel(String title, String price, String imgUrl) {
        this.title = title;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public ExploreFoodNormalModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
