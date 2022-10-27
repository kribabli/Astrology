package com.example.astrology.Common;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class HelperData extends Application {
    private static String SHARED_PREF_NAME1 = "Astrology";
    private SharedPreferences sharedPreferences;
    Context context;
    private SharedPreferences.Editor editor;

    public HelperData(Context context) {
        this.context = context;
    }

    public boolean getIsLogin() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME1, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("IsLoggedIn", false);
    }

    public void saveIsLogin(boolean flag) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME1, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putBoolean("IsLoggedIn", flag);
        editor.apply();
    }

    public void saveLogin(String mobile) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME1, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("mobile", mobile);
        editor.apply();
    }

    public String getMobile() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME1, Context.MODE_PRIVATE);
        return sharedPreferences.getString("mobile", "");
    }

    public void Logout() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME1, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
