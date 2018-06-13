package com.cronlogy.charan.laalsa.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.volley.toolbox.ImageLoader;
import com.cronlogy.charan.laalsa.Models.DishesItemsModel;
import com.cronlogy.charan.laalsa.Models.OffersItemModel;
import com.cronlogy.charan.laalsa.R;
import com.cronlogy.charan.laalsa.Utils.Laalsa;

import java.util.ArrayList;

import fr.arnaudguyon.smartfontslib.FontTextView;

public class DishesItemsAdapter extends RecyclerView.Adapter<DishesItemsAdapter.DishesItemsViewHolder> {

    private Context context;
    private ArrayList<DishesItemsModel> dishesItemsList;
    Laalsa helper = Laalsa.getInstance();

    private ImageLoader imageLoader;

    public DishesItemsAdapter(Context context, ArrayList<DishesItemsModel> dishesItemsList) {
        this.context = context;
        this.dishesItemsList = dishesItemsList;
    }

    @NonNull
    @Override
    public DishesItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popular_dishes_card,parent,false);
        return new DishesItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DishesItemsViewHolder holder, int position) {

        holder.dishTitle.setText(dishesItemsList.get(position).getTitle());

        if(!dishesItemsList.get(position).getImageUrl().isEmpty()){
            if(dishesItemsList.get(position).getImageUrl().contains(".png") || dishesItemsList.get(position).getImageUrl().contains(".jpg")
                    || dishesItemsList.get(position).getImageUrl().contains(".jpeg")){
               imageLoader= helper.getImageLoader();
               imageLoader.get(dishesItemsList.get(position).getImageUrl(),
                       ImageLoader.getImageListener(holder.dishIV,
                               R.drawable.noodles, android.R.drawable
                                       .ic_dialog_alert));
            }
        }else{
            imageLoader= helper.getImageLoader();
            imageLoader.get(context.getResources().getString(R.string.no_image),
                    ImageLoader.getImageListener(holder.dishIV,
                            R.drawable.noodles, android.R.drawable
                                    .ic_dialog_alert));
        }

        holder.restaurant.setText(dishesItemsList.get(position).getRestaurant());
        holder.price.setText(dishesItemsList.get(position).getPrice());
        holder.deliveryTime.setText(dishesItemsList.get(position).getDeliveryTime()+" min");
        if(dishesItemsList.get(position).getTag().isEmpty()){
            holder.bestSellerTag.setVisibility(View.INVISIBLE);
        }else{
            holder.bestSellerTag.setVisibility(View.VISIBLE);
        }

        holder.rating.setText(dishesItemsList.get(position).getRating()+"");

        if(dishesItemsList.get(position).isVeg()){
            holder.dishTypeIV.setImageDrawable(context.getResources().getDrawable(R.drawable.veg));
        }else{
            holder.dishTypeIV.setImageDrawable(context.getResources().getDrawable(R.drawable.nonveg));
        }


    }

    @Override
    public int getItemCount() {
        return dishesItemsList.size();
    }

    public class DishesItemsViewHolder extends RecyclerView.ViewHolder {
        FontTextView bestSellerTag;
        ImageView dishIV;
        ImageView dishTypeIV;
        FontTextView dishTitle;
        FontTextView restaurant;
        FontTextView price;
        FontTextView deliveryTime;
        FontTextView rating;
        public DishesItemsViewHolder(View itemView) {
            super(itemView);

            bestSellerTag=(FontTextView)itemView.findViewById(R.id.bestSellerTag);
            dishIV=(ImageView)itemView.findViewById(R.id.dishIV);
            dishTypeIV=(ImageView)itemView.findViewById(R.id.dishTypeIV);
            dishTitle=(FontTextView)itemView.findViewById(R.id.dishTitle);
            restaurant=(FontTextView)itemView.findViewById(R.id.restaurant);
            price=(FontTextView)itemView.findViewById(R.id.price);
            deliveryTime=(FontTextView)itemView.findViewById(R.id.deliveryTime);
            rating=(FontTextView)itemView.findViewById(R.id.rating);
        }
    }
}
