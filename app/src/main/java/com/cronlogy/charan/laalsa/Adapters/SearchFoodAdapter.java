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

public class SearchFoodAdapter extends RecyclerView.Adapter<SearchFoodAdapter.SearchViewHolder> {

    Context context;
    ArrayList<SearchDataModel> arrayList;

    public SearchFoodAdapter(Context context, ArrayList<SearchDataModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_food,parent,false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {

        holder.foodTitle.setText(arrayList.get(position).getName());
        holder.restaurantsNumber.setText(arrayList.get(position).getTotalRestaurants()+" Restaurants");
        holder.nearYou.setText(arrayList.get(position).getNearYou()+" near you");
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {

        FontTextView foodTitle;
        FontTextView restaurantsNumber;
        FontTextView nearYou;

        public SearchViewHolder(View itemView) {
            super(itemView);

            foodTitle=(FontTextView)itemView.findViewById(R.id.foodTitle);
            restaurantsNumber=(FontTextView)itemView.findViewById(R.id.restaurantsNumber);
            nearYou=(FontTextView)itemView.findViewById(R.id.nearYou);
        }
    }
}
