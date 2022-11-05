package co.edu.uan.software.tasky.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uan.software.tasky.entities.Tarea;
import co.edu.uan.software.tasky.entities.Usuario;
import co.edu.uan.software.tasky.repositories.TareaRepository;


@RestController
@RequestMapping(path = "/tareas")
public class TareaController {

    private final TareaRepository repo;
    
    TareaController(TareaRepository r){
        this.repo=r;
    }
    /**
     * Creates a new tarea in de database
     * @param tarea the new tarea
     * @return Returns the tarea 
     */
    @PostMapping
    public ResponseEntity<Tarea> createtarea(@RequestBody Tarea tarea){
        tarea.setEstado("PENDIENTE");
        if(tarea.getNombreTarea() == null || tarea.getUsuario() == null ||
           tarea.getEstado() == null || tarea.getEtiquetas() == null || 
           tarea.getFechaCierre() == null || tarea.getFechaCreacion() == null ||
           tarea.getNombreTarea().isEmpty() || 
           tarea.getEstado().isEmpty() || tarea.getEtiquetas().isEmpty() || 
           tarea.getNombreTarea().isBlank()  ||
           tarea.getEstado().isBlank() || tarea.getEtiquetas().isBlank()){
            
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        if(repo.findByNombreTarea(tarea.getNombreTarea()).isPresent()){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
       
        String nombreTarea = tarea.getNombreTarea();
       
        if(nombreTarea.length() >= 120){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(this.repo.save(tarea), HttpStatus.CREATED);
    }
    
    /**
     * Returns a list qwhit all the users.
     * 
     * @return Returns all the tareas in database
     */
    @GetMapping
    public List<Tarea> finAlltareas(){
        return this.repo.findAll();
    }

    @GetMapping("/{name}")
    public ResponseEntity<Tarea> findTarea(@PathVariable("name") String name) {
        Usuario  users = new Usuario();
        Tarea tarea = new Tarea();
        if (tarea.getUsuario().equals(users.getNombreUsuario())){
            return this.repo.findByNombreTarea(name)
                .map(result -> new ResponseEntity<>(result, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
    
        }
}
