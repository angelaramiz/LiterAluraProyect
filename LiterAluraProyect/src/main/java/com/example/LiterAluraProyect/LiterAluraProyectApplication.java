package com.example.LiterAluraProyect;

import com.example.LiterAluraProyect.Principal.Principal;
import com.example.LiterAluraProyect.Repository.AutorRepository;
import com.example.LiterAluraProyect.Repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LiterAluraProyectApplication implements CommandLineRunner {
	@Autowired
	private AutorRepository autorRepository;
	@Autowired
	private LibroRepository libroRepository;


	public static void main(String[] args) {
		SpringApplication.run(LiterAluraProyectApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception{
		Principal principal = new Principal(autorRepository, libroRepository);
		Principal.muestraElMenu();
	}


}
