package fruteria.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
	public List<ReciboDTO> getRecibosList() {
		return reciboProvider.getRecibosList();
	}

	@GetMapping("/{id}")
	public ReciboDTO getReciboById(@RequestParam Long id) {
		return reciboProvider.getReciboById(id);
	}

	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public ReciboDTO createRecibo(@RequestParam Long clienteId,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
		return reciboProvider.createRecibo(clienteId, fecha);
	}

	@PatchMapping("/update/{id}")
	public ReciboDTO updateRecibo(@RequestParam Long id, @RequestParam Long clienteId,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
		return reciboProvider.updateRecibo(id, clienteId, fecha);
	}

	@DeleteMapping("/delete/{id}")
	public boolean deleteRecibo(@RequestParam Long id) {
		return reciboProvider.deleteRecibo(id);
	}
}
