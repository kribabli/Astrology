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

    public void saveLogin(String mobile, String userName, String gender, String birthDate, String birthTime, String birthPlace) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME1, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("mobile", mobile);
        editor.putString("userName", userName);
        editor.putString("gender", gender);
        editor.putString("birthDate", birthDate);
        editor.putString("birthTime", birthTime);
        editor.putString("birthPlace", birthPlace);
        editor.apply();
    }

    public String getMobile() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME1, Context.MODE_PRIVATE);
        return sharedPreferences.getString("mobile", "");
    }

    public String getUserName() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME1, Context.MODE_PRIVATE);
        return sharedPreferences.getString("userName", "");
    }

    public String getGender() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME1, Context.MODE_PRIVATE);
        return sharedPreferences.getString("gender", "");
    }

    public String getBirthDate() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME1, Context.MODE_PRIVATE);
        return sharedPreferences.getString("birthDate", "");
    }

    public String getBirthTime() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME1, Context.MODE_PRIVATE);
        return sharedPreferences.getString("birthTime", "");
    }

    public String getBirthPlace() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME1, Context.MODE_PRIVATE);
        return sharedPreferences.getString("birthPlace", "");
    }

    public void Logout() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME1, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
