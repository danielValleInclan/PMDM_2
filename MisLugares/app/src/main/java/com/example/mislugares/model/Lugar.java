package com.example.mislugares.model;


import android.graphics.Bitmap;
import android.media.Image;

import java.net.URL;
import java.util.Date;

public class Lugar {
    private int id;
    private String nombre, direccion, comentario;
    private GeoPunto geoPunto;

    private double valoracion;

    private URL url;

    private Date fecha;

    private TipoLugar tipoLugar;

    private int imagen;

    public Lugar() {
    }

    public Lugar(
                 String nombre,
                 String direccion,
                 String comentario,
                 GeoPunto geoPunto,
                 double valoracion,
                 URL url,
                 Date fecha,
                 TipoLugar tipoLugar,
                 int imagen) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.comentario = comentario;
        this.geoPunto = geoPunto;
        this.valoracion = valoracion;
        this.url = url;
        this.fecha = fecha;
        this.tipoLugar = tipoLugar;
        this.imagen = imagen;
    }

    public Lugar(int id,
            String nombre,
            String direccion,
            String comentario,
            GeoPunto geoPunto,
            double valoracion,
            URL url,
            Date fecha,
            TipoLugar tipoLugar,
            int imagen) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.comentario = comentario;
        this.geoPunto = geoPunto;
        this.valoracion = valoracion;
        this.url = url;
        this.fecha = fecha;
        this.tipoLugar = tipoLugar;
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public GeoPunto getGeoPunto() {
        return geoPunto;
    }

    public void setGeoPunto(GeoPunto geoPunto) {
        this.geoPunto = geoPunto;
    }

    public double getValoracion() {
        return valoracion;
    }

    public void setValoracion(double valoracion) {
        this.valoracion = valoracion;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipoLugar() {
        return tipoLugar.toString();
    }

    public void setTipoLugar(TipoLugar tipoLugar) {
        this.tipoLugar = tipoLugar;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
