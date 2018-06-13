package com.cronlogy.charan.laalsa.Authentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";

    FontTextView continueBtn;

    FontEditText mobileNumber;

    Laalsa helper = Laalsa.getInstance();
    PrefManager prefManager;

    FontTextView skipButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_signup);

        skipButton=(FontTextView)findViewById(R.id.skipButton);
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this,EnterOtpActivity.class));
               // finish();
            }
        });

        prefManager = new PrefManager(this);

        continueBtn=(FontTextView)findViewById(R.id.continueBtn);
        mobileNumber=(FontEditText)findViewById(R.id.mobileNumber);

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mobileNumber.getText().toString().trim().length()!=0){
                    if(isValidMobile(mobileNumber.getText().toString().trim())){

                        sendOtp();

                       /* startActivity(new Intent(SignupActivity.this,EnterOtpActivity.class));
                        finish();
*/
                    }else{
                        Toast.makeText(SignupActivity.this,"Mobile number isn't valid",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(SignupActivity.this,"Mobile number is empty",Toast.LENGTH_LONG).show();
                }

            }
        });



    }

    private boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }

    private void sendOtp(){
        String url ="https://development.laalsa.com/ConsumerAPI-2.0.6/login/getVerificationCode";

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("deviceId", prefManager.getDeviceId(SignupActivity.this));
            jsonBody.put("apkVersion", prefManager.getApkVersion(SignupActivity.this));
            jsonBody.put("mobileNumber", mobileNumber.getText().toString().trim());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJsonRequest customJsonRequest = new CustomJsonRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String statusDesc = response.getString("statusDesc");
                    if(statusDesc.equalsIgnoreCase("success")){
                        parseAndSaveInfo(response);
                    }else{
                        Toast.makeText(SignupActivity.this,"Please try again after sometime",Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                    Toast.makeText(SignupActivity.this,error.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
        customJsonRequest.setPriority(Request.Priority.HIGH);
        helper.addToRequestQueue(customJsonRequest,"SIGNUP");

    }

    private  void parseAndSaveInfo(JSONObject response){


        try {
         String token = response.getString("token");
         String tokenType = response.getString("tokenType");
         token = tokenType +" "+token;

         String otpCode = response.getString("verificationCode");
         prefManager.setTokenId(SignupActivity.this,token);
         prefManager.setMobileNum(SignupActivity.this,mobileNumber.getText().toString().trim());
         prefManager.setOtpCode(SignupActivity.this,otpCode);


            startActivity(new Intent(SignupActivity.this,EnterOtpActivity.class));
            finish();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
