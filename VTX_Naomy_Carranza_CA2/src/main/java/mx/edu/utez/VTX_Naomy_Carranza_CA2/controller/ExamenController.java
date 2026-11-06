package mx.edu.utez.VTX_Naomy_Carranza_CA2.controller;

import mx.edu.utez.VTX_Naomy_Carranza_CA2.dto.PrestamoDTO;
import mx.edu.utez.VTX_Naomy_Carranza_CA2.model.Prestamo;
import mx.edu.utez.VTX_Naomy_Carranza_CA2.model.Videojuego;
import mx.edu.utez.VTX_Naomy_Carranza_CA2.service.ExamenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ExamenController {

    private final ExamenService servicio;

    public ExamenController(ExamenService servicio) {
        this.servicio = servicio;
    }

    @PostMapping("/prestamos")
    public ResponseEntity<Prestamo> registrarPrestamo(@RequestBody @Valid PrestamoDTO payload) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(servicio.registrarPrestamo(payload));
    }

    @PutMapping("/prestamos/devolucion/{videojuegoId}")
    public ResponseEntity<Prestamo> registrarDevolucion(@PathVariable Long videojuegoId) {
        return ResponseEntity.ok(servicio.registrarDevolucion(videojuegoId));
    }

    @PutMapping("/prestamos/cancelar/{id}")
    public ResponseEntity<Prestamo> cancelarPrestamo(@PathVariable Long id) {
        return ResponseEntity.ok(servicio.cancelarPrestamo(id));
    }

    @GetMapping("/videojuegos/disponibles")
    public ResponseEntity<List<Videojuego>> listarDisponibles() {
        return ResponseEntity.ok(servicio.listarDisponibles());
    }
}