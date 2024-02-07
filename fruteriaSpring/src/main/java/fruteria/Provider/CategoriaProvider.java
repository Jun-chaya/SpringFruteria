package fruteria.Provider;

import java.util.List;

import org.springframework.http.ResponseEntity;

import fruteria.DTO.CategoriaDTO;

public interface CategoriaProvider {

	ResponseEntity<List<CategoriaDTO>> getCategoriasList();
	
	ResponseEntity<CategoriaDTO> getCategoriaById(Long id);
	
	String createCategoria(String nombre);
	
	String deleteCategoria(Long id);
}
