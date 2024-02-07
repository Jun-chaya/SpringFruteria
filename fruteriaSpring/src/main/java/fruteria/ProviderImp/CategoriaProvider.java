package fruteria.ProviderImp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fruteria.DTO.CategoriaDTO;
import fruteria.Entity.CategoriaEntity;
import fruteria.Repository.CategoriaRepository;

@Service
public class CategoriaProvider implements fruteria.Provider.CategoriaProvider {

	@Autowired
	private CategoriaRepository repository;

	@Override
	public ResponseEntity<List<CategoriaDTO>> getCategoriasList() {
		if (repository.findAll().isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		List<CategoriaDTO> categorias = repository.findAll().stream().map(this::CategoriaEntityToDto)
				.collect(Collectors.toList());
		return ResponseEntity.ok(categorias);
	}

	@Override
	public ResponseEntity<CategoriaDTO> getCategoriaById(Long id) {
		Optional<CategoriaEntity> categoriaEntity = repository.findById(id);
		if (!categoriaEntity.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(CategoriaEntityToDto(categoriaEntity.get()));
	}

	@Override
	public String createCategoria(String nombre) {
		CategoriaEntity categoriaEntity = new CategoriaEntity();
		categoriaEntity.setNombre(nombre);
		repository.save(categoriaEntity);
		return "Categoria creada";
	}

	@Override
	public String deleteCategoria(Long id) {
		if (!repository.existsById(id)) {
			return "Categoria no encontrada";
		}
		repository.deleteById(id);
		return "Categoria eliminada";

	}

	CategoriaDTO CategoriaEntityToDto(fruteria.Entity.CategoriaEntity categoriaEntity) {
		ModelMapper modelMapper = new ModelMapper();
		CategoriaDTO categoriaDTO = modelMapper.map(categoriaEntity, CategoriaDTO.class);

		return categoriaDTO;
	}
}
