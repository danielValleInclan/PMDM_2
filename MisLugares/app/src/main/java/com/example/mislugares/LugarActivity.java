package com.example.mislugares;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

    private long lugarId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Mostrar el botón de retroceso en la barra de acción

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
            lugarId = intent.getLongExtra("LUGAR_ID", -1);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lugar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == android.R.id.home) {
            // Manejar el clic en el botón de retroceso
            finish(); // Cerrar la actividad
        } else if (itemId == R.id.action_delete) {
            // Guardar el nuevo lugar
            mostrarDialogoConfirmacion();
            eliminarLugar();
        } else {
            return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void mostrarDialogoConfirmacion(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmación");
        builder.setMessage("¿Estás seguro de que deseas eliminar este lugar?");

        // Configurar el botón positivo (Sí)
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // El usuario ha confirmado, eliminar el lugar
                if (eliminarLugar()){
                    // Después de eliminar, iniciar MainActivity
                    Intent intent = new Intent(LugarActivity.this, MainActivity.class);
                    startActivity(intent);

                    // Finalizar la actividad actual (LugarActivity)
                    finish();
                }
            }
        });

        // Configurar el botón negativo (No)
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // El usuario ha cancelado, no hacer nada
            }
        });

        // Mostrar el cuadro de diálogo
        builder.show();
    }
    private boolean eliminarLugar(){
        Log.d("ELIMINAR_LUGAR", "eliminarLugar: lugarId: " + lugarId);
        lugaresImpl.deleteLugar(lugarId);
        return true;
    }
}