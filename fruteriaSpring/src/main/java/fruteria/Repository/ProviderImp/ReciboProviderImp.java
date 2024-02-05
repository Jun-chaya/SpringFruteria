package fruteria.Repository.ProviderImp;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
	public List<ReciboDTO> getRecibosList() {
		return repository.findAll().stream().map(this::reciboEntityToDTO).collect(Collectors.toList());
	}

	@Override
	public ReciboDTO getReciboById(Long id) {
		Optional<ReciboEntity> reciboOpt = repository.findById(id);
		if (reciboOpt.isPresent()) {
			return reciboOpt.map(this::reciboEntityToDTO).get();
		} else {
			return null;
		}
	}

	@Override
	public ReciboDTO createRecibo(Long idCliente, LocalDate	fecha) {
		ReciboEntity reciboEntity = new ReciboEntity();
		Optional<ClienteEntity> cliente = clienteProvider.findById(idCliente);
		reciboEntity.setCliente(cliente.get());
		reciboEntity.setFecha(fecha.toString());
		reciboEntity = repository.save(reciboEntity);
		return reciboEntityToDTO(reciboEntity);
	}

	@Override
	public ReciboDTO updateRecibo(Long id, Long idCliente, LocalDate fecha) {
		
			Optional<ClienteEntity> cliente = clienteProvider.findById(idCliente);
			
			ReciboEntity reciboEntity = new ReciboEntity();
			reciboEntity.setCliente(cliente.get());
			reciboEntity.setFecha(fecha.toString());
			repository.save(reciboEntity);
			return reciboEntityToDTO(reciboEntity);
		
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
		ReciboDTO reciboDTO = modelMapper.map(reciboEntity, ReciboDTO.class);
		return reciboDTO;
	}

	private ReciboEntity reciboDTOToEntity(ReciboDTO recibo) {
		ModelMapper modelMapper = new ModelMapper();
		ReciboEntity reciboEntity = modelMapper.map(recibo, ReciboEntity.class);
		return reciboEntity;
	}

}
