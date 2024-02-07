package fruteria.ProviderImp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import fruteria.DTO.ClienteDTO;
import fruteria.Entity.ClienteEntity;
import fruteria.Provider.ClienteProvider;
import fruteria.Repository.ClienteRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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

	public byte[] getClienteReport() {
		byte[] reportContent = null;
		File file;
		try {
			file = ResourceUtils.getFile(getClass().getResource("/reports/Clientes.jrxml").getFile());

			JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(getClientesList().getBody());
			Map<String, Object> parameters = new HashMap<>();

			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
			reportContent = JasperExportManager.exportReportToPdf(jasperPrint);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}

		return reportContent;
	}
}
