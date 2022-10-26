package co.edu.uan.software.tasky.controllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;

import co.edu.uan.software.tasky.entities.U09_post_etiquetas_entity;
import co.edu.uan.software.tasky.repositories.U09_post_etiquetas_repository;

@RestController
public class U09_post_etiquetas_controller {

    public String colorRandomSelector(int h) {
        ArrayList<String> colores = new ArrayList<String>();
        colores.add("Rojo");
        colores.add("Azul");
        colores.add("Amarillo");
        colores.add("Verde");
        colores.add("Morado");

        return colores.get(h);
    }

    private final U09_post_etiquetas_repository repository;

    public U09_post_etiquetas_controller(U09_post_etiquetas_repository r){
        this.repository = r;
    }
    
    @PostMapping("/etiqueta/{nombre}&{color}")
    public U09_post_etiquetas_entity createEtiquetaPro(@PathVariable String nombre, @PathVariable String color) {        
        
        if (color.equals("")){
            U09_post_etiquetas_entity e = new U09_post_etiquetas_entity(nombre, colorRandomSelector((int)(Math.random()*4)));
            repository.save(e);
            return e;
        }
        else{
            U09_post_etiquetas_entity e = new U09_post_etiquetas_entity(nombre, color);
            repository.save(e);
            return e;
        }   
    }
}
