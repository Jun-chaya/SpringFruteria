package fruteria.ProviderImp;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fruteria.DTO.ReciboDTO;
import fruteria.Entity.ClienteEntity;
import fruteria.Entity.ReciboEntity;
import fruteria.Provider.ReciboProvider;
import fruteria.Repository.ClienteRepository;
import fruteria.Repository.ReciboRepository;

@Service
public class ReciboProviderImp implements ReciboProvider {

	@Autowired
	private ReciboRepository repository;

	@Autowired
	private ClienteRepository clienteProvider;

	@Override
	public ResponseEntity<List<ReciboDTO>> getRecibosList() {
		if (repository.findAll().isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity
				.ok(repository.findAll().stream().map(this::reciboEntityToDTO).collect(Collectors.toList()));
	}

	@Override
	public ResponseEntity<ReciboDTO> getReciboById(Long id) {

		Optional<ReciboEntity> reciboOpt = repository.findById(id);
		if (!reciboOpt.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(reciboEntityToDTO(reciboOpt.get()));

	}

	@Override
	public String createRecibo(Long idCliente) {
		Optional<ClienteEntity> cliente = clienteProvider.findById(idCliente);
		if (!cliente.isPresent()) {
			return "Cliente para el recibo no encontrado";
		}

		ReciboEntity reciboEntity = new ReciboEntity();

		reciboEntity.setCliente(cliente.get());
		reciboEntity.setFecha(LocalDate.now().toString());

		reciboEntity = repository.save(reciboEntity);
		return String.format("Recibo creado con id %d para el cliente %s el dia %s ", reciboEntity.getId(),
				reciboEntity.getCliente().getNombre(), reciboEntity.getFecha());
	}

	@Override
	public String updateRecibo(Long id, Long idCliente) {
		Optional<ReciboEntity> recibo = repository.findById(id);
		if (!recibo.isPresent()) {
			return "Recibo no encontrado";
		}

		Optional<ClienteEntity> cliente = clienteProvider.findById(idCliente);
		if (!cliente.isPresent()) {
			return "Cliente para el recibo no encontrado";
		}

		recibo.get().setCliente(cliente.get());
		recibo.get().setFecha(LocalDate.now().toString());
		repository.save(recibo.get());
		return String.format("Recibo con el id %d actualizado para el cliente %s el dia %s ", recibo.get().getId(),
				recibo.get().getCliente().getNombre(), recibo.get().getFecha());

	}

	@Override
	public boolean deleteRecibo(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	private ReciboDTO reciboEntityToDTO(ReciboEntity reciboEntity) {
		ModelMapper modelMapper = new ModelMapper();

		modelMapper.createTypeMap(String.class, LocalDate.class);
		modelMapper.addConverter(context -> LocalDate.parse((String) context.getSource()), String.class,
				LocalDate.class);

		ReciboDTO reciboDTO = modelMapper.map(reciboEntity, ReciboDTO.class);
		return reciboDTO;
	}

	private ReciboEntity reciboDTOToEntity(ReciboDTO recibo) {
		ModelMapper modelMapper = new ModelMapper();
		ReciboEntity reciboEntity = modelMapper.map(recibo, ReciboEntity.class);
		return reciboEntity;
	}

}
