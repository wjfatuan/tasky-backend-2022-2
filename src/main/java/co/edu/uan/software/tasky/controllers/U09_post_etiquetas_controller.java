package co.edu.uan.software.tasky.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uan.software.tasky.entities.U09_post_etiquetas_entity;

@RestController
public class U09_post_etiquetas_controller {
    
    @PostMapping("/etiqueta")
    public U09_post_etiquetas_entity etiqueta() {

        return new U09_post_etiquetas_entity();
    }






}
