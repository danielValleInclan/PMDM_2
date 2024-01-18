package com.example.mislugares;

import android.media.Image;

import java.net.URL;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class GeoPunto {
    private double longitud, latitud, valoracion;
    private Image imagen;
    private URL url;
    private String comentario;
    private Date fecha;
    private TipoLugar tipoLugar;
}
