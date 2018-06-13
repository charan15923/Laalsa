package com.cronlogy.charan.laalsa.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.volley.toolbox.ImageLoader;
import com.cronlogy.charan.laalsa.Models.CollectionsItemModel;
import com.cronlogy.charan.laalsa.Models.OffersItemModel;
import com.cronlogy.charan.laalsa.R;
import com.cronlogy.charan.laalsa.Utils.Laalsa;

import java.util.ArrayList;

import fr.arnaudguyon.smartfontslib.FontTextView;

public class CollectionsItemsAdapter extends RecyclerView.Adapter<CollectionsItemsAdapter.CollectionsItemsViewHolder> {

    private Context context;
    private ArrayList<CollectionsItemModel> collectionsItemList;
    Laalsa helper = Laalsa.getInstance();

    private ImageLoader imageLoader;

    public CollectionsItemsAdapter(Context context, ArrayList<CollectionsItemModel> collectionsItemList) {
        this.context = context;
        this.collectionsItemList = collectionsItemList;
    }

    @NonNull
    @Override
    public CollectionsItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collections_card,parent,false);
        return new CollectionsItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CollectionsItemsViewHolder holder, int position) {

        holder.titleCollection.setText(collectionsItemList.get(position).getTitle());
        holder.descCollection.setText(collectionsItemList.get(position).getAvailability());

        if(!collectionsItemList.get(position).getImageUrl().isEmpty()){
            if(collectionsItemList.get(position).getImageUrl().contains(".png") || collectionsItemList.get(position).getImageUrl().contains(".jpg")
                    || collectionsItemList.get(position).getImageUrl().contains(".jpeg")){
               imageLoader= helper.getImageLoader();
               imageLoader.get(collectionsItemList.get(position).getImageUrl(),
                       ImageLoader.getImageListener(holder.dummyIV,
                               R.drawable.noodles, android.R.drawable
                                       .ic_dialog_alert));


            }
        }else{
            imageLoader= helper.getImageLoader();
            imageLoader.get(context.getResources().getString(R.string.no_image),
                    ImageLoader.getImageListener(holder.dummyIV,
                            R.drawable.noodles, android.R.drawable
                                    .ic_dialog_alert));

        }

        holder.parentCollectionsLayout.setBackground(holder.dummyIV.getDrawable());
    }

    @Override
    public int getItemCount() {
        return collectionsItemList.size();
    }

    public class CollectionsItemsViewHolder extends RecyclerView.ViewHolder {
        LinearLayout parentCollectionsLayout;
        FontTextView titleCollection;
        FontTextView descCollection;
        ImageView dummyIV;

        public CollectionsItemsViewHolder(View itemView) {
            super(itemView);

            parentCollectionsLayout=(LinearLayout)itemView.findViewById(R.id.parentCollectionsLayout);
            titleCollection=(FontTextView)itemView.findViewById(R.id.titleCollection);
            descCollection=(FontTextView)itemView.findViewById(R.id.descCollection);
            dummyIV=(ImageView)itemView.findViewById(R.id.dummyIV);
        }
    }
}
