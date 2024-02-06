package fruteria.Repository.ProviderImp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fruteria.DTO.ClienteDTO;
import fruteria.Entity.ClienteEntity;
import fruteria.Provider.ClienteProvider;
import fruteria.Repository.ClienteRepository;

@Service
public class ClienteProviderImp implements ClienteProvider {

	@Autowired
	ClienteRepository repository;

	@Override
	public ResponseEntity<List<ClienteDTO>> getClientesList() {
		if (repository.findAll().isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity
				.ok(repository.findAll().stream().map(this::ClienteEntityToDTO).collect(Collectors.toList()));
	}

	@Override
	public ResponseEntity<ClienteDTO> getClienteById(Long id) {
		Optional<ClienteEntity> clienteOpt = repository.findById(id);
		if (!clienteOpt.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(ClienteEntityToDTO(clienteOpt.get()));

	}

	@Override
	public boolean saveCliente(String nombre) {
		ClienteEntity cliente = new ClienteEntity();
		cliente.setNombre(nombre);
		repository.save(cliente);
		return true;
	}

	private ClienteDTO ClienteEntityToDTO(ClienteEntity clienteEntity) {
		return new ModelMapper().map(clienteEntity, ClienteDTO.class);
	}

	@Override
	public String deleteCliente(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return "Cliente eliminado";
		} else {
			return "Cliente no encontrado";
		}

	}

	@Override
	public String updateCliente(Long id, String nombre) {
		if (repository.existsById(id)) {
			ClienteEntity cliente = repository.findById(id).get();
			cliente.setNombre(nombre);
			repository.save(cliente);
			return "Cliente actualizado";
		} else {
			return "Cliente no encontrado";
		}
	}

}
