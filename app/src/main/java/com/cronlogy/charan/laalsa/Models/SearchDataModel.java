package com.cronlogy.charan.laalsa.Models;

public class SearchDataModel {

    private String restId;
    private String name;
    private String mmId;
    private String image;
    private String image_thumb;
    private boolean driveInInd;
    private int deliveryTime;
    private String popularItems;
    private boolean closed;
    private int nearYou;
    private int totalRestaurants;
    private String type;

    public SearchDataModel() {
    }

    public SearchDataModel(String restId, String name, String mmId, String image,
                           String image_thumb, boolean driveInInd, int deliveryTime,
                           String popularItems, boolean closed, int nearYou, int totalRestaurants, String type) {
        this.restId = restId;
        this.name = name;
        this.mmId = mmId;
        this.image = image;
        this.image_thumb = image_thumb;
        this.driveInInd = driveInInd;
        this.deliveryTime = deliveryTime;
        this.popularItems = popularItems;
        this.closed = closed;
        this.nearYou = nearYou;
        this.totalRestaurants = totalRestaurants;
        this.type = type;
    }

    public String getRestId() {
        return restId;
    }

    public void setRestId(String restId) {
        this.restId = restId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMmId() {
        return mmId;
    }

    public void setMmId(String mmId) {
        this.mmId = mmId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage_thumb() {
        return image_thumb;
    }

    public void setImage_thumb(String image_thumb) {
        this.image_thumb = image_thumb;
    }

    public boolean isDriveInInd() {
        return driveInInd;
    }

    public void setDriveInInd(boolean driveInInd) {
        this.driveInInd = driveInInd;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getPopularItems() {
        return popularItems;
    }

    public void setPopularItems(String popularItems) {
        this.popularItems = popularItems;
    }

    public boolean getClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public int getNearYou() {
        return nearYou;
    }

    public void setNearYou(int nearYou) {
        this.nearYou = nearYou;
    }

    public int getTotalRestaurants() {
        return totalRestaurants;
    }

    public void setTotalRestaurants(int totalRestaurants) {
        this.totalRestaurants = totalRestaurants;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
