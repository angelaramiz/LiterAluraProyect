package com.example.LiterAluraProyect.Service;

public interface IConvierteDatos {
    <T>T obtenerDatos(String json, Class<T> clase);
}
