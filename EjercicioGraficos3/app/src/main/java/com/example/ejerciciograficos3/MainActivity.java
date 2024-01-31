package com.example.ejerciciograficos3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MiQuintoGrafico miQuintoGrafico = new MiQuintoGrafico(this);
        setContentView(miQuintoGrafico);

        //CanvasDividido2 canvasDividido2 = new CanvasDividido2(this);
        //setContentView(canvasDividido2);

        /*CanvasDivididoView canvasDivididoView = new CanvasDivididoView(this);
        setContentView(canvasDivididoView);*/
    }
}