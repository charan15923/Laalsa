package com.cronlogy.charan.laalsa.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.cronlogy.charan.laalsa.Models.SearchDataModel;
import com.cronlogy.charan.laalsa.R;
import com.cronlogy.charan.laalsa.Utils.Laalsa;

import java.util.ArrayList;

import fr.arnaudguyon.smartfontslib.FontTextView;

public class SearchNearYouAdapter extends RecyclerView.Adapter<SearchNearYouAdapter.SearchNearYouViewHolder> {

    Context context;
    ArrayList<SearchDataModel> searchNearYouList;

    Laalsa helper = Laalsa.getInstance();

    private ImageLoader imageLoader;

    public SearchNearYouAdapter(Context context, ArrayList<SearchDataModel> searchNearYouList) {
        this.context = context;
        this.searchNearYouList = searchNearYouList;
    }

    @NonNull
    @Override
    public SearchNearYouViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_near_you,parent,false);
        return new SearchNearYouViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchNearYouViewHolder holder, int position) {
        if(!searchNearYouList.get(position).getImage_thumb().isEmpty()){
            if(searchNearYouList.get(position).getImage_thumb().contains(".png") || searchNearYouList.get(position).getImage_thumb().contains(".jpg")
                    || searchNearYouList.get(position).getImage_thumb().contains(".jpeg")){
                imageLoader= helper.getImageLoader();
                imageLoader.get(searchNearYouList.get(position).getImage_thumb(),
                        ImageLoader.getImageListener(holder.nearYourIV,
                                R.drawable.noodles, android.R.drawable
                                        .ic_dialog_alert));
            }
        }else{
            imageLoader= helper.getImageLoader();
            imageLoader.get(context.getResources().getString(R.string.no_image),
                    ImageLoader.getImageListener(holder.nearYourIV,
                            R.drawable.noodles, android.R.drawable
                                    .ic_dialog_alert));
        }

        holder.nearYouTitle.setText(searchNearYouList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return searchNearYouList.size();
    }

    public class SearchNearYouViewHolder extends RecyclerView.ViewHolder {
        ImageView nearYourIV;
        FontTextView nearYouTitle;
        public SearchNearYouViewHolder(View itemView) {
            super(itemView);
            nearYourIV = (ImageView)itemView.findViewById(R.id.nearYourIV);
            nearYouTitle = (FontTextView)itemView.findViewById(R.id.nearYouTitle);
        }
    }
}
