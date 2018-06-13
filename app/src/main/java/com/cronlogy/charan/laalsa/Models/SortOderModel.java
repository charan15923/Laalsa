package com.cronlogy.charan.laalsa.Models;

public class SortOderModel {

    private String sectionId;
    private String cardType;
    private int sortNumber;
    private String title;

    public SortOderModel() {
    }

    public SortOderModel(String sectionId, String cardType, int sortNumber, String title) {
        this.sectionId = sectionId;
        this.cardType = cardType;
        this.sortNumber = sortNumber;
        this.title = title;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public int getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(int sortNumber) {
        this.sortNumber = sortNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
