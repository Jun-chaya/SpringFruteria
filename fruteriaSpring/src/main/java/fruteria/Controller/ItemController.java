package fruteria.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fruteria.DTO.ItemDTO;
import fruteria.Provider.ItemProvider;

@RequestMapping("/item")
@CrossOrigin(origins = "*")
@RestController
public class ItemController {

	@Autowired
	private ItemProvider itemProvider;

	@GetMapping("/lista")
	public ResponseEntity<List<ItemDTO>> getItemsList() {
		return itemProvider.getAllItems();
	}
	@GetMapping("/marca/{id}")
	public ResponseEntity<List<ItemDTO>> getItemsByMarca(@PathVariable Long id) {
		return itemProvider.getItemsByMarca(id);
	}
	@GetMapping("/origen/{id}")
	public ResponseEntity<List<ItemDTO>> getItemsByOrigen(@PathVariable Long id) {
		return itemProvider.getItemsByOrigen(id);
	}
	@GetMapping("/categoria/{id}")
	public ResponseEntity<List<ItemDTO>> getItemsByCategoria(@PathVariable Long id) {
		return itemProvider.getItemsByCategoria(id);
	}
	@GetMapping("/{id}")
	public ResponseEntity<ItemDTO> getItemById(@PathVariable Long id) {
		return itemProvider.getItemById(id);
	}
	
	@PostMapping("/add")
	public boolean saveItem(String nombre, Long marcaId, Long origenId, Long categoriaId, Double precio,
			String fechaCaducidad) {
		return itemProvider.saveItem(nombre, marcaId, origenId, categoriaId, precio, fechaCaducidad);
	}
	
	@PatchMapping("/update/{id}")
	public String updateItem(@PathVariable Long id, String nombre, Long marcaId, Long origenId, Long categoriaId, Double precio) {
		return itemProvider.updateItem(id, nombre, marcaId, origenId, categoriaId, precio);
	}
	
	@PatchMapping("/updateFechaCad/{id}")
	public boolean setFechaCaducidad(@PathVariable Long id, String fechaCaducidad) {
		return itemProvider.setFechaCaducidad(id, fechaCaducidad);
	}
	
	@PatchMapping("/updateFechaProd/{id}")
	public boolean setFechaProduccion(@PathVariable Long id, String fechaProduccion) {
		return itemProvider.setFechaProduccion(id, fechaProduccion);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteItem(@PathVariable Long id) {
		return itemProvider.deleteItem(id);
	}
}
