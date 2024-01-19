package com.example.mislugares.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lugar {
    private long id;
    private String nombre, direccion;
    private GeoPunto geoPunto;
}
