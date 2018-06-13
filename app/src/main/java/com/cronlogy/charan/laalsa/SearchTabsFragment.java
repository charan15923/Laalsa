package com.cronlogy.charan.laalsa;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.cronlogy.charan.laalsa.Adapters.SearchFoodAdapter;
import com.cronlogy.charan.laalsa.Adapters.SearchRestaurantAdapter;
import com.cronlogy.charan.laalsa.Adapters.SearchTrendingNearYouAdapter;
import com.cronlogy.charan.laalsa.Models.SearchDataModel;
import com.cronlogy.charan.laalsa.Models.SearchTrendingNearYouParentModel;
import com.cronlogy.charan.laalsa.Utils.CustomJsonRequest;
import com.cronlogy.charan.laalsa.Utils.Laalsa;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fr.arnaudguyon.smartfontslib.FontEditText;
import fr.arnaudguyon.smartfontslib.FontTextView;

import static com.cronlogy.charan.laalsa.HomeActivity.hideKeyboard;


public class SearchTabsFragment extends Fragment {

    private static final String TAG = "SearchTabsFragment";

    //toolbar widgets
    Spinner locationSpinner;
    ImageView dropDownIV;

    FontTextView cancelSearch;
    FontEditText searchEditText;
    LinearLayout searchLayout;
    boolean isSearchScreenShown=true;
    boolean isTrendingNearYouShown=false;
    boolean areTabsShown=false;
    boolean isFoodOrRes=true;
    FrameLayout notificationLayout;



    FontTextView foodTab,restaurantTab;
    View foodBorder,restaurantBorder;
    RecyclerView recyclerView;
    ArrayList<String> arrayList = new ArrayList<>();
    SearchFoodAdapter searchFoodAdapter;
    SearchRestaurantAdapter searchRestaurantAdapter;
    SearchTrendingNearYouAdapter searchTrendingNearYouAdapter;
    LinearLayout tabsLayout,tabsLayoutBorder;

    ArrayList<SearchTrendingNearYouParentModel> searchTrendingNearYouList;

    Laalsa helper = Laalsa.getInstance();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_tabs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       final HomeActivity homeActivity =(HomeActivity)getActivity();

        locationSpinner=(Spinner)view.findViewById(R.id.locationSpinner);
        dropDownIV=(ImageView)view.findViewById(R.id.dropDownIV);
        cancelSearch=(FontTextView)view.findViewById(R.id.cancelSearch);
        notificationLayout=(FrameLayout)view.findViewById(R.id.notificationLayout);

        cancelSearch.setVisibility(View.GONE);

        tabsLayout=(LinearLayout)view.findViewById(R.id.tabsLayout);
        tabsLayoutBorder=(LinearLayout)view.findViewById(R.id.tabsLayoutBorder);


        searchEditText=(FontEditText)view.findViewById(R.id.searchEditText);
        searchLayout=(LinearLayout)view.findViewById(R.id.searchLayout);

        dropDownIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationSpinner.performClick();
            }
        });

        cancelSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        showKeyboard();
        cancelSearch.setVisibility(View.VISIBLE);
        notificationLayout.setVisibility(View.INVISIBLE);

        searchTrendingNearYouList = new ArrayList<>();
        getTrendingNearYouData("");
        recyclerView = (RecyclerView)view.findViewById(R.id.searchRcv);
        setupTrendingNearYouRecyclerView();




        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

                if(s.toString().length()==0 || s.toString().length()<2){
                    if(!isTrendingNearYouShown){
                        tabsLayout.setVisibility(View.GONE);
                        tabsLayoutBorder.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);

                        // homeActivity.hideKeyboard(getActivity());

                        searchTrendingNearYouList = new ArrayList<>();
                        getTrendingNearYouData(s.toString());
                        recyclerView = (RecyclerView)view.findViewById(R.id.searchRcv);
                        setupTrendingNearYouRecyclerView();
                        isTrendingNearYouShown=true;
                    }else{
                        tabsLayout.setVisibility(View.GONE);
                        tabsLayoutBorder.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        recyclerView = (RecyclerView)view.findViewById(R.id.searchRcv);
                        setupTrendingNearYouRecyclerView();

                    }

                }else{
                    if(!areTabsShown){
                        tabsLayout.setVisibility(View.VISIBLE);
                        tabsLayoutBorder.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.VISIBLE);
                        foodTab.performClick();
                        isFoodOrRes=true;
                        areTabsShown=true;
                    }else{
                        tabsLayout.setVisibility(View.VISIBLE);
                        tabsLayoutBorder.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.VISIBLE);
                        if(isFoodOrRes){
                            getFoodData(s.toString().trim());
                        }else{
                            getRestaurantData(s.toString().trim());
                        }
                    }

                    isTrendingNearYouShown=false;



                }
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


        foodTab=(FontTextView)view.findViewById(R.id.foodTab);
        restaurantTab=(FontTextView)view.findViewById(R.id.restaurantTab);

        foodBorder=(View)view.findViewById(R.id.foodBorder);
        restaurantBorder=(View)view.findViewById(R.id.restaurantBorder);

        final Typeface ubuntuBold = Typeface.createFromAsset(getActivity().getAssets(),
                "fonts/Ubuntu-B.ttf");

        final Typeface ubuntuRegular = Typeface.createFromAsset(getActivity().getAssets(),
                "fonts/Ubuntu-R.ttf");


        foodTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodTab.setTypeface(ubuntuBold);
                restaurantTab.setTypeface(ubuntuRegular);
                foodTab.setTextColor(getActivity().getResources().getColor(R.color.search_tab_text_selected));
                restaurantTab.setTextColor(getActivity().getResources().getColor(R.color.search_tab_text));
                foodBorder.setVisibility(View.VISIBLE);
                restaurantBorder.setVisibility(View.INVISIBLE);
                isFoodOrRes=true;
                ArrayList<SearchDataModel> searchDataModels = new ArrayList<>();
                setupFoodRecyclerView(searchDataModels);
            }
        });



        restaurantTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFoodOrRes=false;

                restaurantTab.setTypeface(ubuntuBold);
                foodTab.setTypeface(ubuntuRegular);
                restaurantTab.setTextColor(getActivity().getResources().getColor(R.color.search_tab_text_selected));
                foodTab.setTextColor(getActivity().getResources().getColor(R.color.search_tab_text));
                restaurantBorder.setVisibility(View.VISIBLE);
                foodBorder.setVisibility(View.INVISIBLE);

                ArrayList<SearchDataModel> searchDataModels = new ArrayList<>();
                setupRestaurantRecyclerView(searchDataModels);
            }
        });
    }

    private void showKeyboard(){
        InputMethodManager inputMethodManager =
                (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInputFromWindow(
                searchEditText.getApplicationWindowToken(),
                InputMethodManager.SHOW_FORCED, 0);
    }

    private void getTrendingNearYouData(String searchKey){

        String url ="https://development.laalsa.com/ConsumerAPI-2.0.6/search/search";

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("deviceId","2A6A39A60EAB4B11968D888E33623685");
            jsonObject.put("latitude","17.433058");
            jsonObject.put("longitude","78.410179");
            jsonObject.put("mobileNumber","9100755503");
            jsonObject.put("locationId","5a4ca003a2ad1a6b890078f8");
            jsonObject.put("key",searchKey.trim());
            jsonObject.put("type","");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJsonRequest customJsonRequest = new CustomJsonRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.e(TAG, "onResponse: "+response.toString());
                String statusDesc = null;
                try {
                    statusDesc = response.getString("statusDesc");

                    if(statusDesc.equalsIgnoreCase("success")){

                        Log.e(TAG, "onResponse: SUCCESS" );
                        parseTrendingData(response.getJSONArray("trending"));
                        parseNearYouData(response.getJSONArray("nearYou"));

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
                Log.e(TAG, "onErrorResponse: "+error.toString() );
            }
        });

        customJsonRequest.setPriority(Request.Priority.HIGH);
        helper.addToRequestQueue(customJsonRequest,"SEARCH_EMPTY");
    }

    private void parseTrendingData(JSONArray trendingArray){
        ArrayList<SearchDataModel> trendingList = new ArrayList<>();

            try {
                for(int i=0;i<trendingArray.length();i++){

                    JSONObject trendingObject = trendingArray.getJSONObject(i);
                trendingList.add(new SearchDataModel(
                        trendingObject.getString("restId"),
                        trendingObject.getString("name"),
                        trendingObject.getString("mmId"),
                        trendingObject.getString("image"),
                        trendingObject.getString("image_thumb"),
                        trendingObject.getBoolean("driveInInd"),
                        trendingObject.getInt("deliveryTime"),
                        trendingObject.getString("popularItems"),
                        trendingObject.getBoolean("closed"),
                        trendingObject.getInt("nearYou"),
                        trendingObject.getInt("totalRestaurants"),
                        trendingObject.getString("type")
                        ));

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


        searchTrendingNearYouList.add(new SearchTrendingNearYouParentModel(R.drawable.ic_trending_search,"Trending",trendingList,null));
       // Log.e(TAG, "parseTrendingData: "+searchTrendingNearYouList.get(0).getTrendingList().toString());
    }

    private void parseNearYouData(JSONArray nearYouArray){
        ArrayList<SearchDataModel> nearYouList = new ArrayList<>();

        try {
            for(int i=0;i<nearYouArray.length();i++){

                JSONObject trendingObject = nearYouArray.getJSONObject(i);
                nearYouList.add(new SearchDataModel(
                        trendingObject.getString("restId"),
                        trendingObject.getString("name"),
                        trendingObject.getString("mmId"),
                        trendingObject.getString("image"),
                        trendingObject.getString("image_thumb"),
                        trendingObject.getBoolean("driveInInd"),
                        trendingObject.getInt("deliveryTime"),
                        trendingObject.getString("popularItems"),
                        trendingObject.getBoolean("closed"),
                        trendingObject.getInt("nearYou"),
                        trendingObject.getInt("totalRestaurants"),
                        trendingObject.getString("type")
                ));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        searchTrendingNearYouList.add(new SearchTrendingNearYouParentModel(R.drawable.ic_near_you_search,"Near You",null,nearYouList));
       // Log.e(TAG, "parseTrendingData: "+searchTrendingNearYouList.get(1).getNearYouList().toString());

    }

    private void setupTrendingNearYouRecyclerView(){
        tabsLayout.setVisibility(View.GONE);
        tabsLayoutBorder.setVisibility(View.GONE);

        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        searchTrendingNearYouAdapter = new SearchTrendingNearYouAdapter(getActivity(),searchTrendingNearYouList);
        Log.e(TAG, "setupTrendingNearYouRecyclerView: "+searchTrendingNearYouList.size());
        recyclerView.setAdapter(searchTrendingNearYouAdapter);
    }


    private void getFoodData(String searchKey){
        String url ="https://development.laalsa.com/ConsumerAPI-2.0.6/search/search";

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("deviceId","2A6A39A60EAB4B11968D888E33623685");
            jsonObject.put("latitude","17.433058");
            jsonObject.put("longitude","78.410179");
            jsonObject.put("mobileNumber","9100755503");
            jsonObject.put("locationId","5a4ca003a2ad1a6b890078f8");
            jsonObject.put("key",searchKey.trim());
            jsonObject.put("type","Food");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJsonRequest customJsonRequest = new CustomJsonRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String statusDesc = null;
                try {
                    statusDesc = response.getString("statusDesc");

                    if(statusDesc.equalsIgnoreCase("success")){

                        Log.e(TAG, "onResponse: SUCCESS" );

                        parseTabsData(response.getJSONArray("searchResults"));

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

            }
        });

        customJsonRequest.setPriority(Request.Priority.HIGH);
        helper.addToRequestQueue(customJsonRequest,"SEARCH_FOOD");
    }

    private void getRestaurantData(String searchKey){
        String url ="https://development.laalsa.com/ConsumerAPI-2.0.6/search/search";

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("deviceId","2A6A39A60EAB4B11968D888E33623685");
            jsonObject.put("latitude","17.433058");
            jsonObject.put("longitude","78.410179");
            jsonObject.put("mobileNumber","9100755503");
            jsonObject.put("locationId","5a4ca003a2ad1a6b890078f8");
            jsonObject.put("key",searchKey.trim());
            jsonObject.put("type","Restaurant");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJsonRequest customJsonRequest = new CustomJsonRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String statusDesc = null;
                try {
                    statusDesc = response.getString("statusDesc");

                    if(statusDesc.equalsIgnoreCase("success")){

                        Log.e(TAG, "onResponse: SUCCESS" );
                        parseTabsData(response.getJSONArray("searchResults"));


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

            }
        });

        customJsonRequest.setPriority(Request.Priority.HIGH);
        helper.addToRequestQueue(customJsonRequest,"SEARCH_RESTAURANTS");
    }


    private void parseTabsData(JSONArray tabsArray){
        ArrayList<SearchDataModel> tabsList = new ArrayList<>();

        try {
            for(int i=0;i<tabsArray.length();i++){

                JSONObject trendingObject = tabsArray.getJSONObject(i);
                tabsList.add(new SearchDataModel(
                        trendingObject.getString("restId"),
                        trendingObject.getString("name"),
                        trendingObject.getString("mmId"),
                        trendingObject.getString("image"),
                        trendingObject.getString("image_thumb"),
                        trendingObject.getBoolean("driveInInd"),
                        trendingObject.getInt("deliveryTime"),
                        trendingObject.getString("popularItems"),
                        trendingObject.getBoolean("closed"),
                        trendingObject.getInt("nearYou"),
                        trendingObject.getInt("totalRestaurants"),
                        trendingObject.getString("type")
                ));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e(TAG, "parseTabsData: "+isFoodOrRes);
        if(isFoodOrRes){

            setupFoodRecyclerView(tabsList);
        }else{
            Log.e(TAG, "parseTabsData: " );
            setupRestaurantRecyclerView(tabsList);
        }
    }

    private void setupFoodRecyclerView(ArrayList<SearchDataModel> arrayList){
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        searchFoodAdapter = new SearchFoodAdapter(getActivity(),arrayList);
        recyclerView.setAdapter(searchFoodAdapter);
    }

    private void setupRestaurantRecyclerView(ArrayList<SearchDataModel> arrayList){
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        searchRestaurantAdapter = new SearchRestaurantAdapter(getActivity(),arrayList);
        recyclerView.setAdapter(searchRestaurantAdapter);
    }

}
