package fruteria.ProviderImp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fruteria.DTO.OrigenDTO;
import fruteria.Entity.OrigenEntity;
import fruteria.Provider.OrigenProvider;
import fruteria.Repository.OrigenRepository;

@Service
public class OrigenProviderImp implements OrigenProvider {

	@Autowired
	private OrigenRepository origenRepository;

	@Override
	public ResponseEntity<List<OrigenDTO>> getOrigenList() {
		if (origenRepository.findAll().isEmpty()) {

			return ResponseEntity.notFound().build();
		}
		List<OrigenDTO> origen = origenRepository.findAll().stream().map(this::OrigenEntityToDto)
				.collect(Collectors.toList());
		return ResponseEntity.ok(origen);
	}

	@Override
	public ResponseEntity<List<OrigenDTO>> getOrigenByPais(String pais) {
		if (origenRepository.findByPais(pais).isEmpty()) {
			return ResponseEntity.notFound().build();

		}

		List<OrigenDTO> origen = origenRepository.findByPais(pais).stream().map(this::OrigenEntityToDto)
				.collect(Collectors.toList());
		return ResponseEntity.ok(origen);
	}

	@Override
	public ResponseEntity<OrigenDTO> getOrigenById(Long id) {
		Optional<OrigenEntity> origen = origenRepository.findById(id);
		if (!origen.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(OrigenEntityToDto(origen.get()));

	}

	@Override
	public String createOrigen(String pais, String ciudad) {
		if (!origenRepository.findByPais(pais).isEmpty()) {
			return "El pais ya existe";
		}

		if (!origenRepository.findByCiudad(ciudad).isEmpty()) {
			return "La ciudad ya existe";
		}

		OrigenEntity origenEntity = new OrigenEntity();
		origenEntity.setPais(pais);
		origenEntity.setCiudad(ciudad);
		origenRepository.save(origenEntity);
		return "Origen creado";
	}

	@Override
	public String updateOrigen(Long id, String pais, String ciudad) {
		Optional<OrigenEntity> origen = origenRepository.findById(id);
		if (!origen.isPresent()) {
			return "El origen no existe";
		}
		origen.get().setPais(pais);
		origen.get().setCiudad(ciudad);
		origenRepository.save(origen.get());
		return "Origen actualizado";

	}

	@Override
	public boolean deleteOrigen(Long id) {
		if (!origenRepository.existsById(id)) {
			return false;
		}
		
		origenRepository.deleteById(id);
		return true;
	}

	private OrigenDTO OrigenEntityToDto(OrigenEntity origenEntity) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(origenEntity, OrigenDTO.class);
	}

}
