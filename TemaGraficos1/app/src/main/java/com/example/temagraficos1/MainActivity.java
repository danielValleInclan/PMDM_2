package com.example.temagraficos1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instancia de GraficoView
        GraficoView graficoView = new GraficoView(this);

        // Establecer GraficoView como la vista de contenido
        setContentView(graficoView);
    }
}