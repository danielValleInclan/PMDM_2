package com.example.mislugares.repository.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mislugares.model.GeoPunto;
import com.example.mislugares.model.Lugar;
import com.example.mislugares.repository.LugaresRepository;

import java.util.ArrayList;
import java.util.List;

public class ListaLugares implements LugaresRepository {
    private static final String DATABASE_NAME = "lugares.db";
    private static final int DATABASE_VERSION = 1;

    // Nombres de las tablas y columnas
    private static final String TABLE_LUGARES = "lugares";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NOMBRE = "nombre";
    private static final String COLUMN_DIRECCION = "direccion";
    private static final String COLUMN_LATITUD = "latitud";
    private static final String COLUMN_LONGITUD = "longitud";
    private static final String COLUMN_VALORACION = "valoracion";
    private static final String COLUMN_IMAGEN = "imagen";
    private static final String COLUMN_URL = "url";
    private static final String COLUMN_COMENTARIO = "comentario";
    private static final String COLUMN_FECHA = "fecha";
    private static final String COLUMN_TIPO_LUGAR = "tipo_lugar";

    private SQLiteDatabase database;
    private SQLiteOpenHelper dbHelper;

    public ListaLugares(Context context) {
        dbHelper = new SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
            @Override
            public void onCreate(SQLiteDatabase db) {
                // Crear la tabla de lugares
                String createTableQuery = "CREATE TABLE " + TABLE_LUGARES + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NOMBRE + " TEXT, " +
                        COLUMN_DIRECCION + " TEXT, " +
                        COLUMN_LATITUD + " REAL, " +
                        COLUMN_LONGITUD + " REAL, " +
                        COLUMN_VALORACION + " REAL, " +
                        COLUMN_IMAGEN + " TEXT, " +
                        COLUMN_URL + " TEXT, " +
                        COLUMN_COMENTARIO + " TEXT, " +
                        COLUMN_FECHA + " INTEGER, " +
                        COLUMN_TIPO_LUGAR + " TEXT)";
                db.execSQL(createTableQuery);
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("DROP TABLE IF EXISTS " + TABLE_LUGARES);
                onCreate(db);
            }
        };

        database = dbHelper.getWritableDatabase();
    }




    @Override
    public List<Lugar> getAllLugares() {
        List<Lugar> lugares = new ArrayList<>();
        Cursor cursor = database.query(TABLE_LUGARES, null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Lugar lugar = new Lugar();
                lugar.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
                lugar.setNombre(cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE)));
                lugar.setDireccion(cursor.getString(cursor.getColumnIndex(COLUMN_DIRECCION)));

                GeoPunto geoPunto = new GeoPunto();
                geoPunto.setLatitud(cursor.getDouble(cursor.getColumnIndex(COLUMN_LATITUD)));
                geoPunto.setLongitud(cursor.getDouble(cursor.getColumnIndex(COLUMN_LONGITUD)));
                geoPunto.setValoracion(cursor.getDouble(cursor.getColumnIndex(COLUMN_VALORACION)));
                // Configurar otros atributos de GeoPunto según tu modelo

                lugar.setGeoPunto(geoPunto);
                lugares.add(lugar);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return lugares;
    }

    @Override
    public Lugar getLugarById() {
        return null;
    }

    @Override
    public void addLugar(Lugar lugar) {

    }

    @Override
    public void updateLugar(Lugar lugar) {

    }

    @Override
    public void deleteLugar(int id) {

    }

    // Nombre de las tablas y columnas

}
