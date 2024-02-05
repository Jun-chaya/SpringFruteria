package fruteria.Provider;

import java.time.LocalDate;
import java.util.List;

import fruteria.DTO.ReciboDTO;

public interface ReciboProvider {
	List<ReciboDTO> getRecibosList();

	ReciboDTO getReciboById(Long id);

	ReciboDTO createRecibo(Long idCliente, LocalDate fecha);

	ReciboDTO updateRecibo(Long id, Long idCliente, LocalDate fecha);

	boolean deleteRecibo(Long id);
}
