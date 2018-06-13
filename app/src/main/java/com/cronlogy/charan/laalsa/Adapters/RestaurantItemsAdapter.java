package com.cronlogy.charan.laalsa.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;

import com.android.volley.toolbox.ImageLoader;
import com.cronlogy.charan.laalsa.Models.OffersItemModel;
import com.cronlogy.charan.laalsa.Models.RestaurantItemModel;
import com.cronlogy.charan.laalsa.R;
import com.cronlogy.charan.laalsa.Utils.CircleImageView;
import com.cronlogy.charan.laalsa.Utils.Laalsa;

import java.util.ArrayList;

import fr.arnaudguyon.smartfontslib.FontTextView;

public class RestaurantItemsAdapter extends RecyclerView.Adapter<RestaurantItemsAdapter.RestaurantItemsViewHolder> {

    private Context context;
    private ArrayList<RestaurantItemModel> restaurantItemList;
    Laalsa helper = Laalsa.getInstance();

    private ImageLoader imageLoader;

    public RestaurantItemsAdapter(Context context, ArrayList<RestaurantItemModel> restaurantItemList) {
        this.context = context;
        this.restaurantItemList = restaurantItemList;
    }

    @NonNull
    @Override
    public RestaurantItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popular_restaurants,parent,false);
        return new RestaurantItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RestaurantItemsViewHolder holder, int position) {

        holder.restaurant.setText(restaurantItemList.get(position).getTitle());

        if(!restaurantItemList.get(position).getImageUrl().isEmpty()){
            if(restaurantItemList.get(position).getImageUrl().contains(".png") || restaurantItemList.get(position).getImageUrl().contains(".jpg")
                    || restaurantItemList.get(position).getImageUrl().contains(".jpeg")){
               imageLoader= helper.getImageLoader();
               imageLoader.get(restaurantItemList.get(position).getImageUrl(),
                       ImageLoader.getImageListener(holder.restaurantIV,
                               R.drawable.noodles, android.R.drawable
                                       .ic_dialog_alert));
            }
        }else{
            imageLoader= helper.getImageLoader();
            imageLoader.get(context.getResources().getString(R.string.no_image),
                    ImageLoader.getImageListener(holder.restaurantIV,
                            R.drawable.noodles, android.R.drawable
                                    .ic_dialog_alert));
        }

        if(!restaurantItemList.get(position).getLogo().isEmpty()){
            if(restaurantItemList.get(position).getLogo().contains(".png") || restaurantItemList.get(position).getLogo().contains(".jpg")
                    || restaurantItemList.get(position).getLogo().contains(".jpeg")){
                imageLoader= helper.getImageLoader();
                imageLoader.get(restaurantItemList.get(position).getLogo(),
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

        holder.area.setText(restaurantItemList.get(position).getAddress1());
        holder.cuisine.setText(restaurantItemList.get(position).getCuisine());
        holder.restaurantRating.setRating((float)restaurantItemList.get(position).getRating());


    }

    @Override
    public int getItemCount() {
        return restaurantItemList.size();
    }

    public class RestaurantItemsViewHolder extends RecyclerView.ViewHolder {
        de.hdodenhof.circleimageview.CircleImageView restaurantLogo;
        ImageView restaurantIV;
        FontTextView restaurant;
        FontTextView area;
        FontTextView cuisine;
        RatingBar restaurantRating;
        public RestaurantItemsViewHolder(View itemView) {
            super(itemView);

            restaurantLogo=(de.hdodenhof.circleimageview.CircleImageView) itemView.findViewById(R.id.restaurantLogo);
            restaurantIV=(ImageView)itemView.findViewById(R.id.restaurantIV);
            restaurant=(FontTextView)itemView.findViewById(R.id.restaurant);
            area=(FontTextView)itemView.findViewById(R.id.area);
            cuisine=(FontTextView)itemView.findViewById(R.id.cuisine);
            restaurantRating=(RatingBar)itemView.findViewById(R.id.restaurantRating);
        }
    }
}
