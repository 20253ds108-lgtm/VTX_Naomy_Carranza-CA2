package mx.edu.utez.VTX_Naomy_Carranza_CA2.repository;

import mx.edu.utez.VTX_Naomy_Carranza_CA2.model.Videojuego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideojuegoRepository extends JpaRepository<Videojuego, Long> {
    List<Videojuego> findByDisponibleTrue();
}