package com.example.investigacionpreferencias;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class OpcionesFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);

        addPreferencesFromResource(R.xml.opciones);
    }
}
