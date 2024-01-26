package com.example.mislugares.model;

import android.graphics.Bitmap;
import android.media.Image;

import java.net.URL;
import java.util.Date;


public class GeoPunto {
    private double longitud, latitud;

    public GeoPunto() {
    }

    public GeoPunto(double longitud, double latitud) {
        this.longitud = longitud;
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }
}
