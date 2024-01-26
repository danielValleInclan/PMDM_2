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
import java.time.LocalDate;
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
        Date date = new Date(122, 0, 26);
        GeoPunto geoPunto1 = new GeoPunto(13.0, 13.0);

        Lugar lugar1 = new Lugar(1,
                "España",
                "arriba",
                "Mu bonito",
                geoPunto1,
                5.0,
                url,
                date,
                TipoLugar.MUSEO,
                image);

        Lugar lugar2 = new Lugar(2,
                "Croacia",
                "derecha",
                "Mu bonito",
                geoPunto1,
                7.0,
                url,
                date,
                TipoLugar.BAR,
                image);

        listaLugares = new ListaLugares(this);

        listaLugares.addLugar(lugar1);
        listaLugares.addLugar(lugar2);

        List<Lugar> lugars = null;
        try {
            lugars = listaLugares.getAllLugares();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        for (Lugar lugar : lugars){
            Log.d("DB_TEST",
                    "Lugar: " + lugar.getNombre()
                            + ", \nDirección " + lugar.getDireccion()
                            + ", \nComentario " + lugar.getComentario()
                            + ", \nLatitud " + lugar.getGeoPunto().getLatitud()
                            + ", \nLongitud " + lugar.getGeoPunto().getLongitud()
                            + ", \nValoración " + lugar.getValoracion()
                            + ", \nURL " + lugar.getUrl()
                            + " \nFecha " + lugar.getFecha()
                            + " \nTipo Lugar " + lugar.getTipoLugar()
                            + ", \nImagen: " + lugar.getImagen());
        }

        listaLugares.deleteLugar(2);

        for (Lugar lugar : lugars){
            Log.d("DB_TEST",
                    "Lugar: " + lugar.getNombre()
                            + ", \nDirección " + lugar.getDireccion()
                            + ", \nComentario " + lugar.getComentario()
                            + ", \nLatitud " + lugar.getGeoPunto().getLatitud()
                            + ", \nLongitud " + lugar.getGeoPunto().getLongitud()
                            + ", \nValoración " + lugar.getValoracion()
                            + ", \nURL " + lugar.getUrl()
                            + " \nFecha " + lugar.getFecha()
                            + " \nTipo Lugar " + lugar.getTipoLugar()
                            + ", \nImagen: " + lugar.getImagen());
        }

        listaLugares.close();
    }
}