package com.cronlogy.charan.laalsa;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RatingBar;

import com.cronlogy.charan.laalsa.Authentication.PreferencesActivity;
import com.cronlogy.charan.laalsa.Authentication.SignupActivity;
import com.cronlogy.charan.laalsa.Utils.CircularImageButton;
import com.cronlogy.charan.laalsa.Utils.PrefManager;
import com.cronlogy.charan.laalsa.Utils.TintableRatingBar;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivity";

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
    CircularImageButton circularImageButton;
    RatingBar tintableRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        String androidId = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);
        int versionCode = BuildConfig.VERSION_CODE;
        String versionName = BuildConfig.VERSION_NAME;

        final PrefManager prefManager = new PrefManager(this);
        prefManager.setApkVersion(this,versionCode+"");
        prefManager.setDeviceId(this,androidId);

        Log.e(TAG, "onCreate: ID"+androidId +"\t version code "+versionCode);


        /*new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if(prefManager.isUserLoggedIn(SplashActivity.this)){
                    Intent i = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(i);
                }else{
                    Intent i = new Intent(SplashActivity.this, SignupActivity.class);
                    startActivity(i);
                }
                finish();
            }
        }, SPLASH_TIME_OUT);*/
/*
        tintableRatingBar=(RatingBar) findViewById(R.id.ratingBar);
       // tintableRatingBar.setCustomTintColors(getResources().getColor(R.color.search_tab_text),getResources().getColor(R.color.search_tab_text),getResources().getColor(R.color.starGreen));
        tintableRatingBar.setIsIndicator(true);
        tintableRatingBar.setStepSize(0.1f);
        tintableRatingBar.setRating(3.7f);

        if(tintableRatingBar.getRating()<2f){
            LayerDrawable stars = (LayerDrawable) tintableRatingBar.getProgressDrawable();
            stars.getDrawable(2).setColorFilter(getResources().getColor(R.color.starGreen), PorterDuff.Mode.SRC_ATOP);
        }else{
            LayerDrawable stars = (LayerDrawable) tintableRatingBar.getProgressDrawable();
            stars.getDrawable(2).setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        }*/

        Intent i = new Intent(SplashActivity.this, HomeActivity.class);
        startActivity(i);
        finish();



    }
}
