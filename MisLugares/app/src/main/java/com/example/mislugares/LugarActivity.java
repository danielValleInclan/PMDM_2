package com.example.mislugares;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.mislugares.model.Lugar;
import com.example.mislugares.repository.LugaresRepository;
import com.example.mislugares.repository.impl.LugaresImpl;
import com.example.mislugares.util.ImageUtils;

import java.net.MalformedURLException;

public class LugarActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView tvNombre;
    private TextView tvComentario;
    private TextView tvUrl;
    private TextView tvTipoLugar;
    private RatingBar ratingBar;
    private TextView tvFecha;
    private LugaresRepository lugaresImpl;

    private Lugar lugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugar);

        lugaresImpl = new LugaresImpl(this);

        imageView = findViewById(R.id.imageView);
        tvNombre = findViewById(R.id.textViewNombre);
        tvComentario = findViewById(R.id.textViewComentario);
        tvUrl = findViewById(R.id.textViewUrl);
        tvTipoLugar = findViewById(R.id.textViewTipoLugar);
        ratingBar = findViewById(R.id.ratingBar);
        tvFecha = findViewById(R.id.textViewFecha);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("LUGAR_ID")) {
            long lugarId = intent.getLongExtra("LUGAR_ID", -1);
            Log.d("INTENT", "onCreate: lugarId -> " + lugarId);

            if (lugarId != -1){
                try {
                    lugar = lugaresImpl.getLugarById(lugarId);
                    Log.d("INTETENT", "onCreate: lugar id de bd -> " + lugar.getId());
                    tvNombre.setText(lugar.getNombre());
                    tvComentario.setText(lugar.getComentario());
                    tvUrl.setText(lugar.getUrl());
                    tvTipoLugar.setText(lugar.getTipoLugar());
                    ratingBar.setRating((float) lugar.getValoracion());

                    tvFecha.setText(lugar.getFecha().toString());

                    ImageUtils.mostrarImagenDesdeByteArray(lugar.getImagenBytes(), imageView);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}