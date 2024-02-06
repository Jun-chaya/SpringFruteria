package fruteria.Provider;

import java.util.List;

import org.springframework.http.ResponseEntity;

import fruteria.DTO.LineaReciboDTO;

public interface LineaReciboProvider {

	ResponseEntity<List<LineaReciboDTO>> getLineaRecibosDeUnRecibo(Long reciboId);

	ResponseEntity<List<LineaReciboDTO>> getLineaRecibosDeUnCliente(Long clienteId);

	ResponseEntity<List<LineaReciboDTO>> getLineaRecibosConItem(Long itemId);

	boolean saveLineaRecibo(Long reciboId, Long itemId, Integer cantidad);

	String deleteLineaRecibo(Long id);

	String updateLineaRecibo(Long id, Long reciboId, Long itemId, Integer cantidad);

}
