package mx.edu.utez.VTX_Naomy_Carranza_CA2.service;

import mx.edu.utez.VTX_Naomy_Carranza_CA2.dto.PrestamoDTO;
import mx.edu.utez.VTX_Naomy_Carranza_CA2.exception.CustomException;
import mx.edu.utez.VTX_Naomy_Carranza_CA2.model.EstadoPrestamo;
import com.examen.model.Prestamo;
import mx.edu.utez.VTX_Naomy_Carranza_CA2.model.Videojuego;
import mx.edu.utez.VTX_Naomy_Carranza_CA2.repository.PrestamoRepository;
import mx.edu.utez.VTX_Naomy_Carranza_CA2.repository.VideojuegoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExamenService {

    private final VideojuegoRepository videojuegoRepository;
    private final PrestamoRepository prestamoRepository;

    public ExamenService(VideojuegoRepository videojuegoRepository, PrestamoRepository prestamoRepository) {
        this.videojuegoRepository = videojuegoRepository;
        this.prestamoRepository = prestamoRepository;
    }

    // 1. Registrar préstamo — 30 puntos
    public Prestamo registrarPrestamo(PrestamoDTO dto) {
        Videojuego vj = videojuegoRepository.findById(dto.getVideojuegoId())
                .orElseThrow(() -> new CustomException("El videojuego no existe"));

        if (!vj.isDisponible()) {
            throw new CustomException("El videojuego no está disponible");
        }

        Prestamo prestamo = new Prestamo();
        prestamo.setNombreSolicitante(dto.getNombreSolicitante());
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamo.setFechaDevolucion(null);
        prestamo.setEstado(EstadoPrestamo.ACTIVO);
        prestamo.setVideojuego(vj);

        vj.setDisponible(false);
        videojuegoRepository.save(vj);

        return prestamoRepository.save(prestamo);
    }

    // 2. Registrar devolución — 10 puntos (AQUÍ ESTÁ EL ERROR DE LÓGICA INTENCIONAL)
    public Prestamo registrarDevolucion(Long videojuegoId) {
        // ERROR INTENCIONAL: Se usa el ID del videojuego para buscar en prestamoRepository
        // en lugar de buscar por el ID del préstamo o consultar los préstamos activos del videojuego.
        Prestamo prestamo = prestamoRepository.findById(videojuegoId)
                .orElseThrow(() -> new CustomException("El préstamo no existe"));

        if (prestamo.getEstado() != EstadoPrestamo.ACTIVO) {
            throw new CustomException("El préstamo no está activo");
        }

        prestamo.setEstado(EstadoPrestamo.DEVUELTO);
        prestamo.setFechaDevolucion(LocalDate.now());

        Videojuego vj = prestamo.getVideojuego();
        vj.setDisponible(true);
        videojuegoRepository.save(vj);

        return prestamoRepository.save(prestamo);
    }

    // 3. Cancelar préstamo — 25 puntos
    public Prestamo cancelarPrestamo(Long prestamoId) {
        Prestamo prestamo = prestamoRepository.findById(prestamoId)
                .orElseThrow(() -> new CustomException("El préstamo no existe"));

        if (prestamo.getEstado() != EstadoPrestamo.ACTIVO) {
            throw new CustomException("El préstamo no está activo");
        }

        prestamo.setEstado(EstadoPrestamo.CANCELADO);

        Videojuego vj = prestamo.getVideojuego();
        vj.setDisponible(true);
        videojuegoRepository.save(vj);

        return prestamoRepository.save(prestamo);
    }

    // 4. Listar videojuegos disponibles — 5 puntos
    public List<Videojuego> listarDisponibles() {
        return videojuegoRepository.findByDisponibleTrue();
    }
}