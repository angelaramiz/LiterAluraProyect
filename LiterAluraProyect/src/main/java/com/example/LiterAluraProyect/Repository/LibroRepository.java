package com.example.LiterAluraProyect.Repository;

import com.example.LiterAluraProyect.Model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    //Encontrar nombre de libro ignorando mayusculas y minusculas
    Libro findByTituloContainsIgnoreCase(String titulo);
    //Encontrar libros por idioma
    List<Libro> findByIdioma(String idioma);
}
