package com.cronlogy.charan.laalsa.Models;

public class NotificationCardModel {
    private String sectionID;
    private String notificationTitle;
    private String description;
    private String imageUrl;


    public NotificationCardModel() {
    }

    public NotificationCardModel(String sectionID, String notificationTitle, String description, String imageUrl) {
        this.sectionID = sectionID;
        this.notificationTitle = notificationTitle;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getSectionID() {
        return sectionID;
    }

    public void setSectionID(String sectionID) {
        this.sectionID = sectionID;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
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
}
