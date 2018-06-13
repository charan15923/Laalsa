package com.cronlogy.charan.laalsa.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.cronlogy.charan.laalsa.Models.CollectionsCardModel;
import com.cronlogy.charan.laalsa.Models.DishesCardModel;
import com.cronlogy.charan.laalsa.Models.FireSaleCardModel;
import com.cronlogy.charan.laalsa.Models.NotificationCardModel;
import com.cronlogy.charan.laalsa.Models.OffersCardModel;
import com.cronlogy.charan.laalsa.Models.RestaurantCardModel;
import com.cronlogy.charan.laalsa.Models.SeasonCardModel;
import com.cronlogy.charan.laalsa.R;
import com.cronlogy.charan.laalsa.Utils.Laalsa;

import java.util.ArrayList;

import fr.arnaudguyon.smartfontslib.FontButton;
import fr.arnaudguyon.smartfontslib.FontTextView;

public class HomeScreenParentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "HomeScreenParentAdapter";

    private Context context;
    private ArrayList<Object> objects;

    private static final int SEASONAL_CARD = 1;
    private static final int FIRE_SALE_CARD = 2;
    private static final int DISHES_CARD = 3;
    private static final int RESTAURANT_CARD = 4;
    private static final int OFFERS_CARD = 5;
    private static final int COLLECTIONS_CARD = 6;
    private static final int NOTIFICATION_CARD = 7;


    Laalsa helper = Laalsa.getInstance();

    private ImageLoader imageLoader;

    public HomeScreenParentAdapter(Context context, ArrayList<Object> objects) {
        this.context = context;
        this.objects = objects;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==SEASONAL_CARD || viewType==DISHES_CARD || viewType==RESTAURANT_CARD || viewType==OFFERS_CARD ||
                viewType==COLLECTIONS_CARD){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_season_card,parent,false);
            Log.e(TAG, "onCreateViewHolder: "+SEASONAL_CARD );

            return new SeasonalViewHolder(view);

        }

        if(viewType==FIRE_SALE_CARD){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_firesale_card,parent,false);
            Log.e(TAG, "onCreateViewHolder: "+FIRE_SALE_CARD );

            return new FireSaleViewHolder(view);

        }

        if(viewType==NOTIFICATION_CARD){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification_card,parent,false);
            Log.e(TAG, "onCreateViewHolder: "+NOTIFICATION_CARD );

            return new NotificationViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        switch (holder.getItemViewType()){

            case SEASONAL_CARD:{
                initSeasonsView(holder,position);
                Log.e(TAG, "onBindViewHolder: "+ holder.getItemViewType());
                break;
            }
            case FIRE_SALE_CARD:{
                initFireSaleView(holder,position);
                Log.e(TAG, "onBindViewHolder: "+ holder.getItemViewType());
                break;
            }
            case OFFERS_CARD:{
                initOffersView(holder,position);
                Log.e(TAG, "onBindViewHolder: "+ holder.getItemViewType());
                break;
            }
            case DISHES_CARD:{
                initDishesView(holder,position);
                Log.e(TAG, "onBindViewHolder: "+ holder.getItemViewType());
                break;
            }
            case RESTAURANT_CARD:{
                initRestaurantsView(holder,position);
                Log.e(TAG, "onBindViewHolder: "+ holder.getItemViewType());
                break;
            }

            case COLLECTIONS_CARD:{
                initCollectionsView(holder,position);
                Log.e(TAG, "onBindViewHolder: "+ holder.getItemViewType());
                break;
            }

            case NOTIFICATION_CARD:{
                initNotificationsView(holder,position);
                Log.e(TAG, "onBindViewHolder: "+ holder.getItemViewType());
                break;
            }

        }

    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(objects.get(position) instanceof SeasonCardModel){
            return SEASONAL_CARD;
        }

        if(objects.get(position) instanceof FireSaleCardModel){
            return FIRE_SALE_CARD;
        }

        if(objects.get(position) instanceof DishesCardModel){
            return DISHES_CARD;
        }

        if(objects.get(position) instanceof RestaurantCardModel){
            return RESTAURANT_CARD;
        }

        if(objects.get(position) instanceof OffersCardModel){
            return OFFERS_CARD;
        }

        if(objects.get(position) instanceof CollectionsCardModel){
            return COLLECTIONS_CARD;
        }

        if(objects.get(position) instanceof NotificationCardModel){
            return NOTIFICATION_CARD;
        }
        return 0;
    }

    class SeasonalViewHolder extends RecyclerView.ViewHolder {
        LinearLayout seasonalHeader;
        ImageView seasonalIV;
        FontTextView titleSeasonal;
        FontTextView itemTitle;
        FontTextView btnMore;
        RecyclerView seasonalRcv;

        public SeasonalViewHolder(View itemView){
            super(itemView);

            seasonalHeader=(LinearLayout)itemView.findViewById(R.id.seasonalHeader);
            seasonalIV=(ImageView)itemView.findViewById(R.id.seasonalIV);
            titleSeasonal=(FontTextView)itemView.findViewById(R.id.titleSeasonal);
            itemTitle=(FontTextView)itemView.findViewById(R.id.itemTitle);
            btnMore=(FontTextView)itemView.findViewById(R.id.btnMore);
            seasonalRcv=(RecyclerView)itemView.findViewById(R.id.seasonalRcv);
        }
    }

    class FireSaleViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout fireSaleHeader;
        ImageView fireSaleIV;
        ImageView dummyIV;
        ImageView firesaleLogo;
        FontTextView titleFireSale;
        FontTextView descFireSale;
        FontTextView listHeaderFireSale;
        RecyclerView firesaleRcv;
        FontButton showAllFiresaleBtn;
        public FireSaleViewHolder(View itemView){
            super(itemView);

            fireSaleHeader=(RelativeLayout)itemView.findViewById(R.id.fireSaleHeader);
            fireSaleIV=(ImageView)itemView.findViewById(R.id.fireSaleIV);
            dummyIV=(ImageView)itemView.findViewById(R.id.dummyIV);
            firesaleLogo=(ImageView) itemView.findViewById(R.id.firesaleLogo);
            titleFireSale=(FontTextView)itemView.findViewById(R.id.titleFireSale);
            descFireSale=(FontTextView)itemView.findViewById(R.id.descFireSale);
            listHeaderFireSale=(FontTextView)itemView.findViewById(R.id.listHeaderFireSale);
            firesaleRcv=(RecyclerView)itemView.findViewById(R.id.firesaleRcv);
            showAllFiresaleBtn=(FontButton)itemView.findViewById(R.id.showAllFiresaleBtn);
        }
    }

    class NotificationViewHolder extends RecyclerView.ViewHolder {
        ImageView notifyLogo;
        FontTextView titleNotification;
        FontTextView notifyMeBtn;
        ImageView dummyIV;

        public NotificationViewHolder(View itemView){
            super(itemView);
            notifyLogo=(ImageView)itemView.findViewById(R.id.notifyLogo);
            titleNotification=(FontTextView)itemView.findViewById(R.id.titleNotification);
            notifyMeBtn=(FontTextView)itemView.findViewById(R.id.notifyMeBtn);
            dummyIV=(ImageView)itemView.findViewById(R.id.dummyIV);

        }
    }

    private void initSeasonsView(RecyclerView.ViewHolder holder,int position){
        SeasonCardModel seasonCardModel = (SeasonCardModel) objects.get(position);
        //((SeasonalViewHolder)holder).titleSeasonal.setText(seasonCardModel.getHeading());
        ((SeasonalViewHolder)holder).itemTitle.setText(seasonCardModel.getListTitle());
        ((SeasonalViewHolder)holder).seasonalRcv.setNestedScrollingEnabled(false);
        ((SeasonalViewHolder)holder).seasonalRcv.setHasFixedSize(true);
        ((SeasonalViewHolder)holder).seasonalRcv.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        SeasonalItemsAdapter seasonalItemsAdapter = new SeasonalItemsAdapter(context,seasonCardModel.getItemsList());
        ((SeasonalViewHolder)holder).seasonalRcv.setAdapter(seasonalItemsAdapter);

    }

    private void initFireSaleView(RecyclerView.ViewHolder holder,int position){
        FireSaleCardModel fireSaleCardModel = (FireSaleCardModel) objects.get(position);

        if(!fireSaleCardModel.getImageUrl().isEmpty()){
            if(fireSaleCardModel.getImageUrl().contains(".png") || fireSaleCardModel.getImageUrl().contains(".jpg")
                    || fireSaleCardModel.getImageUrl().contains(".jpeg")){
                imageLoader= helper.getImageLoader();
                imageLoader.get(fireSaleCardModel.getImageUrl(),
                        ImageLoader.getImageListener(((FireSaleViewHolder)holder).fireSaleIV,
                                R.drawable.noodles, android.R.drawable
                                        .ic_dialog_alert));


            }
        }else{
            imageLoader= helper.getImageLoader();
            imageLoader.get(context.getResources().getString(R.string.no_image),
                    ImageLoader.getImageListener(((FireSaleViewHolder)holder).fireSaleIV,
                            R.drawable.noodles, android.R.drawable
                                    .ic_dialog_alert));

        }

        if(!fireSaleCardModel.getLogoUrl().isEmpty()){
            if(fireSaleCardModel.getLogoUrl().contains(".png") || fireSaleCardModel.getLogoUrl().contains(".jpg")
                    || fireSaleCardModel.getLogoUrl().contains(".jpeg")){
                imageLoader= helper.getImageLoader();
                imageLoader.get(fireSaleCardModel.getLogoUrl(),
                        ImageLoader.getImageListener(((FireSaleViewHolder)holder).firesaleLogo,
                                R.drawable.noodles, android.R.drawable
                                        .ic_dialog_alert));


            }
        }else{
            imageLoader= helper.getImageLoader();
            imageLoader.get(context.getResources().getString(R.string.no_image),
                    ImageLoader.getImageListener(((FireSaleViewHolder)holder).firesaleLogo,
                            R.drawable.noodles, android.R.drawable
                                    .ic_dialog_alert));

        }


        ((FireSaleViewHolder)holder).titleFireSale.setText(fireSaleCardModel.getCardTitle());
        ((FireSaleViewHolder)holder).descFireSale.setText(fireSaleCardModel.getDescription());
        ((FireSaleViewHolder)holder).listHeaderFireSale.setText(fireSaleCardModel.getInformation());


        ((FireSaleViewHolder)holder).firesaleRcv.setNestedScrollingEnabled(false);
        ((FireSaleViewHolder)holder).firesaleRcv.setHasFixedSize(true);
        ((FireSaleViewHolder)holder).firesaleRcv.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        FireSaleItemsAdapter fireSaleItemsAdapter = new FireSaleItemsAdapter(context,fireSaleCardModel.getFireSaleItemsList());
        ((FireSaleViewHolder)holder).firesaleRcv.setAdapter(fireSaleItemsAdapter);

    }

    private void initOffersView(RecyclerView.ViewHolder holder,int position){
        OffersCardModel offersCardModel = (OffersCardModel) objects.get(position);
        ((SeasonalViewHolder)holder).seasonalHeader.setVisibility(View.GONE);
        ((SeasonalViewHolder)holder).itemTitle.setText(offersCardModel.getCardTitle());
        ((SeasonalViewHolder)holder).seasonalRcv.setNestedScrollingEnabled(false);
        ((SeasonalViewHolder)holder).seasonalRcv.setHasFixedSize(true);
        ((SeasonalViewHolder)holder).seasonalRcv.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        OffersItemsAdapter offersItemsAdapter = new OffersItemsAdapter(context,offersCardModel.getOffersItemList());
        ((SeasonalViewHolder)holder).seasonalRcv.setAdapter(offersItemsAdapter);
    }

    private void initDishesView(RecyclerView.ViewHolder holder,int position){
        DishesCardModel dishesCardModel = (DishesCardModel) objects.get(position);
        ((SeasonalViewHolder)holder).seasonalHeader.setVisibility(View.GONE);
        ((SeasonalViewHolder)holder).itemTitle.setText(dishesCardModel.getCardTitle());
        ((SeasonalViewHolder)holder).seasonalRcv.setNestedScrollingEnabled(false);
        ((SeasonalViewHolder)holder).seasonalRcv.setHasFixedSize(true);
        ((SeasonalViewHolder)holder).seasonalRcv.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        DishesItemsAdapter dishesItemsAdapter = new DishesItemsAdapter(context,dishesCardModel.getDishesItemsList());
        ((SeasonalViewHolder)holder).seasonalRcv.setAdapter(dishesItemsAdapter);

    }

    private void initRestaurantsView(RecyclerView.ViewHolder holder,int position){
        RestaurantCardModel restaurantCardModel = (RestaurantCardModel) objects.get(position);
        ((SeasonalViewHolder)holder).seasonalHeader.setVisibility(View.GONE);
        ((SeasonalViewHolder)holder).itemTitle.setText(restaurantCardModel.getCardTitle());
        ((SeasonalViewHolder)holder).seasonalRcv.setNestedScrollingEnabled(false);
        ((SeasonalViewHolder)holder).seasonalRcv.setHasFixedSize(true);
        ((SeasonalViewHolder)holder).seasonalRcv.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        RestaurantItemsAdapter restaurantItemsAdapter = new RestaurantItemsAdapter(context,restaurantCardModel.getRestaurantItemList());
        ((SeasonalViewHolder)holder).seasonalRcv.setAdapter(restaurantItemsAdapter);
    }

    private void initCollectionsView(RecyclerView.ViewHolder holder,int position){
        CollectionsCardModel collectionsCardModel = (CollectionsCardModel) objects.get(position);
        ((SeasonalViewHolder)holder).seasonalHeader.setVisibility(View.GONE);
        ((SeasonalViewHolder)holder).itemTitle.setText(collectionsCardModel.getCardTitle());
        ((SeasonalViewHolder)holder).seasonalRcv.setNestedScrollingEnabled(false);
        ((SeasonalViewHolder)holder).seasonalRcv.setHasFixedSize(true);
        ((SeasonalViewHolder)holder).seasonalRcv.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        CollectionsItemsAdapter collectionsItemsAdapter = new CollectionsItemsAdapter(context,collectionsCardModel.getCollectionsItemList());
        ((SeasonalViewHolder)holder).seasonalRcv.setAdapter(collectionsItemsAdapter);

    }


    private void initNotificationsView(RecyclerView.ViewHolder holder,int position){
        NotificationCardModel notificationCardModel = (NotificationCardModel) objects.get(position);
        ((NotificationViewHolder)holder).titleNotification.setText(notificationCardModel.getNotificationTitle().trim().toUpperCase());
        if(!notificationCardModel.getImageUrl().isEmpty()){
            if(notificationCardModel.getImageUrl().contains(".png") || notificationCardModel.getImageUrl().contains(".jpg")
                    || notificationCardModel.getImageUrl().contains(".jpeg")){
                imageLoader= helper.getImageLoader();
                imageLoader.get(notificationCardModel.getImageUrl(),
                        ImageLoader.getImageListener(((NotificationViewHolder)holder).notifyLogo,
                                R.drawable.noodles, android.R.drawable
                                        .ic_dialog_alert));


            }
        }else{
            imageLoader= helper.getImageLoader();
            imageLoader.get(context.getResources().getString(R.string.no_image),
                    ImageLoader.getImageListener(((NotificationViewHolder)holder).notifyLogo,
                            R.drawable.noodles, android.R.drawable
                                    .ic_dialog_alert));

        }

    }
}
