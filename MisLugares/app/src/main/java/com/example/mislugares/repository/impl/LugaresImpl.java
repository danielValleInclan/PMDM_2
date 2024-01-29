package com.example.mislugares.repository.impl;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.mislugares.model.GeoPunto;
import com.example.mislugares.model.Lugar;
import com.example.mislugares.model.TipoLugar;
import com.example.mislugares.repository.LugaresRepository;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LugaresImpl implements LugaresRepository {
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

    public LugaresImpl(Context context) {
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




    @SuppressLint("Range")
    @Override
    public List<Lugar> getAllLugares() throws MalformedURLException {
        List<Lugar> lugares = new ArrayList<>();
        Cursor cursor = database.query(TABLE_LUGARES, null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Lugar lugar = new Lugar();
                lugar.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                lugar.setNombre(cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE)));
                lugar.setDireccion(cursor.getString(cursor.getColumnIndex(COLUMN_DIRECCION)));
                lugar.setComentario(cursor.getString(cursor.getColumnIndex(COLUMN_COMENTARIO)));
                lugar.setValoracion(cursor.getDouble(cursor.getColumnIndex(COLUMN_VALORACION)));
                lugar.setUrl(cursor.getString(cursor.getColumnIndex(COLUMN_URL)));
                lugar.setTipoLugar(TipoLugar.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_TIPO_LUGAR))));
                lugar.setImagenBytes(cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGEN)));

                GeoPunto geoPunto = new GeoPunto();
                geoPunto.setLatitud(cursor.getDouble(cursor.getColumnIndex(COLUMN_LATITUD)));
                geoPunto.setLongitud(cursor.getDouble(cursor.getColumnIndex(COLUMN_LONGITUD)));

                // Configurar otros atributos de GeoPunto según tu modelo

                lugar.setGeoPunto(geoPunto);
                lugares.add(lugar);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return lugares;
    }

    @SuppressLint("Range")
    @Override
    public Lugar getLugarById(long id) throws MalformedURLException {
        Lugar lugar = null;
        Cursor cursor = database.query(
                TABLE_LUGARES,
                null,  // Selecciona todas las columnas
                COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            lugar = new Lugar(
                    cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_DIRECCION)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_COMENTARIO)),
                    new GeoPunto(cursor.getDouble(cursor.getColumnIndex(COLUMN_LATITUD)),
                            cursor.getDouble(cursor.getColumnIndex(COLUMN_LONGITUD))),
                    cursor.getDouble(cursor.getColumnIndex(COLUMN_VALORACION)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_URL)),
                    new Date(cursor.getLong(cursor.getColumnIndex(COLUMN_FECHA))),
                    TipoLugar.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_TIPO_LUGAR))),
                    cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGEN))
            );
            cursor.close();
        }

        return lugar;
    }

    @Override
    public void addLugar(Lugar lugar) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, lugar.getNombre());
        values.put(COLUMN_DIRECCION, lugar.getDireccion());
        values.put(COLUMN_LATITUD, lugar.getGeoPunto().getLatitud());
        values.put(COLUMN_LONGITUD, lugar.getGeoPunto().getLongitud());
        values.put(COLUMN_VALORACION, lugar.getValoracion());
        values.put(COLUMN_COMENTARIO, lugar.getComentario());
        values.put(COLUMN_TIPO_LUGAR, lugar.getTipoLugar());
        values.put(COLUMN_IMAGEN, lugar.getImagenBytes());
        values.put(COLUMN_URL, String.valueOf(lugar.getUrl()));
        values.put(COLUMN_FECHA, lugar.getFecha().getTime());

        database.insert(TABLE_LUGARES, null, values);

    }

    @Override
    public void updateLugar(Lugar lugar) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, lugar.getNombre());
        values.put(COLUMN_DIRECCION, lugar.getDireccion());
        values.put(COLUMN_COMENTARIO, lugar.getComentario());
        values.put(COLUMN_LATITUD, lugar.getGeoPunto().getLatitud());
        values.put(COLUMN_LONGITUD, lugar.getGeoPunto().getLongitud());
        values.put(COLUMN_VALORACION, lugar.getValoracion());
        values.put(COLUMN_URL, lugar.getUrl());
        values.put(COLUMN_TIPO_LUGAR, lugar.getTipoLugar());
        values.put(COLUMN_IMAGEN, lugar.getImagenBytes());

        String whereClause = COLUMN_ID + " = ?";
        String[] whereArgs = {String.valueOf(lugar.getId())};

        database.update(TABLE_LUGARES, values, whereClause, whereArgs);

    }

    @Override
    public void deleteLugar(int id) {
        String whereClause = COLUMN_ID + " = ?";
        String[] whereArgs = {String.valueOf(id)};

        int rowsDeleted = database.delete(TABLE_LUGARES, whereClause, whereArgs);
        Log.d("DB_TEST", "Filas eliminadas: " + rowsDeleted);

    }

    public void close() {
        dbHelper.close();
    }
}
