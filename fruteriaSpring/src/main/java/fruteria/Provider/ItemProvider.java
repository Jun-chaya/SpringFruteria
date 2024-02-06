package fruteria.Provider;

import java.util.List;

import org.springframework.http.ResponseEntity;

import fruteria.DTO.ItemDTO;

public interface ItemProvider {

	ResponseEntity<List<ItemDTO>> getAllItems();

	ResponseEntity<List<ItemDTO>> getItemsByMarca(Long marcaId);

	ResponseEntity<List<ItemDTO>> getItemsByOrigen(Long origenId);

	ResponseEntity<List<ItemDTO>> getItemsByCategoria(Long categoriaId);

	ResponseEntity<ItemDTO> getItemById(Long id);

	boolean saveItem(String nombre, Long marcaId, Long origenId, Long categoriaId, Double precio,
			String fechaCaducidad);

	String deleteItem(Long id);

	String updatePrice(Long id, Double price);

	String updateItem(Long id, String nombre, Long marcaId, Long origenId, Long categoriaId, Double precio);

	boolean setFechaCaducidad(Long id, String fechaCaducidad);

	boolean setFechaProduccion(Long id, String fechaProduccion);

}
