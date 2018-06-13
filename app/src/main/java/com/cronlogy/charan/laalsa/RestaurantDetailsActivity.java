package com.cronlogy.charan.laalsa;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;

public class RestaurantDetailsActivity extends AppCompatActivity {

    private static final String TAG = "RestaurantDetailsActivi";
    CardView fullCard,smallCard;
    AppBarLayout appBarLayout;
    boolean expanded=false,collapsed=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        fullCard=(CardView)findViewById(R.id.full_card);
        smallCard=(CardView)findViewById(R.id.smallCard);
        appBarLayout=(AppBarLayout)findViewById(R.id.app_bar);

       /* appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if(state.equals(State.COLLAPSED)){
                    collapsed=true;
                    expanded=false;
                    Log.e(TAG, "onStateChanged: COLLAPSED" );
                   *//* fullCard.setVisibility(View.GONE);
                    smallCard.setVisibility(View.VISIBLE);*//*
                }
                if(state.equals(State.EXPANDED)){
                    collapsed=false;
                    expanded=true;
                    Log.e(TAG, "onStateChanged: EXAON" );
                    *//*fullCard.setVisibility(View.VISIBLE);
                    smallCard.setVisibility(View.GONE);*//*
                }
            }


        });*/

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                float percentage = ((float)Math.abs(verticalOffset)/appBarLayout.getTotalScrollRange());
                Log.e(TAG, "onOffsetChanged:"+verticalOffset+"PER"+percentage );

                smallCard.setAlpha(percentage);
                float negPer=1-percentage;
                Log.e(TAG, "onOffsetChanged: NEG"+negPer );
                fullCard.setAlpha(negPer);

                if(fullCard.getAlpha()<0.1){
                    Log.e(TAG, "onOffsetChanged: HIDE FULL" );
                    fullCard.setVisibility(View.GONE);
                   // smallCard.setVisibility(View.VISIBLE);
                }else{
                   // smallCard.setVisibility(View.GONE);
                    fullCard.setVisibility(View.VISIBLE);
                }

            }
        });
    }

}
