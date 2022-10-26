package co.edu.uan.software.tasky.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.uan.software.tasky.entities.U09_post_etiquetas_entity;

public interface U09_post_etiquetas_repository extends JpaRepository<U09_post_etiquetas_entity, UUID>{
    
}
