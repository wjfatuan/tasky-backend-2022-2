package co.edu.uan.software.tasky.controllers;

import java.util.ArrayList;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uan.software.tasky.entities.EtiquetasEntity;
import co.edu.uan.software.tasky.repositories.EtiquetasRepository;

@RestController
public class EtiquetasController {
    // Repositorio y su constructor
    private final EtiquetasRepository repository;

    public EtiquetasController(EtiquetasRepository r){
        this.repository = r;
    }
    
    // ArrayList con la lista de colores a seleccionar cuando el usuario no elige ninguna
    public String colorRandomSelector(int h) {
        ArrayList<String> colores = new ArrayList<String>();
        colores.add("Rojo");
        colores.add("Azul");
        colores.add("Amarillo");
        colores.add("Verde");
        colores.add("Morado");

        return colores.get(h);
    }
    
    /*
        POST creacion de etiquetas junto con su color
        Ejemplo de creacion de etiqueta por medio de la URL:
            /etiqueta/Comida&Rojo/
            /etiqueta/Hogar&Verde/
            /etiqueta/Laboral&/  *Aqui se autocompleta el color aleatoriamente
    */
    @PostMapping("/etiqueta/{nombre}&{color}")
    public EtiquetasEntity createEtiquetaPro(@PathVariable String nombre, @PathVariable String color) {        
        
        // Si el usuario deja en blanco el espacio de "color" este sera autocompletado aleatoriamente
        if (color.equals("")){
            EtiquetasEntity e = new EtiquetasEntity(nombre, colorRandomSelector((int)(Math.random()*4)));
            repository.save(e);
            return e;
        }
        else{
            EtiquetasEntity e = new EtiquetasEntity(nombre, color);
            repository.save(e);
            return e;
        }   
    }
}
