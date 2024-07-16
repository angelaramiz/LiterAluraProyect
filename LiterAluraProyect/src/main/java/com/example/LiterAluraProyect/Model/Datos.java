package com.example.LiterAluraProyect.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public record Datos(
        List<DatosLibros> results
) {

}