package fruteria.Provider;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import fruteria.DTO.ClienteDTO;

public interface ClienteProvider {

	List<ClienteDTO> getClientesList();

	ClienteDTO getClienteById(Long id);

	boolean saveCliente(String nombre);

	String deleteCliente(Long id);

	String updateCliente(Long id, String nombre);
}
