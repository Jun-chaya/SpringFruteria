package fruteria.DTO;

import java.time.LocalDate;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReciboDTO {

	private Long id;
	private Long idCliente;
	private LocalDate fecha;

}
