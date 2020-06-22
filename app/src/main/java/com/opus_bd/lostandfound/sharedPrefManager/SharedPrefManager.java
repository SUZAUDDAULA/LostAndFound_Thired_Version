package com.opus_bd.lostandfound.sharedPrefManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SharedPrefManager {
    public static final String SHARED_PREF_NAME = "LostandFound";
    private static SharedPrefManager mInstance;
    private Context mCtx;
    private static final String KEY_TOKEN = "token";
    private static final String KEY_OTP = "otp";
    private static final String KEY_USER = "User";
    public static final String BEARER = "Bearer ";
    public static final String KEY_State = "state";


    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public void saveToken(String token) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_TOKEN, token);
        editor.apply();
    }

    public void clearToken() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,
                Context.MODE_PRIVATE);
        sharedPreferences.edit().remove(KEY_TOKEN).apply();
    }

    public String getToken() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,
                Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_TOKEN, null);
    }

    public void saveotp(String token) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_OTP, token);
        editor.apply();
    }

    public void clearotp() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,
                Context.MODE_PRIVATE);
        sharedPreferences.edit().remove(KEY_OTP).apply();
    }

    public String getotp() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,
                Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_OTP, null);
    }

    public void saveUser(String token) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER, token);
        editor.apply();
    }

    public void clearUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,
                Context.MODE_PRIVATE);
        sharedPreferences.edit().remove(KEY_USER).apply();
    }

    public String getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,
                Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER, null);
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,
                Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_TOKEN, null) != null;
    }

    /*public void logout(Context context) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        Intent intent = new Intent(context, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }*/

    /*public void saveSalesToCart(ArrayList<SaleModel> saleModels) {
        Gson gson = new Gson();
        String tempOrders = gson.toJson(saleModels);

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_ORDERS, tempOrders);
        editor.apply();
    }*/

    /*public ArrayList<SaleModel> getSavedSales() {
        List<SaleModel> tempOrders = new ArrayList<>();
1111111111111111111111111111
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,
                Context.MODE_PRIVATE);

        String stringOrders = sharedPreferences.getString(KEY_ORDERS, null);
        try {
            Gson gson = new Gson();
            SaleModel[] checkOutModels = gson.fromJson(stringOrders,
                    SaleModel[].class);

            tempOrders = Arrays.asList(checkOutModels);
            tempOrders = new ArrayList<>(tempOrders);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (ArrayList<SaleModel>) tempOrders;
    }*/


}