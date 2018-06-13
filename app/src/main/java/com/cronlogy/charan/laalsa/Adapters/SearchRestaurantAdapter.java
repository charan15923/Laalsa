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

public class SearchRestaurantAdapter extends RecyclerView.Adapter<SearchRestaurantAdapter.SearchRestaurantViewHolder> {

    Context context;
    ArrayList<SearchDataModel> arrayList;

    Laalsa helper = Laalsa.getInstance();

    private ImageLoader imageLoader;


    public SearchRestaurantAdapter(Context context, ArrayList<SearchDataModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public SearchRestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_restaurant,parent,false);
        return new SearchRestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchRestaurantViewHolder holder, int position) {
        if(!arrayList.get(position).getImage_thumb().isEmpty()){
            if(arrayList.get(position).getImage_thumb().contains(".png") || arrayList.get(position).getImage_thumb().contains(".jpg")
                    || arrayList.get(position).getImage_thumb().contains(".jpeg")){
                imageLoader= helper.getImageLoader();
                imageLoader.get(arrayList.get(position).getImage_thumb(),
                        ImageLoader.getImageListener(holder.restaurantLogo,
                                R.drawable.noodles, android.R.drawable
                                        .ic_dialog_alert));
            }
        }else{
            imageLoader= helper.getImageLoader();
            imageLoader.get(context.getResources().getString(R.string.no_image),
                    ImageLoader.getImageListener(holder.restaurantLogo,
                            R.drawable.noodles, android.R.drawable
                                    .ic_dialog_alert));
        }

        if(arrayList.get(position).getClosed()){
            holder.closedStatus.setVisibility(View.INVISIBLE);
        }else{
            holder.closedStatus.setVisibility(View.VISIBLE);
        }

        holder.deliveryTime.setText(arrayList.get(position).getDeliveryTime()+" min");
        holder.knownFor.setText("Known for "+arrayList.get(position).getPopularItems());
        holder.restaurantTitle.setText(arrayList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class SearchRestaurantViewHolder extends RecyclerView.ViewHolder {
        ImageView restaurantLogo;
        FontTextView restaurantTitle;
        FontTextView knownFor;
        FontTextView deliveryTime;
        FontTextView closedStatus;
        public SearchRestaurantViewHolder(View itemView) {
            super(itemView);
            restaurantLogo=(ImageView)itemView.findViewById(R.id.restaurantLogo);
            restaurantTitle=(FontTextView)itemView.findViewById(R.id.restaurantTitle);
            knownFor=(FontTextView)itemView.findViewById(R.id.knownFor);
            deliveryTime=(FontTextView)itemView.findViewById(R.id.deliveryTime);
            closedStatus=(FontTextView)itemView.findViewById(R.id.closedStatus);
        }
    }
}
