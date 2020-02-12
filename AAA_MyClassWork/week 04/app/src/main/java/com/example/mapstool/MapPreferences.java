package com.example.mapstool;

import android.preference.PreferenceActivity;
import android.os.Bundle;

public class MapPreferences extends PreferenceActivity{

    public void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }

}
