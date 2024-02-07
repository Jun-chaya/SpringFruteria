package fruteria.ProviderImp;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fruteria.DTO.ItemDTO;
import fruteria.Entity.ItemEntity;
import fruteria.Provider.ItemProvider;
import fruteria.Repository.CategoriaRepository;
import fruteria.Repository.ItemRepository;
import fruteria.Repository.MarcaRepository;
import fruteria.Repository.OrigenRepository;

@Service
public class ItemProviderImp implements ItemProvider {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private MarcaRepository marcaRepository;

	@Autowired
	private OrigenRepository origenRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public ResponseEntity<List<ItemDTO>> getAllItems() {
		if (itemRepository.findAll().isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		List<ItemDTO> items = itemRepository.findAll().stream().map(this::ItemEntityToDto).collect(Collectors.toList());
		return ResponseEntity.ok(items);
	}

	// TODO Arreglar el metodo de encontrar items por marca y completar los siguientes metodos
	@Override
	public ResponseEntity<List<ItemDTO>> getItemsByMarca(Long marcaId) {
		if (!marcaRepository.existsById(marcaId)) {
			return ResponseEntity.notFound().build();
		}
		List<ItemDTO> items = itemRepository.findAll().stream().map(this::ItemEntityToDto)
				.collect(Collectors.toList());
		return ResponseEntity.ok(items);
	}

	@Override
	public ResponseEntity<List<ItemDTO>> getItemsByOrigen(Long origenId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<ItemDTO>> getItemsByCategoria(Long categoriaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ItemDTO> getItemById(Long id) {
		Optional<ItemEntity> item = itemRepository.findById(id);
		if (!item.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(ItemEntityToDto(item.get()));
	}

	@Override
	public boolean saveItem(String nombre, Long marcaId, Long origenId, Long categoriaId, Double precio,
			String fechaCaducidad) {
		if (!marcaRepository.existsById(marcaId)) {
			return false;
		}
		ItemEntity item = new ItemEntity();
		item.setNombre(nombre);
		item.setMarca(marcaRepository.findById(marcaId).get());
		// item.setOrigen(origenRepository.findById(origenId).get())
		// item.setCategoria(categoriaRepository.findById(categoriaId).get());
		item.setPrecio(precio);
		item.setFechaProduccion(LocalDate.now().toString());
		item.setFechaCaducidad(fechaCaducidad);
		itemRepository.save(item);
		return false;
	}

	@Override
	public String deleteItem(Long id) {
		if (!itemRepository.existsById(id)) {
			return "Item no encontrado";
		}
		itemRepository.deleteById(id);
		return "Item eliminado";
	}

	@Override
	public String updatePrice(Long id, Double price) {
		if (!itemRepository.existsById(id)) {
			return "Item no encontrado";
		}
		itemRepository.findById(id).get().setPrecio(price);
		return "Precio actualizado";
	}

	@Override
	public String updateItem(Long id, String nombre, Long marcaId, Long origenId, Long categoriaId, Double precio) {
		if (!itemRepository.existsById(id)) {
			return "Item no encontrado";
		}
		if (!marcaRepository.existsById(marcaId)) {
			return "Marca no encontrada";
		}
		ItemEntity updatedItem = itemRepository.findById(id).get();
		updatedItem.setNombre(nombre);
		updatedItem.setMarca(marcaRepository.findById(marcaId).get());
		// updatedItem.setOrigen(origenRepository.findById(origenId).get());
		// updatedItem.setCategoria(categoriaRepository.findById(categoriaId).get());
		updatedItem.setPrecio(precio);
		itemRepository.save(updatedItem);
		return "Item actualizado";

	}

	@Override
	public boolean setFechaCaducidad(Long id, String fechaCaducidad) {
		if (!itemRepository.existsById(id)) {
			return false;
		}
		itemRepository.findById(id).get().setFechaCaducidad(fechaCaducidad);
		return true;
	}

	@Override
	public boolean setFechaProduccion(Long id, String fechaProduccion) {
		if (!itemRepository.existsById(id)) {
			return false;
		}
		itemRepository.findById(id).get().setFechaProduccion(fechaProduccion);
		return true;

	}

	private ItemDTO ItemEntityToDto(ItemEntity item) {
		ItemDTO itemDTO = new ItemDTO();
		ModelMapper modelMapper = new ModelMapper();

		modelMapper.createTypeMap(String.class, LocalDate.class);
		modelMapper.addConverter(context -> LocalDate.parse((String) context.getSource()), String.class,
				LocalDate.class);

		return itemDTO;
	}

	private ItemEntity itemDtotoItemEntity(ItemDTO itemDTO) {
		ModelMapper modelMapper = new ModelMapper();
		ItemEntity itemEntity = modelMapper.map(itemDTO, ItemEntity.class);

		modelMapper.createTypeMap(LocalDate.class, String.class);
		modelMapper.addConverter(context -> ((LocalDate) context.getSource()).toString(), LocalDate.class,
				String.class);

		return itemEntity;
	}
}
