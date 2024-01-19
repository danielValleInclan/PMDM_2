package com.example.mislugares.model;


public class Lugar {
    private long id;
    private String nombre, direccion;
    private GeoPunto geoPunto;

    public Lugar() {
    }

    public Lugar(String nombre, String direccion, GeoPunto geoPunto) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.geoPunto = geoPunto;
    }

    public Lugar(long aLong, String string, String string1, double aDouble, double aDouble1, double aDouble2, String string2, String string3, String string4, String string5) {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public GeoPunto getGeoPunto() {
        return geoPunto;
    }

    public void setGeoPunto(GeoPunto geoPunto) {
        this.geoPunto = geoPunto;
    }
}
