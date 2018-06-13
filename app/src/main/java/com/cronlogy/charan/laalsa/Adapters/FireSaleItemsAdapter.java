package com.cronlogy.charan.laalsa.Adapters;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.cronlogy.charan.laalsa.Models.DishesItemsModel;
import com.cronlogy.charan.laalsa.Models.FireSaleItemsModel;
import com.cronlogy.charan.laalsa.R;
import com.cronlogy.charan.laalsa.Utils.Laalsa;

import java.util.ArrayList;

import fr.arnaudguyon.smartfontslib.FontTextView;

public class FireSaleItemsAdapter extends RecyclerView.Adapter<FireSaleItemsAdapter.FireSaleItemsViewHolder> {

    private Context context;
    private ArrayList<FireSaleItemsModel> fireSaleItemsList;
    Laalsa helper = Laalsa.getInstance();

    private ImageLoader imageLoader;

    public FireSaleItemsAdapter(Context context, ArrayList<FireSaleItemsModel> fireSaleItemsList) {
        this.context = context;
        this.fireSaleItemsList = fireSaleItemsList;
    }

    @NonNull
    @Override
    public FireSaleItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_firesale_dishes_card,parent,false);
        return new FireSaleItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FireSaleItemsViewHolder holder, int position) {

        holder.dishTitle.setText(fireSaleItemsList.get(position).getTitle());

        if(!fireSaleItemsList.get(position).getImageUrl().isEmpty()){
            if(fireSaleItemsList.get(position).getImageUrl().contains(".png") || fireSaleItemsList.get(position).getImageUrl().contains(".jpg")
                    || fireSaleItemsList.get(position).getImageUrl().contains(".jpeg")){
               imageLoader= helper.getImageLoader();
               imageLoader.get(fireSaleItemsList.get(position).getImageUrl(),
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

        holder.restaurant.setText(fireSaleItemsList.get(position).getRestaurant());
        holder.offerPrice.setText(fireSaleItemsList.get(position).getOfferPrice());
        holder.unitPrice.setText(fireSaleItemsList.get(position).getUnitPrice());
        holder.stockLeft.setText(fireSaleItemsList.get(position).getAvailability());



        if(fireSaleItemsList.get(position).getTag().isEmpty()){
            holder.offerDescTag.setVisibility(View.INVISIBLE);
        }else{
            holder.offerDescTag.setVisibility(View.VISIBLE);
        }


        if(fireSaleItemsList.get(position).isVeg()){
            holder.dishTypeIV.setImageDrawable(context.getResources().getDrawable(R.drawable.veg));
        }else{
            holder.dishTypeIV.setImageDrawable(context.getResources().getDrawable(R.drawable.nonveg));
        }


        holder.unitPrice.setPaintFlags(holder.unitPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.rupeeSymbolStrike.setPaintFlags(holder.rupeeSymbolStrike.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


    }

    @Override
    public int getItemCount() {
        return fireSaleItemsList.size();
    }

    public class FireSaleItemsViewHolder extends RecyclerView.ViewHolder {
        FontTextView offerDescTag;
        ImageView dishIV;
        ImageView dishTypeIV;
        FontTextView dishTitle;
        FontTextView restaurant;
        FontTextView unitPrice;
        FontTextView rupeeSymbolStrike;
        FontTextView offerPrice;
        FontTextView stockLeft;


        public FireSaleItemsViewHolder(View itemView) {
            super(itemView);

            offerDescTag=(FontTextView)itemView.findViewById(R.id.offerDescTag);
            dishIV=(ImageView)itemView.findViewById(R.id.dishIV);
            dishTypeIV=(ImageView)itemView.findViewById(R.id.dishTypeIV);
            dishTitle=(FontTextView)itemView.findViewById(R.id.dishTitle);
            restaurant=(FontTextView)itemView.findViewById(R.id.restaurant);
            unitPrice=(FontTextView)itemView.findViewById(R.id.unitPrice);
            rupeeSymbolStrike=(FontTextView)itemView.findViewById(R.id.rupeeSymbolStrike);
            offerPrice=(FontTextView)itemView.findViewById(R.id.offerPrice);
            stockLeft=(FontTextView)itemView.findViewById(R.id.stockLeft);
        }
    }
}
