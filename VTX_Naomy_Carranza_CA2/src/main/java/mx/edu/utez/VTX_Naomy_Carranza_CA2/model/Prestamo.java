package mx.edu.utez.VTX_Naomy_Carranza_CA2.model;

import jakarta.persistence.*;
import lombok.*;
import mx.edu.utez.VTX_Naomy_Carranza_CA2.model.EstadoPrestamo;
import mx.edu.utez.VTX_Naomy_Carranza_CA2.model.Videojuego;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreSolicitante;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    @Enumerated(EnumType.STRING)
    private EstadoPrestamo estado;

    @ManyToOne
    @JoinColumn(name = "videojuego_id")
    private Videojuego videojuego;
}