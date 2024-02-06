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

import fruteria.DTO.LineaReciboDTO;
import fruteria.Provider.LineaReciboProvider;

@RestController
@RequestMapping("/lineaRecibo")
@CrossOrigin(origins = "*")
public class LineaReciboController {

	@Autowired
	private LineaReciboProvider lineaReciboProvider;
	
	@GetMapping("/recibo/{id}")
	public ResponseEntity<List<LineaReciboDTO>> getLineaRecibosDeUnRecibo(@PathVariable Long id) {
		return lineaReciboProvider.getLineaRecibosDeUnRecibo(id);
	}
	
	@GetMapping("/cliente/{id}")
	public ResponseEntity<List<LineaReciboDTO>> getLineaRecibosDeUnCliente(@PathVariable Long id) {
		return lineaReciboProvider.getLineaRecibosDeUnCliente(id);
	}
	@GetMapping("/item/{id}")
	public ResponseEntity<List<LineaReciboDTO>> getLineaRecibosConItem(@PathVariable Long id) {
		return lineaReciboProvider.getLineaRecibosConItem(id);
	}
	
	@PostMapping("/add")
	public boolean saveLineaRecibo(Long reciboId, Long itemId, Integer cantidad) {
		return lineaReciboProvider.saveLineaRecibo(reciboId, itemId, cantidad);
	}
	
	@PatchMapping("/update/{id}")
	public String updateLineaRecibo(Long id, Long reciboId, Long itemId, Integer cantidad) {
		return lineaReciboProvider.updateLineaRecibo(id, reciboId, itemId, cantidad);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteLineaRecibo(@PathVariable Long id) {
		return lineaReciboProvider.deleteLineaRecibo(id);
	}
}
