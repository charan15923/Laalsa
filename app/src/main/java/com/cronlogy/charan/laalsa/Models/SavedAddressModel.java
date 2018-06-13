package com.cronlogy.charan.laalsa.Models;

public class SavedAddressModel {

    private String title;
    private String address;
    private boolean selected;

    public SavedAddressModel() {
    }

    public SavedAddressModel(String title, String address, boolean selected) {
        this.title = title;
        this.address = address;
        this.selected = selected;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
