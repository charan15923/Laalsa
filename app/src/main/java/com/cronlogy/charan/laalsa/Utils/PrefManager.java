package com.cronlogy.charan.laalsa.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {


    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREFERNCE_NAME = "LAALSA";
    private static final String CANDIDATE_ID = "candidate_id";

    private static final String NOTIFICATION_COUNT = "NOTIFICATION_COUNT";

    private static final String FCM_TOKEN = "fcm_token";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static final String FORGOT_PASS = "forgotPass";

    private static final String IS_PROFILE_COMPLETE = "PROFILE";

    private static final String TOKEN_ID="token_id";

    private static final String MOBILE_NUM="mobile_number";

    private static final String DEVICE_ID = "device_Id";

    private static final String APK_VERSION="apk_version";

    private static final String OTP_CODE= "otp_Code";


    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREFERNCE_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public static void setUserLoggedIn(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERNCE_NAME, context.MODE_PRIVATE).edit();
        editor.putBoolean("userloggedin", true);
        editor.commit();
    }

    public static boolean isUserLoggedIn(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFERNCE_NAME, context.MODE_PRIVATE);
        return prefs.getBoolean("userloggedin", false);
    }


    public static void setTokenId(Context context,String token) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERNCE_NAME, context.MODE_PRIVATE).edit();
        editor.putString(TOKEN_ID, token);
        editor.commit();
    }

    public static String getTokenId(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFERNCE_NAME, context.MODE_PRIVATE);
        return prefs.getString(TOKEN_ID, null);
    }

    public static void setMobileNum(Context context,String mobileNumber){
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERNCE_NAME, context.MODE_PRIVATE).edit();
        editor.putString(MOBILE_NUM, mobileNumber);
        editor.commit();
    }

    public static String getMobileNum(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFERNCE_NAME, context.MODE_PRIVATE);
        return prefs.getString(MOBILE_NUM, null);
    }

    public static void setDeviceId(Context context,String deviceId){
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERNCE_NAME, context.MODE_PRIVATE).edit();
        editor.putString(DEVICE_ID, deviceId);
        editor.commit();
    }

    public static String getDeviceId(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFERNCE_NAME, context.MODE_PRIVATE);
        return prefs.getString(DEVICE_ID, null);
    }

    public static void setApkVersion(Context context,String apkVersion){
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERNCE_NAME, context.MODE_PRIVATE).edit();
        editor.putString(APK_VERSION, apkVersion);
        editor.commit();
    }

    public static String getApkVersion(Context context){
        SharedPreferences prefs = context.getSharedPreferences(PREFERNCE_NAME, context.MODE_PRIVATE);
        return prefs.getString(APK_VERSION, null);
    }

    public static void setOtpCode(Context context,String otp){
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERNCE_NAME, context.MODE_PRIVATE).edit();
        editor.putString(OTP_CODE, otp);
        editor.commit();
    }

    public static String getOtpCode(Context context){
        SharedPreferences prefs = context.getSharedPreferences(PREFERNCE_NAME, context.MODE_PRIVATE);
        return prefs.getString(OTP_CODE, null);
    }


}
