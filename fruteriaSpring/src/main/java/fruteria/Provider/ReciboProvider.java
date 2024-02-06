package fruteria.Provider;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;

import fruteria.DTO.ReciboDTO;

public interface ReciboProvider {
	ResponseEntity<List<ReciboDTO>> getRecibosList();

	ResponseEntity<ReciboDTO> getReciboById(Long id);

	String createRecibo(Long idCliente);

	String updateRecibo(Long id, Long idCliente);

	boolean deleteRecibo(Long id);
}
