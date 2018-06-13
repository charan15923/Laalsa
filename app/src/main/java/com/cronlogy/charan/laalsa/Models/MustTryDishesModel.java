package com.cronlogy.charan.laalsa.Models;

public class MustTryDishesModel {

    private String title;
    private String imageUrl;
    private String itemId;
    private String tag;
    private boolean veg;

    public MustTryDishesModel() {
    }


    public MustTryDishesModel(String title, String imageUrl, String itemId, String tag, boolean veg) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.itemId = itemId;
        this.tag = tag;
        this.veg = veg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isVeg() {
        return veg;
    }

    public void setVeg(boolean veg) {
        this.veg = veg;
    }
}
