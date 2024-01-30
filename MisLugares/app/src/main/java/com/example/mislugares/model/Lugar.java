package com.example.mislugares.model;


import java.io.Serializable;
import java.util.Date;

public class Lugar implements Serializable {
    private int id;
    private String nombre, direccion, comentario;
    private GeoPunto geoPunto;

    private double valoracion;

    private String url;

    private Date fecha;

    private TipoLugar tipoLugar;

    private byte[] imagenBytes;

    public Lugar() {
    }

    public Lugar(
                 String nombre,
                 String direccion,
                 String comentario,
                 GeoPunto geoPunto,
                 double valoracion,
                 String url,
                 Date fecha,
                 TipoLugar tipoLugar,
                 byte[] imagenBytes) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.comentario = comentario;
        this.geoPunto = geoPunto;
        this.valoracion = valoracion;
        this.url = url;
        this.fecha = fecha;
        this.tipoLugar = tipoLugar;
        this.imagenBytes = imagenBytes;
    }

    public Lugar(int id,
            String nombre,
            String direccion,
            String comentario,
            GeoPunto geoPunto,
            double valoracion,
            String url,
            Date fecha,
            TipoLugar tipoLugar,
            byte[] imagenBytes) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.comentario = comentario;
        this.geoPunto = geoPunto;
        this.valoracion = valoracion;
        this.url = url;
        this.fecha = fecha;
        this.tipoLugar = tipoLugar;
        this.imagenBytes = imagenBytes;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
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

    public byte[] getImagenBytes() {
        return imagenBytes;
    }

    public void setImagenBytes(byte[] imagenBytes) {
        this.imagenBytes = imagenBytes;
    }
}
