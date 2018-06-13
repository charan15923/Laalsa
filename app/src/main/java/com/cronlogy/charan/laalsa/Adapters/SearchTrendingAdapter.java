package com.cronlogy.charan.laalsa.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cronlogy.charan.laalsa.Models.SearchDataModel;
import com.cronlogy.charan.laalsa.R;

import java.util.ArrayList;

import fr.arnaudguyon.smartfontslib.FontTextView;

public class SearchTrendingAdapter extends RecyclerView.Adapter<SearchTrendingAdapter.SearchTrendingViewHolder> {

    Context context;
    ArrayList<SearchDataModel> searchTrendingList;

    public SearchTrendingAdapter(Context context, ArrayList<SearchDataModel> searchTrendingList) {
        this.context = context;
        this.searchTrendingList = searchTrendingList;
    }

    @NonNull
    @Override
    public SearchTrendingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_trending,parent,false);
        return new SearchTrendingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchTrendingViewHolder holder, int position) {
            holder.trendingTitle.setText(searchTrendingList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return searchTrendingList.size();
    }

    public class SearchTrendingViewHolder extends RecyclerView.ViewHolder {

        FontTextView trendingTitle;

        public SearchTrendingViewHolder(View itemView) {
            super(itemView);
            trendingTitle=(FontTextView)itemView.findViewById(R.id.trendingTitle);
        }
    }
}
