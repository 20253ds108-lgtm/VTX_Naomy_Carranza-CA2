package mx.edu.utez.VTX_Naomy_Carranza_CA2.repository;

import com.examen.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
}