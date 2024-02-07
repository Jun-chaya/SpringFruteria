package fruteria.Provider;

import java.util.List;

import org.springframework.http.ResponseEntity;

import fruteria.DTO.OrigenDTO;

public interface OrigenProvider {
	ResponseEntity<List<OrigenDTO>> getOrigenList();

	ResponseEntity<List<OrigenDTO>> getOrigenByPais(String pais);
	
	ResponseEntity<OrigenDTO> getOrigenById(Long id);

	String createOrigen(String pais, String ciudad);

	String updateOrigen(Long id, String pais, String ciudad);

    boolean deleteOrigen(Long id);

    
}
