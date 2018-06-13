package com.cronlogy.charan.laalsa.Models;

import java.util.ArrayList;

public class SearchTrendingNearYouParentModel {

    private int imgUrl;
    private String title;
    private ArrayList<SearchDataModel> trendingList;
    private ArrayList<SearchDataModel> nearYouList;

    public SearchTrendingNearYouParentModel(int imgUrl, String title, ArrayList<SearchDataModel> trendingList, ArrayList<SearchDataModel> nearYouList) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.trendingList = trendingList;
        this.nearYouList = nearYouList;
    }

    public int getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(int imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<SearchDataModel> getTrendingList() {
        return trendingList;
    }

    public void setTrendingList(ArrayList<SearchDataModel> trendingList) {
        this.trendingList = trendingList;
    }

    public ArrayList<SearchDataModel> getNearYouList() {
        return nearYouList;
    }

    public void setNearYouList(ArrayList<SearchDataModel> nearYouList) {
        this.nearYouList = nearYouList;
    }
}
