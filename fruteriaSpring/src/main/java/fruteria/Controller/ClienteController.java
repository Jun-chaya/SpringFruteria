package fruteria.Controller;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import fruteria.DTO.ClienteDTO;
import fruteria.Provider.ClienteProvider;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.repo.Resource;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "*")
public class ClienteController {
	
    @Autowired
    private ClienteProvider clienteProvider;

	@GetMapping("/lista")
	public ResponseEntity<List<ClienteDTO>> getClientesList() {
		return clienteProvider.getClientesList();
	}
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> getClienteById(@PathVariable Long id) {
		return clienteProvider.getClienteById(id);
	}

	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public boolean saveCliente(@RequestParam String nombre) {
		return clienteProvider.saveCliente(nombre);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteCliente(@PathVariable Long id) {
		return clienteProvider.deleteCliente(id);
	}
	
	@PatchMapping("/update/{id}")
	public String updateCliente(@PathVariable Long id, @RequestParam String nombre) {
		return clienteProvider.updateCliente(id, nombre);
	}
	
	 @GetMapping("item-report/")
	  public ResponseEntity<ByteArrayResource> getItemReport() throws JRException, IOException {

	    byte[] reportContent =  clienteProvider.getClienteReport();

	    ByteArrayResource resource = new ByteArrayResource(reportContent);

	    
	    return ResponseEntity.ok()
	        .contentType(MediaType.APPLICATION_PDF)
	        .contentLength(resource.contentLength())
	        .header(HttpHeaders.CONTENT_DISPOSITION,
	            ContentDisposition.attachment()
	                .filename("cliente-report.pdf")
	                .build().toString())
	        .body(resource);
	  }
	
}
