package com.example.mislugares;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;

import com.example.mislugares.model.GeoPunto;
import com.example.mislugares.model.Lugar;
import com.example.mislugares.model.TipoLugar;
import com.example.mislugares.repository.impl.ListaLugares;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListaLugares listaLugares;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int image = R.drawable.altavoz;

        URL url;
        try {
            url = new URL("http://www.google.com");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        GeoPunto geoPunto1 = new GeoPunto(13.0, 13.0);

        Lugar lugar1 = new Lugar(1, "España", "arriba", "Mu bonito", geoPunto1, 5.0, url, null, TipoLugar.MUSEO, image);

        listaLugares = new ListaLugares(this);

        listaLugares.addLugar(lugar1);

        List<Lugar> lugars = listaLugares.getAllLugares();
        for (Lugar lugar : lugars){
            Log.d("DB_TEST", "Lugar: " +
                    lugar.getNombre() + ", Dirección " +
                    lugar.getDireccion() + ", Imagen: " +
                    lugar.getImagen() + ", URL: " +
                    lugar.getUrl());
        }
    }
}