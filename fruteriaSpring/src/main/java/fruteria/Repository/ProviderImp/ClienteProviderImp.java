package fruteria.Repository.ProviderImp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import fruteria.DTO.ClienteDTO;
import fruteria.Entity.ClienteEntity;
import fruteria.Provider.ClienteProvider;
import fruteria.Repository.ClienteRepository;

public class ClienteProviderImp implements ClienteProvider {

	
	@Autowired
	ClienteRepository repository;
	
	@Override
	public List<ClienteDTO> getClientesList() {
		
	System.out.println(repository.count());
	return repository.findAll().stream().map(this::ClienteEntityToDTO).collect(Collectors.toList());
	}

	private ClienteDTO ClienteEntityToDTO(ClienteEntity clienteEntity) {
		return new ModelMapper().map(clienteEntity, ClienteDTO.class);
	}

	

}
