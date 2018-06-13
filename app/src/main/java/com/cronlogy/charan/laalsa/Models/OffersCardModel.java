package com.cronlogy.charan.laalsa.Models;

import java.util.ArrayList;

public class OffersCardModel {

    private String sectionID;
    private String cardTitle;
    private String description;
    private ArrayList<OffersItemModel> offersItemList;

    public OffersCardModel() {
    }

    public OffersCardModel(String sectionID, String cardTitle, String description, ArrayList<OffersItemModel> offersItemList) {
        this.sectionID = sectionID;
        this.cardTitle = cardTitle;
        this.description = description;
        this.offersItemList = offersItemList;
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

    public ArrayList<OffersItemModel> getOffersItemList() {
        return offersItemList;
    }

    public void setOffersItemList(ArrayList<OffersItemModel> offersItemList) {
        this.offersItemList = offersItemList;
    }
}
