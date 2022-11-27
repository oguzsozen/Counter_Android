package com.example.mycounter;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

public class SettingsClass {

    int CurrentNumber, UpperLimit, LowerLimit;
    boolean upperSound, lowerSound, upperVib, lowerVib;

    static SettingsClass settingsClass;

    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private SettingsClass(Context context) {
        this.context = context;
        sharedPreferences= context.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static SettingsClass getInstance(Context context) {
        if(settingsClass == null) {
            settingsClass = new SettingsClass(context);
        }

        return settingsClass;
    }

    public void saveValues(){
        editor.putInt("CurrentNumber", CurrentNumber);
        editor.putInt("UpperLimit", UpperLimit);
        editor.putInt("LowerLimit", LowerLimit);
        editor.putBoolean("upperSound", upperSound);
        editor.putBoolean("lowerSound", lowerSound);
        editor.putBoolean("upperVib", upperVib);
        editor.putBoolean("lowerVib", lowerVib);
        editor.commit();
    }

    public void loadValues(){
        CurrentNumber = sharedPreferences.getInt("CurrentNumber", 0);
        UpperLimit = sharedPreferences.getInt("UpperLimit", 20);
        LowerLimit = sharedPreferences.getInt("LowerLimit", 0);
        upperSound = sharedPreferences.getBoolean("upperSound", true);
        lowerSound = sharedPreferences.getBoolean("lowerSound", false);
        upperVib = sharedPreferences.getBoolean("upperVib", true);
        lowerVib = sharedPreferences.getBoolean("lowerVib", false);
    }
}
