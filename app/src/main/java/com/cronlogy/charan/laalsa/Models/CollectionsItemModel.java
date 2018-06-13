package com.cronlogy.charan.laalsa.Models;

public class CollectionsItemModel {

    private String title;
    private String description;
    private String imageUrl;
    private String availability;

    public CollectionsItemModel() {
    }

    public CollectionsItemModel(String title, String description, String imageUrl, String availability) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.availability = availability;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}

