package com.cronlogy.charan.laalsa.Models;

public class FireSaleItemsModel {

    private String title;
    private String imageUrl;
    private String restaurant;
    private String offerPrice;
    private String unitPrice;
    private String tag;
    private String fireSaleId;
    private String menuId;
    private String restId;
    private String parentRestId;
    private String availability;
    private boolean veg;

    public FireSaleItemsModel() {
    }

    public FireSaleItemsModel(String title, String imageUrl, String restaurant, String offerPrice, String unitPrice, String tag, String fireSaleId, String menuId, String restId, String parentRestId, String availability, boolean veg) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.restaurant = restaurant;
        this.offerPrice = offerPrice;
        this.unitPrice = unitPrice;
        this.tag = tag;
        this.fireSaleId = fireSaleId;
        this.menuId = menuId;
        this.restId = restId;
        this.parentRestId = parentRestId;
        this.availability = availability;
        this.veg = veg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(String offerPrice) {
        this.offerPrice = offerPrice;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getFireSaleId() {
        return fireSaleId;
    }

    public void setFireSaleId(String fireSaleId) {
        this.fireSaleId = fireSaleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getRestId() {
        return restId;
    }

    public void setRestId(String restId) {
        this.restId = restId;
    }

    public String getParentRestId() {
        return parentRestId;
    }

    public void setParentRestId(String parentRestId) {
        this.parentRestId = parentRestId;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public boolean isVeg() {
        return veg;
    }

    public void setVeg(boolean veg) {
        this.veg = veg;
    }
}
