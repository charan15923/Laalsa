package com.cronlogy.charan.laalsa;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import fr.arnaudguyon.smartfontslib.FontTextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExploreFragment extends Fragment {

    private static final String TAG = "ExploreFragment";

    //toolbar widgets
    Spinner locationSpinner;
    FontTextView locationTV;
    ImageView dropDownIV;

    FontTextView foodExploreTab,restaurantExploreTab;
    ImageView filterIV;

    Typeface ubuntuBold,ubuntuRegular;


    public ExploreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_explore, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        locationSpinner=(Spinner)view.findViewById(R.id.locationSpinner);
        locationTV=(FontTextView) view.findViewById(R.id.locationTV);
        dropDownIV=(ImageView)view.findViewById(R.id.dropDownIV);
        filterIV=(ImageView)view.findViewById(R.id.filterIV);
        foodExploreTab=(FontTextView)view.findViewById(R.id.foodExlporeTab);
        restaurantExploreTab=(FontTextView)view.findViewById(R.id.restaurantExlporeTab);

        ubuntuBold=Typeface.createFromAsset(getActivity().getAssets(),"fonts/Ubuntu-B.ttf");
        ubuntuRegular=Typeface.createFromAsset(getActivity().getAssets(),"fonts/Ubuntu-R.ttf");


        foodExploreTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodExploreTab.setTextColor(getActivity().getResources().getColor(R.color.white));
                restaurantExploreTab.setTextColor(getActivity().getResources().getColor(R.color.white_opacity));

                foodExploreTab.setTypeface(ubuntuBold);
                restaurantExploreTab.setTypeface(ubuntuRegular);
            }
        });


        restaurantExploreTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restaurantExploreTab.setTextColor(getActivity().getResources().getColor(R.color.white));
                foodExploreTab.setTextColor(getActivity().getResources().getColor(R.color.white_opacity));

                restaurantExploreTab.setTypeface(ubuntuBold);
                foodExploreTab.setTypeface(ubuntuRegular);
            }
        });

        foodExploreTab.performClick();
        initLocationSpinner();


        filterIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SortFilterFragment dialog = new SortFilterFragment();
                dialog.show(getActivity().getFragmentManager(), "dialog");
            }
        });
    }

    private void initLocationSpinner(){
        dropDownIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationSpinner.performClick();
            }
        });

        final String[] locationArray = getActivity().getResources().getStringArray(R.array.location_array);

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

        locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                locationTV.setText(locationArray[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
