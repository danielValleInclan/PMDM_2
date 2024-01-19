package com.example.mislugares.repository;

import com.example.mislugares.model.Lugar;

import java.util.List;

public interface LugaresRepository {

    List<Lugar> getAllLugares();
    Lugar getLugarById(long id);
    void addLugar(Lugar lugar);
    void updateLugar(Lugar lugar);
    void deleteLugar(int id);
}
