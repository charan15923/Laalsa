package com.cronlogy.charan.laalsa.Models;

import java.util.ArrayList;

public class CollectionsCardModel {

    private String sectionID;
    private String cardTitle;
    private String description;
    private ArrayList<CollectionsItemModel> collectionsItemList;

    public CollectionsCardModel() {
    }

    public CollectionsCardModel(String sectionID, String cardTitle, String description, ArrayList<CollectionsItemModel> collectionsItemList) {
        this.sectionID = sectionID;
        this.cardTitle = cardTitle;
        this.description = description;
        this.collectionsItemList = collectionsItemList;
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

    public ArrayList<CollectionsItemModel> getCollectionsItemList() {
        return collectionsItemList;
    }

    public void setCollectionsItemList(ArrayList<CollectionsItemModel> collectionsItemList) {
        this.collectionsItemList = collectionsItemList;
    }
}
