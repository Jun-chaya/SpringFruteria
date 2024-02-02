package fruteria.Repository.ProviderImp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import fruteria.DTO.MarcaDTO;
import fruteria.Entity.MarcaEntity;
import fruteria.Repository.MarcaRepository;

public class MarcaProviderImp {

	private MarcaRepository marcaRepository;

	public MarcaProviderImp(MarcaRepository marcaRepository) {
		this.marcaRepository = marcaRepository;
	}

	public List<MarcaDTO> getMarcasList() {
		List<MarcaEntity> marcas = marcaRepository.findAll();
		return marcas.stream().map(this::marcaEntityToDTO).collect(Collectors.toList());
				
	}
	
	private MarcaDTO marcaEntityToDTO(MarcaEntity marcaEntity) {
		ModelMapper modelMapper = new ModelMapper();
		MarcaDTO marcaDTO = modelMapper.map(marcaEntity, MarcaDTO.class);
		return marcaDTO;
	}
}
