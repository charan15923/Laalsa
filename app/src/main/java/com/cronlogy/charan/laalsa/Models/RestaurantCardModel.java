package com.cronlogy.charan.laalsa.Models;

import java.util.ArrayList;

public class RestaurantCardModel {
    private String sectionID;
    private String cardTitle;
    private String description;
    private ArrayList<RestaurantItemModel> restaurantItemList;

    public RestaurantCardModel() {
    }

    public RestaurantCardModel(String sectionID, String cardTitle, String description, ArrayList<RestaurantItemModel> restaurantItemList) {
        this.sectionID = sectionID;
        this.cardTitle = cardTitle;
        this.description = description;
        this.restaurantItemList = restaurantItemList;
    }

    public String getSectionID() {
        return sectionID;
    }

    public void setSectionID(String sectionID) {
        this.sectionID = sectionID;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<RestaurantItemModel> getRestaurantItemList() {
        return restaurantItemList;
    }

    public void setRestaurantItemList(ArrayList<RestaurantItemModel> restaurantItemList) {
        this.restaurantItemList = restaurantItemList;
    }
}
