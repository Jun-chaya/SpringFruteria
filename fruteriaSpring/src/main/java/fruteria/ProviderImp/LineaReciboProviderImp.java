package fruteria.ProviderImp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fruteria.DTO.LineaReciboDTO;
import fruteria.Entity.ItemEntity;
import fruteria.Entity.LineaReciboEntity;
import fruteria.Provider.LineaReciboProvider;
import fruteria.Repository.ClienteRepository;
import fruteria.Repository.ItemRepository;
import fruteria.Repository.LineaReciboRepository;
import fruteria.Repository.ReciboRepository;

@Service
public class LineaReciboProviderImp implements LineaReciboProvider {

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	ReciboRepository reciboRepository;

	@Autowired
	LineaReciboRepository repository;
	
	@Autowired
	ItemRepository itemRepository;

	// TODO arreglar este metodo con el findAll
	@Override
	public ResponseEntity<List<LineaReciboDTO>> getLineaRecibosDeUnRecibo(Long reciboId) {
		if (!reciboRepository.findById(reciboId).isPresent()) {
			return ResponseEntity.notFound().build();
		}

		List<LineaReciboDTO> lineas = repository.findAll().stream().map(this::LineaReciboEntityToDto).collect(Collectors.toList());
		return ResponseEntity.ok(lineas);

	}

	@Override
	public ResponseEntity<List<LineaReciboDTO>> getLineaRecibosDeUnCliente(Long clienteId) {
		if (!clienteRepository.findById(clienteId).isPresent()) {
			return ResponseEntity.notFound().build();
		}

		List<LineaReciboDTO> lineas = repository.findAll().stream().map(this::LineaReciboEntityToDto).collect(Collectors.toList());
		return ResponseEntity.ok(lineas);
	}

	@Override
	public ResponseEntity<List<LineaReciboDTO>> getLineaRecibosConItem(Long itemId) {
		if (!itemRepository.findById(itemId).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		List<LineaReciboDTO> lineas = repository.findAll().stream().map(this::LineaReciboEntityToDto).collect(Collectors.toList());
		return ResponseEntity.ok(lineas);
	}

	@Override
	public boolean saveLineaRecibo(Long reciboId, Long itemId, Integer cantidad) {
		if (!reciboRepository.findById(reciboId).isPresent()) {
			return false;
		}

		if (!reciboRepository.findById(itemId).isPresent()) {
			return false;
		}

		LineaReciboEntity lineaReciboEntity = new LineaReciboEntity();
		lineaReciboEntity.setRecibo(reciboRepository.findById(reciboId).get());
		lineaReciboEntity.setItem(new ItemEntity());
		lineaReciboEntity.setCantidad(cantidad);
		repository.save(lineaReciboEntity);
		return true;
	}

	@Override
	public String deleteLineaRecibo(Long id) {
		if (!repository.existsById(id)) {
			return "Linea de recibo no encontrada";
		}

		repository.deleteById(id);
		return "Linea de recibo eliminada";

	}

	@Override
	public String updateLineaRecibo(Long id, Long reciboId, Long itemId, Integer cantidad) {
		if (!repository.existsById(id)) {
			return "Linea de recibo no encontrada";
		}

		LineaReciboEntity lineaReciboEntity = repository.findById(id).get();
		lineaReciboEntity.setRecibo(reciboRepository.findById(reciboId).get());
		lineaReciboEntity.setItem(new ItemEntity());
		lineaReciboEntity.setCantidad(cantidad);
		repository.save(lineaReciboEntity);
		return "Linea de recibo actualizada";
	}

	private LineaReciboEntity lineaReciboDTOToEntity(LineaReciboDTO lineaReciboDTO) {
		ModelMapper modelMapper = new ModelMapper();
		LineaReciboEntity lineaReciboEntity = modelMapper.map(lineaReciboDTO, LineaReciboEntity.class);
		
		return lineaReciboEntity;
	}

	private LineaReciboDTO LineaReciboEntityToDto(LineaReciboEntity lineaReciboEntity) {
		ModelMapper modelMapper = new ModelMapper();
		LineaReciboDTO lineaReciboDTO = modelMapper.map(lineaReciboEntity, LineaReciboDTO.class);
		return lineaReciboDTO;

	}
}