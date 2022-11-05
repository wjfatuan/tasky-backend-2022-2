package co.edu.uan.software.tasky.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.uan.software.tasky.entities.Tarea;

public interface TareaRepository extends JpaRepository<Tarea, UUID>{
    Optional<Tarea> findByNombreTarea(String nombreTarea);
}