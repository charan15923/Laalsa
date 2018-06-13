package com.cronlogy.charan.laalsa.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.cronlogy.charan.laalsa.Models.MustTryDishesModel;
import com.cronlogy.charan.laalsa.R;
import com.cronlogy.charan.laalsa.Utils.Laalsa;

import java.util.ArrayList;

import fr.arnaudguyon.smartfontslib.FontTextView;

public class SeasonalItemsAdapter extends RecyclerView.Adapter<SeasonalItemsAdapter.SeasonalItemsViewHolder> {

    private Context context;
    private ArrayList<MustTryDishesModel> mustTryDishesList;
    Laalsa helper = Laalsa.getInstance();

    private ImageLoader imageLoader;

    public SeasonalItemsAdapter(Context context, ArrayList<MustTryDishesModel> mustTryDishesList) {
        this.context = context;
        this.mustTryDishesList = mustTryDishesList;
    }

    @NonNull
    @Override
    public SeasonalItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_must_try_dishes_card,parent,false);
        return new SeasonalItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SeasonalItemsViewHolder holder, int position) {

        holder.title.setText(mustTryDishesList.get(position).getTitle());
        if(mustTryDishesList.get(position).getTag().isEmpty()){
            holder.tag.setVisibility(View.INVISIBLE);
        }else{
            holder.tag.setVisibility(View.VISIBLE);
        }

        if(!mustTryDishesList.get(position).getImageUrl().isEmpty()){
            if(mustTryDishesList.get(position).getImageUrl().contains(".png") || mustTryDishesList.get(position).getImageUrl().contains(".jpg")
                    || mustTryDishesList.get(position).getImageUrl().contains(".jpeg")){
               imageLoader= helper.getImageLoader();
               imageLoader.get(mustTryDishesList.get(position).getImageUrl(),
                       ImageLoader.getImageListener(holder.image,
                               R.drawable.noodles, android.R.drawable
                                       .ic_dialog_alert));
            }
        }else{
            imageLoader= helper.getImageLoader();
            imageLoader.get(context.getResources().getString(R.string.no_image),
                    ImageLoader.getImageListener(holder.image,
                            R.drawable.noodles, android.R.drawable
                                    .ic_dialog_alert));
        }

        if(mustTryDishesList.get(position).isVeg()){
            holder.dishTypeIV.setImageDrawable(context.getResources().getDrawable(R.drawable.veg));
        }else{
            holder.dishTypeIV.setImageDrawable(context.getResources().getDrawable(R.drawable.nonveg));
        }
    }

    @Override
    public int getItemCount() {
        return mustTryDishesList.size();
    }

    public class SeasonalItemsViewHolder extends RecyclerView.ViewHolder {
        FontTextView tag;
        ImageView dishTypeIV;
        ImageView image;
        FontTextView title;
        public SeasonalItemsViewHolder(View itemView) {
            super(itemView);

            tag=(FontTextView)itemView.findViewById(R.id.bestSellerTag);
            dishTypeIV=(ImageView)itemView.findViewById(R.id.dishTypeIV);
            image=(ImageView)itemView.findViewById(R.id.dishIV);
            title=(FontTextView)itemView.findViewById(R.id.dishTitle);
        }
    }
}
