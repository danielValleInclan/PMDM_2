package com.example.mislugares;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mislugares.model.GeoPunto;
import com.example.mislugares.model.Lugar;
import com.example.mislugares.model.TipoLugar;
import com.example.mislugares.repository.impl.LugaresImpl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    LugaresImpl lugaresImpl;
    private ListView listView;
    private List<Lugar> listaLugares;
    private LugarAdapter lugarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lugaresImpl = new LugaresImpl(this);

        listView = findViewById(R.id.listViewLugares);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtener el id del lugar seleccionado
                Lugar lugar = listaLugares.get(position);
                Intent intent = new Intent(MainActivity.this, LugarActivity.class);
                Log.d("INTENT", "onItemClick: id -> " + lugar.getId());
                intent.putExtra("LUGAR_ID",(long) lugar.getId());

                // Iniciar la actividad
                startActivity(intent);
            }
        });

        //limpiarBD();

    }

    @Override
    protected void onResume() {
        super.onResume();
        actualizarListaLugares();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            // Operación de inserción, por ejemplo, abrir una nueva actividad para agregar un nuevo lugar
            // o mostrar un cuadro de diálogo para la entrada de datos.
            Intent intent = new Intent(this, InsertActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private List<Lugar> obtenerListaLugares() {
        List<Lugar> listaLugares = null;
        try {
            listaLugares = lugaresImpl.getAllLugares();
        } catch (MalformedURLException e) {
            e.printStackTrace(); // Imprime el rastreo de la pila para obtener más información sobre el error
            throw new RuntimeException("Error al obtener la lista de lugares", e);
        }
        return listaLugares;
    }

    private void actualizarListaLugares() {
        listaLugares = obtenerListaLugares();
        lugarAdapter = new LugarAdapter(this, R.layout.list_item_layout, listaLugares);
        listView.setAdapter(lugarAdapter);
    }


    private void limpiarBD(){
        for (Lugar l : obtenerListaLugares()){
            lugaresImpl.deleteLugar(l.getId());
        }
    }
}