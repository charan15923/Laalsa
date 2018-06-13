package com.cronlogy.charan.laalsa.Models;

import java.util.ArrayList;

public class FireSaleCardModel {
    private String sectionID;
    private String cardTitle;
    private String description;
    private String information;
    private String imageUrl;
    private String logoUrl;
    private ArrayList<FireSaleItemsModel> fireSaleItemsList;

    public FireSaleCardModel() {
    }

    public FireSaleCardModel(String sectionID, String cardTitle, String description, String information, String imageUrl, String logoUrl, ArrayList<FireSaleItemsModel> fireSaleItemsList) {
        this.sectionID = sectionID;
        this.cardTitle = cardTitle;
        this.description = description;
        this.information = information;
        this.imageUrl = imageUrl;
        this.logoUrl = logoUrl;
        this.fireSaleItemsList = fireSaleItemsList;
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

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public ArrayList<FireSaleItemsModel> getFireSaleItemsList() {
        return fireSaleItemsList;
    }

    public void setFireSaleItemsList(ArrayList<FireSaleItemsModel> fireSaleItemsList) {
        this.fireSaleItemsList = fireSaleItemsList;
    }
}
