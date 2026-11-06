package mx.edu.utez.VTX_Naomy_Carranza_CA2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrestamoDTO {

    @NotNull(message = "El ID del videojuego no puede ser nulo")
    private Long videojuegoId;

    @NotBlank(message = "El nombre del solicitante no puede estar vacío")
    private String nombreSolicitante;
}
