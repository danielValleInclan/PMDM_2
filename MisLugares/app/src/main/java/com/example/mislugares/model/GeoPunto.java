package com.example.mislugares.model;

import android.media.Image;

import java.net.URL;
import java.util.Date;


public class GeoPunto {
    private double longitud, latitud, valoracion;
    private Image imagen;
    private URL url;
    private String comentario;
    private Date fecha;
    private TipoLugar tipoLugar;

    public GeoPunto() {
    }

    public GeoPunto(double longitud, double latitud, double valoracion, Image imagen, URL url, String comentario, Date fecha, TipoLugar tipoLugar) {
        this.longitud = longitud;
        this.latitud = latitud;
        this.valoracion = valoracion;
        this.imagen = imagen;
        this.url = url;
        this.comentario = comentario;
        this.fecha = fecha;
        this.tipoLugar = tipoLugar;
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

    public double getValoracion() {
        return valoracion;
    }

    public void setValoracion(double valoracion) {
        this.valoracion = valoracion;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public TipoLugar getTipoLugar() {
        return tipoLugar;
    }

    public void setTipoLugar(TipoLugar tipoLugar) {
        this.tipoLugar = tipoLugar;
    }
}
