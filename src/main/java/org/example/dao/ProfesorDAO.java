package org.example.dao;

import org.example.model.Profesor;

import java.util.List;

public interface ProfesorDAO {
    void crear(Profesor profesor);
    Profesor leer(String cedula);
    void actualizar(Profesor profesor);
    void eliminar(String cedula);
    List<Profesor> listar();
}
