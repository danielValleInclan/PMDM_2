package com.example.mislugares.repository;

import com.example.mislugares.model.Lugar;

import java.net.MalformedURLException;
import java.util.List;

public interface LugaresRepository {

    List<Lugar> getAllLugares() throws MalformedURLException;
    Lugar getLugarById(long id) throws MalformedURLException;
    void addLugar(Lugar lugar);
    void updateLugar(Lugar lugar);
    void deleteLugar(int id);
}
