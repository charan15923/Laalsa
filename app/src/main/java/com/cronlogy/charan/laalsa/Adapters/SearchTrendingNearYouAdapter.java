package com.cronlogy.charan.laalsa.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cronlogy.charan.laalsa.Models.SearchTrendingNearYouParentModel;
import com.cronlogy.charan.laalsa.R;

import java.util.ArrayList;

import fr.arnaudguyon.smartfontslib.FontTextView;

public class SearchTrendingNearYouAdapter extends RecyclerView.Adapter<SearchTrendingNearYouAdapter.SearchTrendingNearYouViewHolder> {


    Context context;
    ArrayList<SearchTrendingNearYouParentModel> searchTrendingNearYouParentList;

    public SearchTrendingNearYouAdapter(Context context, ArrayList<SearchTrendingNearYouParentModel> searchTrendingNearYouParentList) {
        this.context = context;
        this.searchTrendingNearYouParentList = searchTrendingNearYouParentList;
    }

    @NonNull
    @Override
    public SearchTrendingNearYouViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_trending_near_you_parent,parent,false);
        return new SearchTrendingNearYouViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchTrendingNearYouViewHolder holder, int position) {

        SearchTrendingNearYouParentModel searchTrendingNearYouParentModel = searchTrendingNearYouParentList.get(position);

        if(searchTrendingNearYouParentModel.getTrendingList()!=null){

            holder.headerTitle.setText(searchTrendingNearYouParentModel.getTitle());
            holder.headerIcon.setImageResource(searchTrendingNearYouParentModel.getImgUrl());

            holder.searchTrendingNearRcv.setHasFixedSize(true);
            holder.searchTrendingNearRcv.setNestedScrollingEnabled(false);
            holder.searchTrendingNearRcv.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
            SearchTrendingAdapter searchTrendingAdapter = new SearchTrendingAdapter(context,searchTrendingNearYouParentModel.getTrendingList());
            holder.searchTrendingNearRcv.setAdapter(searchTrendingAdapter);

        }

        if(searchTrendingNearYouParentModel.getNearYouList()!=null){
            holder.headerTitle.setText(searchTrendingNearYouParentModel.getTitle());
            holder.headerIcon.setImageResource(searchTrendingNearYouParentModel.getImgUrl());

            holder.searchTrendingNearRcv.setHasFixedSize(true);
            holder.searchTrendingNearRcv.setNestedScrollingEnabled(false);
            holder.searchTrendingNearRcv.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
            SearchNearYouAdapter searchNearYouAdapter = new SearchNearYouAdapter(context,searchTrendingNearYouParentModel.getNearYouList());
            holder.searchTrendingNearRcv.setAdapter(searchNearYouAdapter);
        }

    }

    @Override
    public int getItemCount() {
        return searchTrendingNearYouParentList.size();
    }

    public class SearchTrendingNearYouViewHolder extends RecyclerView.ViewHolder {

        ImageView headerIcon;
        FontTextView headerTitle;
        RecyclerView searchTrendingNearRcv;
        public SearchTrendingNearYouViewHolder(View itemView) {
            super(itemView);

            headerIcon=(ImageView)itemView.findViewById(R.id.headerIcon);
            headerTitle=(FontTextView)itemView.findViewById(R.id.headerTitle);
            searchTrendingNearRcv=(RecyclerView)itemView.findViewById(R.id.searchTrendingNearRcv);
        }
    }
}
