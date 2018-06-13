package com.cronlogy.charan.laalsa.Authentication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.cronlogy.charan.laalsa.R;
import com.cronlogy.charan.laalsa.Utils.CustomJsonRequest;
import com.cronlogy.charan.laalsa.Utils.Laalsa;
import com.cronlogy.charan.laalsa.Utils.PrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import fr.arnaudguyon.smartfontslib.FontEditText;
import fr.arnaudguyon.smartfontslib.FontTextView;

public class EnterOtpActivity extends AppCompatActivity {

    private static final String TAG = "EnterOtpActivity";

    FontTextView continueBtn;

    FontEditText otpDigitone,otpDigitTwo,otpDigitThree,otpDigitFour,otpDigitFive,otpDigitSix;

    Laalsa helper = Laalsa.getInstance();
    PrefManager prefManager;
    String otp;

    FontTextView skipButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_enter_otp);

        skipButton=(FontTextView)findViewById(R.id.skipButton);
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EnterOtpActivity.this,PreferencesActivity.class));
                // finish();
            }
        });

        prefManager = new PrefManager(this);

        otpDigitone=(FontEditText)findViewById(R.id.otpDigitOne);
        otpDigitTwo=(FontEditText)findViewById(R.id.otpDigitTwo);
        otpDigitThree=(FontEditText)findViewById(R.id.otpDigitThree);
        otpDigitFour=(FontEditText)findViewById(R.id.otpDigitFour);
        otpDigitFive=(FontEditText)findViewById(R.id.otpDigitFive);
        otpDigitSix=(FontEditText)findViewById(R.id.otpDigitSix);

        otpDigitone.addTextChangedListener(new GenericTextWatcher(otpDigitone));
        otpDigitTwo.addTextChangedListener(new GenericTextWatcher(otpDigitTwo));
        otpDigitThree.addTextChangedListener(new GenericTextWatcher(otpDigitThree));
        otpDigitFour.addTextChangedListener(new GenericTextWatcher(otpDigitFour));
        otpDigitFive.addTextChangedListener(new GenericTextWatcher(otpDigitFive));
        otpDigitSix.addTextChangedListener(new GenericTextWatcher(otpDigitSix));



        continueBtn=(FontTextView)findViewById(R.id.continueBtn);
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otp = otpDigitone.getText().toString().trim()+
                        otpDigitTwo.getText().toString().trim()+
                        otpDigitThree.getText().toString().trim()+
                        otpDigitFour.getText().toString().trim()+
                        otpDigitFive.getText().toString().trim()+
                        otpDigitSix.getText().toString().trim();

                Log.e(TAG, "onClick: "+otp);

                if(otp.length() == 6){

                    validateOtp();
                   // navigateToPrefs();

                }else{
                    Toast.makeText(EnterOtpActivity.this,"Invalid Otp",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public class GenericTextWatcher implements TextWatcher {
        private View view;
        private GenericTextWatcher(View view)
        {
            this.view = view;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // TODO Auto-generated method stub
            String text = editable.toString();
            switch(view.getId())
            {

                case R.id.otpDigitOne:
                    if(text.length()==1)
                        otpDigitTwo.requestFocus();
                    break;
                case R.id.otpDigitTwo:
                    if(text.length()==1)
                        otpDigitThree.requestFocus();
                    break;
                case R.id.otpDigitThree:
                    if(text.length()==1)
                        otpDigitFour.requestFocus();
                    break;
                case R.id.otpDigitFour:
                    if(text.length()==1)
                        otpDigitFive.requestFocus();
                    break;
                case R.id.otpDigitFive:
                    if(text.length()==1)
                        otpDigitSix.requestFocus();
                    break;
                case R.id.otpDigitSix:
                    if(text.length()==1)
                        hideKeyboard(EnterOtpActivity.this);

                    break;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void validateOtp(){
        String url = "https://development.laalsa.com/ConsumerAPI-2.0.6/login/registerDevice";
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("deviceId", prefManager.getDeviceId(EnterOtpActivity.this));
            jsonBody.put("apkVersion", prefManager.getApkVersion(EnterOtpActivity.this));
            jsonBody.put("mobileNumber", prefManager.getMobileNum(EnterOtpActivity.this));
            jsonBody.put("verificationCode", otp);
            jsonBody.put("deviceType", "Android");
            jsonBody.put("appInstanceId", "adddwwdqqf23");
            jsonBody.put("gcmId", "adddwwdqqf23");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJsonRequest customJsonRequest = new CustomJsonRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String statusDesc = response.getString("statusDesc");
                    if(statusDesc.equalsIgnoreCase("success")){
                       navigateToPrefs();
                    }else{
                        Toast.makeText(EnterOtpActivity.this,"Please try again after sometime",Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EnterOtpActivity.this,""+error.getLocalizedMessage(),Toast.LENGTH_LONG).show();

            }
        });
        customJsonRequest.setPriority(Request.Priority.HIGH);
        helper.addToRequestQueue(customJsonRequest,"REGISTERDEVICE");

    }

    private void navigateToPrefs(){
        startActivity(new Intent(EnterOtpActivity.this,PreferencesActivity.class));
        finish();
    }
}
