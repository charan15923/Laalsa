package com.cronlogy.charan.laalsa;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import fr.arnaudguyon.smartfontslib.FontEditText;
import fr.arnaudguyon.smartfontslib.FontTextView;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

    private Handler mHandler;


    //bottom navigation widgets
    LinearLayout laalsaLayout,exploreLayout,turanLayout,dealsLayout,accountLayout;
    ImageView laalsaIV,exploreIV,dealsIV,accountIV;
    FontTextView laalsaTV,exploreTV,dealsTV,accountTV;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       /* requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/

        setContentView(R.layout.activity_home);



        mHandler = new Handler();

        setupBottomNavigationView();



    }

    private void setupBottomNavigationView() {
        laalsaLayout=(LinearLayout)findViewById(R.id.laalsa_ll);
        exploreLayout=(LinearLayout)findViewById(R.id.explore_ll);
        turanLayout=(LinearLayout)findViewById(R.id.turan_ll);
        dealsLayout=(LinearLayout)findViewById(R.id.deals_ll);
        accountLayout=(LinearLayout)findViewById(R.id.account_ll);

        laalsaIV=(ImageView)findViewById(R.id.laalsa_tab);
        exploreIV=(ImageView)findViewById(R.id.explore_tab);
        dealsIV=(ImageView)findViewById(R.id.deals_tab);
        accountIV=(ImageView)findViewById(R.id.account_tab);

        laalsaTV=(FontTextView)findViewById(R.id.laalsa_tab_tv);
        exploreTV=(FontTextView)findViewById(R.id.explore_tab_tv);
        dealsTV=(FontTextView)findViewById(R.id.deals_tab_tv);
        accountTV=(FontTextView)findViewById(R.id.account_tab_tv);

        laalsaLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelectionTabs();
                Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Ubuntu-B.ttf");

                laalsaIV.setImageDrawable(getResources().getDrawable(R.drawable.icon_laalsa_tab_selected));
                laalsaTV.setTextColor(getResources().getColor(R.color.red));
                laalsaTV.setTypeface(typeface);

                loadHomeFragment();
            }
        });

        exploreLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelectionTabs();
                Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Ubuntu-B.ttf");

                exploreIV.setImageDrawable(getResources().getDrawable(R.drawable.icon_explore_tab_selected));
                exploreTV.setTextColor(getResources().getColor(R.color.red));
                exploreTV.setTypeface(typeface);

                loadExploreFragment();
            }
        });

        turanLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelectionTabs();
            }
        });

        dealsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelectionTabs();
                Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Ubuntu-B.ttf");

                dealsIV.setImageDrawable(getResources().getDrawable(R.drawable.icon_deals_tab_selected));
                dealsTV.setTextColor(getResources().getColor(R.color.red));
                dealsTV.setTypeface(typeface);

            }
        });

        accountLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelectionTabs();
                Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Ubuntu-B.ttf");

                accountIV.setImageDrawable(getResources().getDrawable(R.drawable.icon_account_tab_selected));
                accountTV.setTextColor(getResources().getColor(R.color.red));
                accountTV.setTypeface(typeface);

            }
        });

        laalsaLayout.performClick();
    }

    private void loadExploreFragment() {

        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = new ExploreFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.add(R.id.content_frame,fragment);
                fragmentTransaction.replace(R.id.content_frame, fragment, fragment.getTag());
                fragmentTransaction.addToBackStack(fragment.getTag());
                fragmentTransaction.commit();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }
    }


    private void clearSelectionTabs(){
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Ubuntu-R.ttf");


        laalsaIV.setImageDrawable(getResources().getDrawable(R.drawable.icon_laalsa_tab));
        laalsaTV.setTextColor(getResources().getColor(R.color.tab_text_not_selected));
        laalsaTV.setTypeface(typeface);

        exploreIV.setImageDrawable(getResources().getDrawable(R.drawable.icon_explore_tab));
        exploreTV.setTextColor(getResources().getColor(R.color.tab_text_not_selected));
        exploreTV.setTypeface(typeface);

        dealsIV.setImageDrawable(getResources().getDrawable(R.drawable.icon_deals_tab));
        dealsTV.setTextColor(getResources().getColor(R.color.tab_text_not_selected));
        dealsTV.setTypeface(typeface);

        accountIV.setImageDrawable(getResources().getDrawable(R.drawable.icon_account_tab));
        accountTV.setTextColor(getResources().getColor(R.color.tab_text_not_selected));
        accountTV.setTypeface(typeface);
    }


    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void loadSearchFragment(){



        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = new SearchTabsFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.add(R.id.content_frame,fragment);
                fragmentTransaction.replace(R.id.content_frame, fragment, fragment.getTag());
                fragmentTransaction.addToBackStack(fragment.getTag());
                fragmentTransaction.commit();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }
    }

    public void popSearchFragment(){
        getSupportFragmentManager().popBackStackImmediate();

    }

    private void loadHomeFragment(){

        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = new HomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.add(R.id.content_frame,fragment);
                fragmentTransaction.replace(R.id.content_frame, fragment, fragment.getTag());
                fragmentTransaction.addToBackStack(fragment.getTag());
                fragmentTransaction.commit();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }
    }
}
