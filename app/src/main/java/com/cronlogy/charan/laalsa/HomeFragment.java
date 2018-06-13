package com.cronlogy.charan.laalsa;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.cronlogy.charan.laalsa.Adapters.HomeScreenParentAdapter;
import com.cronlogy.charan.laalsa.Authentication.SignupActivity;
import com.cronlogy.charan.laalsa.Models.CollectionsCardModel;
import com.cronlogy.charan.laalsa.Models.CollectionsItemModel;
import com.cronlogy.charan.laalsa.Models.DishesCardModel;
import com.cronlogy.charan.laalsa.Models.DishesItemsModel;
import com.cronlogy.charan.laalsa.Models.FireSaleCardModel;
import com.cronlogy.charan.laalsa.Models.FireSaleItemsModel;
import com.cronlogy.charan.laalsa.Models.MustTryDishesModel;
import com.cronlogy.charan.laalsa.Models.NotificationCardModel;
import com.cronlogy.charan.laalsa.Models.OffersCardModel;
import com.cronlogy.charan.laalsa.Models.OffersItemModel;
import com.cronlogy.charan.laalsa.Models.RestaurantCardModel;
import com.cronlogy.charan.laalsa.Models.RestaurantItemModel;
import com.cronlogy.charan.laalsa.Models.SeasonCardModel;
import com.cronlogy.charan.laalsa.Models.SortOderModel;
import com.cronlogy.charan.laalsa.Utils.CustomJsonRequest;
import com.cronlogy.charan.laalsa.Utils.Laalsa;
import com.cronlogy.charan.laalsa.Utils.PrefManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import fr.arnaudguyon.smartfontslib.FontEditText;
import fr.arnaudguyon.smartfontslib.FontTextView;

