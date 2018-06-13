package com.cronlogy.charan.laalsa.Models;

public class OffersItemModel {
    private String title;
    private String imageUrl;
    private String description;
    private String promotionId;
    private String tag;

    public OffersItemModel() {
    }

    public OffersItemModel(String title, String imageUrl, String description, String promotionId, String tag) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.description = description;
        this.promotionId = promotionId;
        this.tag = tag;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
