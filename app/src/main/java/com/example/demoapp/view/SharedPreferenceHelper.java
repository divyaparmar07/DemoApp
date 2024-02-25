package com.example.demoapp.view;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceHelper {
    private static final String PREF_NAME = "MyPrefs";
    private static final String KEY_DEFAULT_SMS = "default_sms";

    private final SharedPreferences preferences;

    public SharedPreferenceHelper(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public String getDefaultSms() {
        return preferences.getString(KEY_DEFAULT_SMS, "Hello! How are you?");
    }

    public void setDefaultSms(String defaultSms) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_DEFAULT_SMS, defaultSms);
        editor.apply();
    }
}
