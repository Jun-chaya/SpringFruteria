package fruteria.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fruteria.DTO.ReciboDTO;
import fruteria.Provider.ReciboProvider;

@RestController
@RequestMapping("/recibo")
@CrossOrigin(origins = "*")
public class ReciboController {

	@Autowired
	private ReciboProvider reciboProvider;

	@GetMapping("/lista")
	public ResponseEntity<List<ReciboDTO>> getRecibosList() {
		return reciboProvider.getRecibosList();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ReciboDTO> getReciboById(@PathVariable Long id) {
		return reciboProvider.getReciboById(id);
	}

	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public String createRecibo(@RequestParam Long clienteId) {

		return reciboProvider.createRecibo(clienteId);
	}

	@PatchMapping("/update/{id}")
	public String updateRecibo(@PathVariable Long id, @RequestParam Long clienteId) {

		return reciboProvider.updateRecibo(id, clienteId);
	}

	@DeleteMapping("/delete/{id}")
	public boolean deleteRecibo(@PathVariable Long id) {
		return reciboProvider.deleteRecibo(id);
	}
}
