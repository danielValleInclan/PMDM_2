package com.example.mislugares;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lugar {
    private String nombre, direccion;
    private GeoPunto geoPunto;
}
