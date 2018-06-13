package com.cronlogy.charan.laalsa.Authentication;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.cronlogy.charan.laalsa.HomeActivity;
import com.cronlogy.charan.laalsa.R;
import com.cronlogy.charan.laalsa.Utils.CustomJsonRequest;
import com.cronlogy.charan.laalsa.Utils.Laalsa;
import com.cronlogy.charan.laalsa.Utils.PrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.arnaudguyon.smartfontslib.FontEditText;
import fr.arnaudguyon.smartfontslib.FontTextView;

public class PreferencesActivity extends AppCompatActivity {

    private static final String TAG = "PreferencesActivity";

    Spinner prefSpinner;
    FontTextView enjoylaalsa;

    Laalsa helper = Laalsa.getInstance();
    PrefManager prefManager;

    FontEditText userName, favDish, withGroup;

    FontTextView skipButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_preferences);

        skipButton=(FontTextView)findViewById(R.id.skipButton);
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PreferencesActivity.this,HomeActivity.class));
                // finish();
            }
        });

        prefManager = new PrefManager(this);


        prefSpinner =(Spinner)findViewById(R.id.prefSpinner);
        enjoylaalsa=(FontTextView)findViewById(R.id.enjoyLaalsa);

        userName=(FontEditText)findViewById(R.id.userName);
        favDish=(FontEditText)findViewById(R.id.favDish);
        withGroup=(FontEditText)findViewById(R.id.withGroup);



        enjoylaalsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(validateFields()){
                       // updateUserProfile();
                        navigateToHome();
                    }
            }
        });

       String[] prefArray = getResources().getStringArray(R.array.preferences_aray);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, prefArray){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v = super.getView(position, convertView, parent);

                Typeface externalFont= Typeface.createFromAsset(getAssets(), "fonts/Ubuntu-R.ttf");
                ((TextView) v).setTypeface(externalFont);

                ((TextView) v).setTextColor(getResources().getColor(R.color.white));


                switch (getResources().getDisplayMetrics().densityDpi) {
                    case DisplayMetrics.DENSITY_LOW:
                        Log.e(TAG, "getView: LDPI" );
                        ((TextView) v).setTextSize(36);
                        break;
                    case DisplayMetrics.DENSITY_MEDIUM:
                        Log.e(TAG, "getView: MDPI" );
                        ((TextView) v).setTextSize(36);
                        break;
                    case DisplayMetrics.DENSITY_HIGH:
                        Log.e(TAG, "getView: HDPI" );
                        ((TextView) v).setTextSize(36);
                        break;
                    case DisplayMetrics.DENSITY_XHIGH:
                        Log.e(TAG, "getView: XHDPI" );
                        ((TextView) v).setTextSize(36);
                        break;
                    case DisplayMetrics.DENSITY_XXHIGH:
                        Log.e(TAG, "getView: XXHDPI" );
                        ((TextView) v).setTextSize(36);
                        break;

                        default:
                            Log.e(TAG, "getView: DEF");
                            ((TextView) v).setTextSize(36);
                            break;
                }


                return v;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v =super.getDropDownView(position, convertView, parent);

                Typeface externalFont=Typeface.createFromAsset(getAssets(), "fonts/Ubuntu-M.ttf");
                ((TextView) v).setTypeface(externalFont);
                ((TextView) v).setTextColor(getResources().getColor(R.color.black));

                switch (getResources().getDisplayMetrics().densityDpi) {
                    case DisplayMetrics.DENSITY_LOW:
                        ((TextView) v).setTextSize(18);
                        break;
                    case DisplayMetrics.DENSITY_MEDIUM:
                        ((TextView) v).setTextSize(18);
                        break;
                    case DisplayMetrics.DENSITY_HIGH:
                        ((TextView) v).setTextSize(18);
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
        prefSpinner.setAdapter(dataAdapter);
    }


    private boolean validateFields(){
        if(userName.getText().toString().trim().length()!=0){
            if (favDish.getText().toString().trim().length() != 0) {

                if(withGroup.getText().toString().trim().length()!=0){
                    return true;
                }else{
                    Toast.makeText(PreferencesActivity.this,"With is empty",Toast.LENGTH_LONG).show();

                }

            }else{
                Toast.makeText(PreferencesActivity.this,"Dish is empty",Toast.LENGTH_LONG).show();

            }
        }else{
            Toast.makeText(PreferencesActivity.this,"Name is empty",Toast.LENGTH_LONG).show();
        }

        return false;
    }

    private void navigateToHome(){
        startActivity(new Intent(PreferencesActivity.this,HomeActivity.class));
        finish();
    }

    private void updateUserProfile(){
        String url="https://development.laalsa.com/ConsumerAPI-2.0.6/login/createUpdateProfile";

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("deviceId", prefManager.getDeviceId(PreferencesActivity.this));
            jsonBody.put("mobileNumber", prefManager.getMobileNum(PreferencesActivity.this));
            jsonBody.put("userName",userName.getText().toString().trim());
            jsonBody.put("profession",prefSpinner.getSelectedItem());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJsonRequest customJsonRequest = new CustomJsonRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                    navigateToHome();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                navigateToHome();
            }
        });

        customJsonRequest.setPriority(Request.Priority.HIGH);
        helper.addToRequestQueue(customJsonRequest,"USER_PROFILE_PREFS");
    }
}
