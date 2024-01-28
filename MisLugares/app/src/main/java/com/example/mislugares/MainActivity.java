package com.example.mislugares;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.mislugares.model.GeoPunto;
import com.example.mislugares.model.Lugar;
import com.example.mislugares.model.TipoLugar;
import com.example.mislugares.repository.impl.LugaresImpl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LugaresImpl lugaresImpl;
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

        Lugar lugar1 = new Lugar(
                "España",
                "arriba",
                "Mu bonito",
                geoPunto1,
                5.0,
                url,
                date,
                TipoLugar.MUSEO,
                image);

        Lugar lugar2 = new Lugar(
                "Croacia",
                "derecha",
                "Mu bonito",
                geoPunto1,
                7.0,
                url,
                date,
                TipoLugar.BAR,
                image);

        lugaresImpl = new LugaresImpl(this);

        // Obtener la lista de lugares antes de borrar
        List<Lugar> lugarsBefore = null;
        try {
            lugarsBefore = lugaresImpl.getAllLugares();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        // Limpiar la base de datos
        for (Lugar l : lugarsBefore){
            lugaresImpl.deleteLugar((int) l.getId());
        }

        // Obtener la lista de lugares después de borrar
        List<Lugar> lugarsAfter = null;
        try {
            lugarsAfter = lugaresImpl.getAllLugares();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        // Imprimir la información de los lugares después de borrar
        for (Lugar lugar : lugarsAfter){
            Log.d("DB_TEST", "Lugar después de borrar: " + lugar.getNombre());
        }

        lugaresImpl.addLugar(lugar1);
        lugaresImpl.addLugar(lugar2);

        try {
            lugarsAfter = lugaresImpl.getAllLugares();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        int lastId = 0;

        for (Lugar lugar : lugarsAfter){
            Log.d("DB_TEST",
                    "Id: " + lugar.getId() +
                    " \nLugar: " + lugar.getNombre()
                            + ", \nDirección " + lugar.getDireccion()
                            + ", \nComentario " + lugar.getComentario()
                            + ", \nLatitud " + lugar.getGeoPunto().getLatitud()
                            + ", \nLongitud " + lugar.getGeoPunto().getLongitud()
                            + ", \nValoración " + lugar.getValoracion()
                            + ", \nURL " + lugar.getUrl()
                            + " \nFecha " + lugar.getFecha()
                            + " \nTipo Lugar " + lugar.getTipoLugar()
                            + ", \nImagen: " + lugar.getImagen());
            lastId = lugar.getId();
        }

        lugar1.setId(lastId);
        lugaresImpl.updateLugar(lugar1);

        try {
            lugarsAfter = lugaresImpl.getAllLugares();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        for (Lugar lugar : lugarsAfter){
            Log.d("DB_TEST",
                    "Id: " + lugar.getId() +
                            " \nLugar: " + lugar.getNombre()
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

        lugaresImpl.close();
    }
}