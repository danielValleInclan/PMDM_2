package com.example.mislugares;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.mislugares.model.GeoPunto;
import com.example.mislugares.model.Lugar;
import com.example.mislugares.repository.impl.ListaLugares;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListaLugares listaLugares;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GeoPunto geoPunto1 = new GeoPunto();
        Lugar lugar1 = new Lugar("España", "España", geoPunto1);

        listaLugares = new ListaLugares(this);

        listaLugares.addLugar(lugar1);

        List<Lugar> lugars = listaLugares.getAllLugares();
        for (Lugar lugar : lugars){
            Log.d("DB_TEST", "Lugar: " + lugar.getNombre() + ", Dirección " + lugar.getDireccion());
        }

    }
}