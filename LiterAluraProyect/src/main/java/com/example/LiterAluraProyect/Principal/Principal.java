package com.example.LiterAluraProyect.Principal;

import com.example.LiterAluraProyect.Model.Autor;
import com.example.LiterAluraProyect.Model.Datos;
import com.example.LiterAluraProyect.Model.DatosLibros;
import com.example.LiterAluraProyect.Model.Libro;
import com.example.LiterAluraProyect.Repository.AutorRepository;
import com.example.LiterAluraProyect.Repository.LibroRepository;
import com.example.LiterAluraProyect.Service.ConsumoAPI;
import com.example.LiterAluraProyect.Service.ConvierteDatos;

import java.util.*;
public class Principal {
    private static final Scanner teclado = new Scanner(System.in);
    private static final ConsumoAPI consumoAPI = new ConsumoAPI();
    private static final ConvierteDatos conversor = new ConvierteDatos();
    private static final String URL_BASE = "https://gutendex.com/books/";

    private static  AutorRepository autorRepository;
    private static LibroRepository libroRepository = null;

    public Principal(AutorRepository autorRepository, LibroRepository libroRepository) {
        Principal.autorRepository = autorRepository;
        Principal.libroRepository = libroRepository;
    }

    public static void muestraElMenu() {

        var json = consumoAPI.obtenerDatos(URL_BASE);
        System.out.println(json);
        var datos = conversor.obtenerDatos(json, Datos.class);
        System.out.println(datos);

        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libros por título
                    2 - Mostrar Libros registrados
                    3 - Mostrar Autores registrados
                    4 - Mostrar autores vivos en determinado año
                    5 - Mostrar libros por idioma
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    try {
                        busquedaLibros();
                    } catch (Exception e) {
                        System.out.println("Error al buscar el libro");
                    }
                    break;
                case 2:
                    getLibros();
                    break;
                case 3:
                    getAutores();
                    break;
                case 4:
                    getAutoresEnDeterminadosAnios();
                    break;
                case 5:
                    getLibrosPorIdioma();
                    break;
            }
        }
    }

    private static void getLibrosPorIdioma() {
        System.out.println("Por favor escribe el idioma que desees buscar:");
        var idioma = teclado.nextLine();
        List<Libro> libros = libroRepository.findByIdioma(idioma);
        if (libros.isEmpty()) {
            System.out.println("No se pudo encontrar un libro en el idioma: " + idioma);
        } else {
            for (Libro libro : libros) {
                System.out.println(libro.toString());
            }
        }
    }

    private static void getAutoresEnDeterminadosAnios() {
        System.out.println("Por favor escribe el año que desees buscar:");
        var anio = teclado.nextInt();
        teclado.nextLine();
        List<Autor> autors = autorRepository.findByFechaNacimientoLessThanEqualAndFechaDefuncionGreaterThanEqual(anio, anio);
        if (autors.isEmpty()) {
            System.out.println("No se pudo encontrar a un autor que haya nacido entre el año:  " + anio);
        } else {
            for (Autor autor : autors) {
                System.out.println(autor.toString());
            }
        }
    }


    private static void busquedaLibros() {
        //Buscando libros por nombre
        System.out.println("Por favor escribe el nombre del libro que desees buscar:");
        var tituloLibro = teclado.nextLine();
        var jsonLibro = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(jsonLibro, Datos.class);
        Optional<DatosLibros> libroBuscado = datosBusqueda.results().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();

        if (libroBuscado.isPresent()) {
            System.out.println("Libro encontrado: \n" + libroBuscado.get());
            Libro libroVerificado = libroRepository.findByTituloContainsIgnoreCase(libroBuscado.get().titulo());
            if (libroVerificado == null) {
                System.out.println("Libro no encontrado en la base de datos, ¿Deseas guardarlo? (S/N)");
                var respuesta = teclado.nextLine();
                if (respuesta.equalsIgnoreCase("S")) {
                    Libro libro = new Libro(libroBuscado.get());
                    libroRepository.save(libro);
                    System.out.println("Libro guardado exitosamente");
                }
            } else {
                System.out.println("Libro no encontrado");
            }
        }
    }

    private static void getLibros() {
        List<Libro> libros = libroRepository.findAll();
        try {
            if (libros.isEmpty()) {
                System.out.println("No hay libros registrados");
            } else {
                for (Libro libro : libros) {
                    System.out.println(libro.toString());
                }
            }
        } catch (Exception e) {
            System.out.println("Error al mostrar los libros");
        }
    }

    private static void getAutores() {
        List<Libro> libros = libroRepository.findAll();
        List<String> autores = libros.stream()
                .map(libro -> libro.getAutor().getNombre())
                .distinct()
                .toList();
        try {
            if (autores.isEmpty()) {
                System.out.println("No hay autores registrados");
            } else {
                for (String autor : autores) {
                    System.out.println("\n3Autor: " + autor + "\n" + "Libros: " + libros.stream()
                            .filter(libro -> libro.getAutor().getNombre().equals(autor))
                            .map(Libro::getTitulo)
                            .toList());
                }
            }
        } catch (Exception e) {
            System.out.println("Error al mostrar los autores");
        }
    }
}
