package com.example.investigacionpreferencias;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class OpcionesFragment extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);

        addPreferencesFromResource(R.xml.opciones);
    }
}
