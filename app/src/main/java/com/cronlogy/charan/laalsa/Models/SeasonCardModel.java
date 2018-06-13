package com.cronlogy.charan.laalsa.Models;

import java.util.ArrayList;

public class SeasonCardModel {



    private String sectionID;
    private String heading;
    private String listTitle;
    private String description;
    private ArrayList<MustTryDishesModel> itemsList;

    public SeasonCardModel() {
    }

    public SeasonCardModel(String sectionID, String heading, String listTitle, String description, ArrayList<MustTryDishesModel> itemsList) {
        this.sectionID = sectionID;
        this.heading = heading;
        this.listTitle = listTitle;
        this.description = description;
        this.itemsList = itemsList;
    }

    public String getSectionID() {
        return sectionID;
    }

    public void setSectionID(String sectionID) {
        this.sectionID = sectionID;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getListTitle() {
        return listTitle;
    }

    public void setListTitle(String listTitle) {
        this.listTitle = listTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<MustTryDishesModel> getItemsList() {
        return itemsList;
    }

    public void setItemsList(ArrayList<MustTryDishesModel> itemsList) {
        this.itemsList = itemsList;
    }
}
