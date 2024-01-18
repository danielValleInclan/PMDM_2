package com.example.investigacionpreferencias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private Button btnPreferencias;
    private Button btnObtenerPreferencias;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPreferencias = findViewById(R.id.BtnPreferencias);
        btnObtenerPreferencias = findViewById(R.id.ObtenerPreferencias);

        btnPreferencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OpcionesFragment.class));
            }
        });

        btnObtenerPreferencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref =             PreferenceManager.getDefaultSharedPreferences(
                        MainActivity.this);
                Log.i("", "Opción 1: " + pref.getBoolean("opcion1", false));
                Log.i("", "Opción 2: " + pref.getString("opcion2", ""));
                Log.i("", "Opción 3: " + pref.getString("opcion3", ""));
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}