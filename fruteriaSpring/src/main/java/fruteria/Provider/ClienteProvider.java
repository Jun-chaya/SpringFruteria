package fruteria.Provider;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import fruteria.DTO.ClienteDTO;
import fruteria.DTO.ReciboDTO;

public interface ClienteProvider {

	ResponseEntity<List<ClienteDTO>> getClientesList();

	ResponseEntity<ClienteDTO> getClienteById(Long id);

	boolean saveCliente(String nombre);

	String deleteCliente(Long id);

	String updateCliente(Long id, String nombre);
	
	byte[] getClienteReport();
}
