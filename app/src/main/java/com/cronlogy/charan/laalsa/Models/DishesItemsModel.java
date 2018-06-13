package com.cronlogy.charan.laalsa.Models;

public class DishesItemsModel {
    private String title;
    private String imageUrl;
    private String restaurant;
    private String price;
    private int rating;
    private int deliveryTime;
    private String menuId;
    private String restId;
    private String parentRestId;
    private String tag;
    private boolean veg;

    public DishesItemsModel() {
    }

    public DishesItemsModel(String title, String imageUrl, String restaurant, String price, int rating, int deliveryTime, String menuId, String restId, String parentRestId, String tag, boolean veg) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.restaurant = restaurant;
        this.price = price;
        this.rating = rating;
        this.deliveryTime = deliveryTime;
        this.menuId = menuId;
        this.restId = restId;
        this.parentRestId = parentRestId;
        this.tag = tag;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isVeg() {
        return veg;
    }

    public void setVeg(boolean veg) {
        this.veg = veg;
    }
}
