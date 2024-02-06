package fruteria.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineaReciboDTO {

	private Long id;
	private Long reciboId;
	private Long itemId;
	private Integer cantidad;
	
}