import static com.cronlogy.charan.laalsa.HomeActivity.hideKeyboard;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";


    //toolbar widgets
    Spinner locationSpinner;
    ImageView dropDownIV;

    FontTextView cancelSearch;
    FontEditText searchEditText;
    LinearLayout searchLayout;



    boolean isSearchScreenShown=false;

    FrameLayout notificationLayout;




    Laalsa helper = Laalsa.getInstance();
    PrefManager prefManager;

    ArrayList<String> sectionIdsList;

    //home arrays
    ArrayList<SortOderModel> sortOdeList;
    SeasonCardModel seasonCardModel;
    FireSaleCardModel fireSaleCardModel;
    DishesCardModel dishesCardModel;
    RestaurantCardModel restaurantCardModel;
    CollectionsCardModel collectionsCardModel;
    OffersCardModel offersCardModel;
    NotificationCardModel notificationCardModel;
    private ArrayList<Object> objects;
    HomeScreenParentAdapter homeScreenParentAdapter;

    RecyclerView  parentRcv;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       final HomeActivity homeActivity = (HomeActivity)getActivity();

        prefManager = new PrefManager(getActivity());

        Log.e(TAG, "onViewCreated: ");

        locationSpinner=(Spinner)view.findViewById(R.id.locationSpinner);
        dropDownIV=(ImageView)view.findViewById(R.id.dropDownIV);
        cancelSearch=(FontTextView)view.findViewById(R.id.cancelSearch);
        notificationLayout=(FrameLayout)view.findViewById(R.id.notificationLayout);

        cancelSearch.setVisibility(View.GONE);


        searchEditText=(FontEditText)view.findViewById(R.id.searchEditText);
        searchLayout=(LinearLayout)view.findViewById(R.id.searchLayout);

        searchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* searchEditText.setFocusable(true);
                searchEditText.setFocusableInTouchMode(true);
                cancelSearch.setVisibility(View.VISIBLE);
                notificationLayout.setVisibility(View.INVISIBLE);
                searchEditText.requestFocus();*/

                Log.e(TAG, "onClick: " );

                if(!isSearchScreenShown){
                    Log.e(TAG, "onClick: "+isSearchScreenShown);
                    isSearchScreenShown=true;
                    homeActivity.loadSearchFragment();
                }else{
                    Log.e(TAG, "afterTextChanged:ELSE "+isSearchScreenShown);

                }
            }
        });


        dropDownIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationSpinner.performClick();
            }
        });

        cancelSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchEditText.setFocusable(false);
                searchEditText.setFocusableInTouchMode(false);
                cancelSearch.setVisibility(View.GONE);
                notificationLayout.setVisibility(View.VISIBLE);
                hideKeyboard(getActivity());
                searchEditText.getText().clear();
                if(isSearchScreenShown){
                    homeActivity.popSearchFragment();
                    isSearchScreenShown=false;
                }

            }
        });

        searchEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e(TAG, "onClick: SE" );
                if(!isSearchScreenShown){
                    Log.e(TAG, "onClick: "+isSearchScreenShown);
                    isSearchScreenShown=true;
                    homeActivity.loadSearchFragment();
                }else{
                    Log.e(TAG, "afterTextChanged:ELSE "+isSearchScreenShown);

                }

            }
        });

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        String[] locationArray = getActivity().getResources().getStringArray(R.array.location_array);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, locationArray){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v = super.getView(position, convertView, parent);

                Typeface externalFont= Typeface.createFromAsset(getActivity().getAssets(), "fonts/Ubuntu-R.ttf");
                ((TextView) v).setTypeface(externalFont);

                ((TextView) v).setTextColor(getResources().getColor(R.color.white));


                switch (getResources().getDisplayMetrics().densityDpi) {
                    case DisplayMetrics.DENSITY_LOW:
                        Log.e(TAG, "getView: LDPI" );
                        ((TextView) v).setTextSize(14);
                        break;
                    case DisplayMetrics.DENSITY_MEDIUM:
                        Log.e(TAG, "getView: MDPI" );
                        ((TextView) v).setTextSize(16);
                        break;
                    case DisplayMetrics.DENSITY_HIGH:
                        Log.e(TAG, "getView: HDPI" );
                        ((TextView) v).setTextSize(16);
                        break;
                    case DisplayMetrics.DENSITY_XHIGH:
                        Log.e(TAG, "getView: XHDPI" );
                        ((TextView) v).setTextSize(18);
                        break;
                    case DisplayMetrics.DENSITY_XXHIGH:
                        Log.e(TAG, "getView: XXHDPI" );
                        ((TextView) v).setTextSize(16);
                        break;

                    default:
                        Log.e(TAG, "getView: DEF");
                        ((TextView) v).setTextSize(16);
                        break;
                }


                return v;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v =super.getDropDownView(position, convertView, parent);

                Typeface externalFont=Typeface.createFromAsset(getActivity().getAssets(), "fonts/Ubuntu-M.ttf");
                ((TextView) v).setTypeface(externalFont);
                ((TextView) v).setTextColor(getResources().getColor(R.color.black));

                switch (getResources().getDisplayMetrics().densityDpi) {
                    case DisplayMetrics.DENSITY_LOW:
                        ((TextView) v).setTextSize(14);
                        break;
                    case DisplayMetrics.DENSITY_MEDIUM:
                        ((TextView) v).setTextSize(16);
                        break;
                    case DisplayMetrics.DENSITY_HIGH:
                        ((TextView) v).setTextSize(16);
                        break;
                    case DisplayMetrics.DENSITY_XHIGH:
                        ((TextView) v).setTextSize(18);
                        break;
                    case DisplayMetrics.DENSITY_XXHIGH:
                        ((TextView) v).setTextSize(16);
                        break;
                }

                return v;
            }
        };
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(dataAdapter);

        notificationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ChangeAddressActivity.class));
            }
        });

        parentRcv=(RecyclerView)view.findViewById(R.id.parentRcv);
        parentRcv.setHasFixedSize(true);
        parentRcv.setNestedScrollingEnabled(false);
        parentRcv.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));


        getSectionIds();
    }

    private  void getSectionIds(){
        Log.e(TAG, "getSectionIds: ");

        String url ="https://development.laalsa.com/ConsumerAPI-2.0.6/home/getSections";

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("locationId","5a4ca003a2ad1a6b890078f8" );
            jsonBody.put("mobileNumber", "8374240521");
            jsonBody.put("latitude", "25.2346");
            jsonBody.put("longitude", "78.233333");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJsonRequest customJsonRequest = new CustomJsonRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String statusDesc = null;
                try {
                    statusDesc = response.getString("statusDesc");

                    if(statusDesc.equalsIgnoreCase("success")){

                        storeSectionIds(response);

                    }else{
                        Toast.makeText(getActivity(),"Please try again after sometime",Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(),"Please try again after sometime",Toast.LENGTH_LONG).show();
                    return;
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),""+error.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });

        customJsonRequest.setPriority(Request.Priority.HIGH);
        helper.addToRequestQueue(customJsonRequest,"GET_SECTION_IDS");
    }


    private void storeSectionIds(JSONObject response){

        Log.e(TAG, "storeSectionIds: " );

        sectionIdsList = new ArrayList<>();
        sectionIdsList.clear();

        try {
            JSONObject homeScreen = response.getJSONObject("homeScreen");
            JSONArray services = homeScreen.getJSONArray("services");
            for(int i=0;i<services.length();i++){
                JSONObject section = services.getJSONObject(i);
                JSONArray sectionIdsArray = section.getJSONArray("sectionIds");
                for(int j=0;j<sectionIdsArray.length();j++){
                    sectionIdsList.add(sectionIdsArray.getString(j));
                }
            }

            getHomeScreenData(sectionIdsList);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(),"Please try again after sometime",Toast.LENGTH_LONG).show();
            return;
        }
    }

    private void getHomeScreenData(ArrayList<String> sectionIdsList){
        String url ="https://development.laalsa.com/ConsumerAPI-2.0.6/home/homeScreen";

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("locationId","5a4ca003a2ad1a6b890078f8" );
            jsonBody.put("mobileNumber", "8374240521");
            jsonBody.put("latitude", "25.2346");
            jsonBody.put("longitude", "78.233333");
            JSONArray sectionIds = new JSONArray();
            for(int i=0;i<sectionIdsList.size();i++){
                sectionIds.put(sectionIdsList.get(i));
            }
            jsonBody.put("sectionIds",sectionIds);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJsonRequest customJsonRequest = new CustomJsonRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                String statusDesc = null;
                try {
                    statusDesc = response.getString("statusDesc");

                    if(statusDesc.equalsIgnoreCase("success")){

                        parseHomeScreenData(response);

                    }else{
                        Toast.makeText(getActivity(),"Please try again after sometime",Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(),"Please try again after sometime",Toast.LENGTH_LONG).show();
                    return;
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onResponse: FAILED");
                Log.e(TAG, "onErrorResponse: "+error.getMessage()+ "NET "+error.networkResponse + error.toString());

                Toast.makeText(getActivity(),""+error.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });

        int MY_SOCKET_TIMEOUT_MS=10*1000;

        customJsonRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        customJsonRequest.setShouldCache(false);
        customJsonRequest.setPriority(Request.Priority.HIGH);
        helper.addToRequestQueue(customJsonRequest,"GET_HOME_DATA");
    }


    private void parseHomeScreenData(JSONObject response){
        sortOdeList= new ArrayList<>();
        try {
            JSONArray sectionsArray = response.getJSONArray("sections");
            for(int i=0;i<sectionsArray.length();i++){
                JSONObject section = sectionsArray.getJSONObject(i);
                String type =section.getString("type");
                String title= section.getString("title");

                if(type.equalsIgnoreCase("notification_card") && title.equalsIgnoreCase("Our Collections")){
                    type="collection_card";
                }
                sortOdeList.add(new SortOderModel(
                        section.getString("sectionId"),type,
                        section.getInt("sortOrder"),section.getString("title")
                ));
            }

            sortOdeList.sort(new Comparator<SortOderModel>() {
                @Override
                public int compare(SortOderModel o1, SortOderModel o2) {
                    return Integer.compare(o1.getSortNumber(), o2.getSortNumber());
                }
            });

            objects = new ArrayList<>();


            for(int i=0;i<sortOdeList.size();i++){
                SortOderModel sortOderModel = sortOdeList.get(i);

                if(sortOderModel.getCardType().equalsIgnoreCase("season_card")){
                        parseSeasonalData(sectionsArray.getJSONObject(i));
                }

                if(sortOderModel.getCardType().equalsIgnoreCase("firesale_card")){
                    parseFireSaleData(sectionsArray.getJSONObject(i));

                }

                if(sortOderModel.getCardType().equalsIgnoreCase("dishes_card")){
                    parseDishesData(sectionsArray.getJSONObject(i));

                }

                if(sortOderModel.getCardType().equalsIgnoreCase("restaurant_card")){
                    parseRestaurantsData(sectionsArray.getJSONObject(i));
                }

                if(sortOderModel.getCardType().equalsIgnoreCase("offers_card")){
                    parseOffersData(sectionsArray.getJSONObject(i));

                }

                if(sortOderModel.getCardType().equalsIgnoreCase("collection_card")){
                    parseCollectionsData(sectionsArray.getJSONObject(i));

                }

                if(sortOderModel.getCardType().equalsIgnoreCase("notification_card")){
                    parseNotificationData(sectionsArray.getJSONObject(i));

                }
            }


            //setuprecyclerview

            homeScreenParentAdapter = new HomeScreenParentAdapter(getActivity(),objects);
            parentRcv.setAdapter(homeScreenParentAdapter);
            homeScreenParentAdapter.notifyDataSetChanged();
            Log.e(TAG, "parseHomeScreenData: "+objects.size() );

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(),"Please try again after sometime",Toast.LENGTH_LONG).show();
            return;
        }
    }

    private void parseSeasonalData(JSONObject seasonSection){
        seasonCardModel = new SeasonCardModel();
        ArrayList<MustTryDishesModel> mustTryDishesList = new ArrayList<>();
        try {
            seasonCardModel.setSectionID(seasonSection.getString("sectionId"));
            seasonCardModel.setHeading(seasonSection.getString("heading"));
            seasonCardModel.setListTitle(seasonSection.getString("title"));
            seasonCardModel.setDescription(seasonSection.getString("description"));
            JSONArray items = seasonSection.getJSONArray("items");
            for(int i=0;i<items.length();i++){
                JSONObject item = items.getJSONObject(i);
                mustTryDishesList.add(new MustTryDishesModel(
                        item.getString("title"),
                        item.getString("imageUrl"),
                        item.getString("itemId"),
                        item.getString("tag"),
                        item.getBoolean("veg")
                ));
            }
            seasonCardModel.setItemsList(mustTryDishesList);

            objects.add(seasonCardModel);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseFireSaleData(JSONObject seasonFireSale){
        fireSaleCardModel = new FireSaleCardModel();
        ArrayList<FireSaleItemsModel> fireSaleItemsList = new ArrayList<>();
        try {
            fireSaleCardModel.setSectionID(seasonFireSale.getString("sectionId"));
            fireSaleCardModel.setCardTitle(seasonFireSale.getString("title"));
            fireSaleCardModel.setDescription(seasonFireSale.getString("description"));
            fireSaleCardModel.setInformation(seasonFireSale.getString("information"));
            fireSaleCardModel.setImageUrl(seasonFireSale.getString("imageUrl"));
            fireSaleCardModel.setLogoUrl(seasonFireSale.getString("logoUrl"));
            JSONArray items = seasonFireSale.getJSONArray("items");
            for(int i=0;i<items.length();i++){
                JSONObject item = items.getJSONObject(i);
                fireSaleItemsList.add(new FireSaleItemsModel(
                        item.getString("title"),
                        item.getString("imageUrl"),
                        item.getString("restaurant"),
                        item.getString("offerPrice"),
                        item.getString("unitPrice"),
                        item.getString("tag"),
                        item.getString("fireSaleId"),
                        item.getString("menuId"),
                        item.getString("restId"),
                        item.getString("parentRestId"),
                        item.getString("availability"),
                        item.getBoolean("veg")
                ));
            }
            fireSaleCardModel.setFireSaleItemsList(fireSaleItemsList);

            objects.add(fireSaleCardModel);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseDishesData(JSONObject dishesSection){
        dishesCardModel = new DishesCardModel();
        ArrayList<DishesItemsModel> dishesItemsList = new ArrayList<>();
        try {
            dishesCardModel.setSectionID(dishesSection.getString("sectionId"));
            dishesCardModel.setCardTitle(dishesSection.getString("title"));
            dishesCardModel.setDescription(dishesSection.getString("description"));
            JSONArray items = dishesSection.getJSONArray("items");
            for(int i=0;i<items.length();i++){
                JSONObject item = items.getJSONObject(i);
                dishesItemsList.add(new DishesItemsModel(
                        item.getString("title"),
                        item.getString("imageUrl"),
                        item.getString("restaurant"),
                        item.getString("price"),
                        item.getInt("rating"),
                        item.getInt("deliveryTime"),
                        item.getString("menuId"),
                        item.getString("restId"),
                        item.getString("parentRestId"),
                        item.getString("tag"),
                        item.getBoolean("veg")
                ));
            }
            dishesCardModel.setDishesItemsList(dishesItemsList);

            objects.add(dishesCardModel);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseRestaurantsData(JSONObject restaurantsSection){
        restaurantCardModel = new RestaurantCardModel();
        ArrayList<RestaurantItemModel> restaurantItemList = new ArrayList<>();
        try {
            restaurantCardModel.setSectionID(restaurantsSection.getString("sectionId"));
            restaurantCardModel.setCardTitle(restaurantsSection.getString("title"));
            restaurantCardModel.setDescription(restaurantsSection.getString("description"));
            JSONArray items = restaurantsSection.getJSONArray("items");
            for(int i=0;i<items.length();i++){
                JSONObject item = items.getJSONObject(i);
                JSONObject address = item.getJSONObject("address");
                restaurantItemList.add(new RestaurantItemModel(
                        item.getString("title"),
                        item.getString("description"),
                        item.getString("imageUrl"),
                        item.getInt("rating"),
                        item.getInt("deliveryTime"),
                        item.getString("logo"),
                        address.getString("addressFlag"),
                        address.getString("address1"),
                        address.getString("address2"),
                        address.getString("city"),
                        address.getString("state"),
                        address.getString("country"),
                        address.getString("pincode"),
                        item.getString("cuisine"),
                        item.getString("closeTime"),
                        item.getString("restId"),
                        item.getString("tag"),
                        item.getString("restType"),
                        item.getBoolean("veg")
                ));
            }
            restaurantCardModel.setRestaurantItemList(restaurantItemList);
            objects.add(restaurantCardModel);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseOffersData(JSONObject offersSection){

        offersCardModel = new OffersCardModel();
        ArrayList<OffersItemModel> offersItemList = new ArrayList<>();
        try {
            offersCardModel.setSectionID(offersSection.getString("sectionId"));
            offersCardModel.setCardTitle(offersSection.getString("title"));
            offersCardModel.setDescription(offersSection.getString("description"));
            JSONArray items = offersSection.getJSONArray("items");
            for(int i=0;i<items.length();i++){
                JSONObject item = items.getJSONObject(i);
                offersItemList.add(new OffersItemModel(
                        item.getString("title"),
                        item.getString("imageUrl"),
                        item.getString("description"),
                        item.getString("promotionId"),
                        item.getString("tag")
                ));
            }
            offersCardModel.setOffersItemList(offersItemList);

            objects.add(offersCardModel);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseCollectionsData(JSONObject collectionsSection){
        collectionsCardModel = new CollectionsCardModel();
        ArrayList<CollectionsItemModel> collectionsItemList = new ArrayList<>();
        try {
            collectionsCardModel.setSectionID(collectionsSection.getString("sectionId"));
            collectionsCardModel.setCardTitle(collectionsSection.getString("title"));
            collectionsCardModel.setDescription(collectionsSection.getString("description"));
            JSONArray items = collectionsSection.getJSONArray("items");
            for(int i=0;i<items.length();i++){
                JSONObject item = items.getJSONObject(i);
                collectionsItemList.add(new CollectionsItemModel(
                        item.getString("title"),
                        item.getString("description"),
                        item.getString("imageUrl"),
                        item.getString("availability")
                ));
            }
            collectionsCardModel.setCollectionsItemList(collectionsItemList);

            objects.add(collectionsCardModel);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseNotificationData(JSONObject notificationSection){
        notificationCardModel = new NotificationCardModel();

        try {
            notificationCardModel.setSectionID(notificationSection.getString("sectionId"));
            notificationCardModel.setNotificationTitle(notificationSection.getString("title"));
            notificationCardModel.setDescription(notificationSection.getString("description"));
            notificationCardModel.setImageUrl(notificationSection.getString("imageUrl"));


            objects.add(notificationCardModel);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        isSearchScreenShown=false;
    }
}
