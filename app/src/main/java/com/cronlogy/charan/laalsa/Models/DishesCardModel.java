package com.cronlogy.charan.laalsa.Models;

import java.util.ArrayList;

public class DishesCardModel {
    private String sectionID;
    private String cardTitle;
    private String description;
    private ArrayList<DishesItemsModel> dishesItemsList;

    public DishesCardModel() {
    }

    public DishesCardModel(String sectionID, String cardTitle, String description, ArrayList<DishesItemsModel> dishesItemsList) {
        this.sectionID = sectionID;
        this.cardTitle = cardTitle;
        this.description = description;
        this.dishesItemsList = dishesItemsList;
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

    public ArrayList<DishesItemsModel> getDishesItemsList() {
        return dishesItemsList;
    }

    public void setDishesItemsList(ArrayList<DishesItemsModel> dishesItemsList) {
        this.dishesItemsList = dishesItemsList;
    }
}
