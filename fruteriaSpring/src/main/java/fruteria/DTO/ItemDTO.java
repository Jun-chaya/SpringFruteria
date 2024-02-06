package fruteria.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

	private Long id;
	private Long marcaId;
	private String nombre;
	private LocalDate fechaProduccion;
	private LocalDate fechaCaducidad;
	private Double precio;
	private Long origenId;
	private Long categoriaId;
}
