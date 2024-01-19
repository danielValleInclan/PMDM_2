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

        Bitmap image = BitmapFactory.decodeResource(getResources(), R.mipmap.altavoz);

        URL url;
        try {
            url = new URL("http://www.google.com");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        GeoPunto geoPunto1 = new GeoPunto(
                13.0,
                13.0,
                5.0,
                image,
                url,
                "Mu bonito",
                new Date(),
                TipoLugar.MUSEO);

        Lugar lugar1 = new Lugar("España", "arriba", geoPunto1);
        Lugar lugar2 = new Lugar("Marruecos", "abajo", geoPunto1);
        Lugar lugar3 = new Lugar("Canada", "izquierda", geoPunto1);

        listaLugares = new ListaLugares(this);

        listaLugares.addLugar(lugar1);
        listaLugares.addLugar(lugar2);
        listaLugares.addLugar(lugar3);

        List<Lugar> lugars = listaLugares.getAllLugares();
        for (Lugar lugar : lugars){
            Log.d("DB_TEST", "Lugar: " +
                    lugar.getNombre() + ", Dirección " +
                    lugar.getDireccion() + ", Imagen: " +
                    lugar.getGeoPunto().getImagen() + ", URL: " +
                    lugar.getGeoPunto().getUrl());
        }
    }
}