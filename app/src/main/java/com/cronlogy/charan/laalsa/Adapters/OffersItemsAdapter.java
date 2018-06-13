package com.cronlogy.charan.laalsa.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.volley.toolbox.ImageLoader;
import com.cronlogy.charan.laalsa.Models.MustTryDishesModel;
import com.cronlogy.charan.laalsa.Models.OffersItemModel;
import com.cronlogy.charan.laalsa.R;
import com.cronlogy.charan.laalsa.Utils.Laalsa;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import fr.arnaudguyon.smartfontslib.FontTextView;

public class OffersItemsAdapter extends RecyclerView.Adapter<OffersItemsAdapter.OffersItemsViewHolder> {

    private Context context;
    private ArrayList<OffersItemModel> offersItemList;
    Laalsa helper = Laalsa.getInstance();

    private static final String TAG = "OffersItemsAdapter";


    private ImageLoader imageLoader;

    public OffersItemsAdapter(Context context, ArrayList<OffersItemModel> offersItemList) {
        this.context = context;
        this.offersItemList = offersItemList;
    }

    @NonNull
    @Override
    public OffersItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_offers_card,parent,false);
        return new OffersItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final OffersItemsViewHolder holder, int position) {

        holder.offerTitle.setText(offersItemList.get(position).getTitle().toUpperCase());

        if(!offersItemList.get(position).getImageUrl().isEmpty()){
            if(offersItemList.get(position).getImageUrl().contains(".png") || offersItemList.get(position).getImageUrl().contains(".jpg")
                    || offersItemList.get(position).getImageUrl().contains(".jpeg")){

                Log.e(TAG, "onBindViewHolder: YES" );
               imageLoader= helper.getImageLoader();
               imageLoader.get(offersItemList.get(position).getImageUrl(),
                       ImageLoader.getImageListener(holder.offerIv,
                               R.drawable.noodles, android.R.drawable
                                       .ic_dialog_alert));
            }
        }else{
            Log.e(TAG, "onBindViewHolder: NAY" );

            imageLoader= helper.getImageLoader();
            imageLoader.get(context.getResources().getString(R.string.no_image),
                    ImageLoader.getImageListener(holder.offerIv,
                            R.drawable.noodles, android.R.drawable
                                    .ic_dialog_alert));
        }

        holder.offerDesc.setText(offersItemList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return offersItemList.size();
    }

    public class OffersItemsViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout parentOfferLayout;
        ImageView offerIv;
        ImageView trendingIV;
        FontTextView specialTV;
        FontTextView offerTitle;
        FontTextView offerDesc;
        public OffersItemsViewHolder(View itemView) {
            super(itemView);

            parentOfferLayout=(RelativeLayout)itemView.findViewById(R.id.parentOfferLayout);
            offerIv=(ImageView)itemView.findViewById(R.id.offerIv);
            trendingIV=(ImageView)itemView.findViewById(R.id.trendingIV);
            specialTV=(FontTextView)itemView.findViewById(R.id.specialTV);
            offerTitle=(FontTextView)itemView.findViewById(R.id.offerTitle);
            offerDesc=(FontTextView)itemView.findViewById(R.id.offerDesc);
        }
    }
}
