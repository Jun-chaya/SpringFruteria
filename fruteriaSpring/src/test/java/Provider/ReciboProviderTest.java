package Provider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import fruteria.Entity.ClienteEntity;
import fruteria.Entity.ReciboEntity;
import fruteria.ProviderImp.ReciboProviderImp;
import fruteria.Repository.ClienteRepository;
import fruteria.Repository.ReciboRepository;

@ExtendWith(MockitoExtension.class)
public class ReciboProviderTest {

	@Mock
	private ReciboRepository repository;

	@InjectMocks
	private ReciboProviderImp reciboProvider;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void getRecibosListTest() {
		ReciboEntity recibo = new ReciboEntity();
		recibo.setId(1L);
		recibo.setCliente(new ClienteEntity(1L, "nom1"));
		recibo.setFecha("2021-01-01");

		List<ReciboEntity> reciboList = new ArrayList<ReciboEntity>();
		reciboList.add(recibo);

		when(repository.findAll()).thenReturn(reciboList);
		assertEquals(1, reciboProvider.getRecibosList().getBody().size());
		verify((repository), times(2)).findAll();
	}

	@Test
	public void getReciboByIdTest() {
		ReciboEntity recibo = new ReciboEntity();
		recibo.setId(1L);
		recibo.setCliente(new ClienteEntity(1L, "nom1"));
		recibo.setFecha("2021-01-01");

		when(repository.findById(1L)).thenReturn(java.util.Optional.of(recibo));
		assertEquals(1L, reciboProvider.getReciboById(1L).getBody().getId());
	}

	@Test
	public void deleteReciboTest() {
		ReciboEntity recibo = new ReciboEntity();
		recibo.setId(1L);
		recibo.setCliente(new ClienteEntity(1L, "nom1"));
		recibo.setFecha("2021-01-01");

		when(repository.existsById(1L)).thenReturn(true);
		assertEquals(true, reciboProvider.deleteRecibo(1L));
		verify(repository).deleteById(1L);
	}

	
	//TODO	conseguir mockear el optional de clienteRepository.findById
	@Test
	public void createReciboTest() {
		ReciboEntity recibo = new ReciboEntity();
		recibo.setId(1L);
		// recibo.setCliente(mock(ClienteEntity.class));
		recibo.setFecha("2021-01-01");

		when(reciboProvider.createRecibo(1L)).thenReturn(
				String.format("Recibo creado con id %d para el cliente %s el dia %s ", 1L, "nom1", "2021-01-01"));
		// when(repository.save(recibo)).thenReturn(recibo);
		assertEquals("Cliente para el recibo no encontrado", reciboProvider.createRecibo(1L));
	}

	//TODO	conseguir mockear el optional de clienteRepository.findById
	@Test
	public void updateReciboTest() {
		ReciboEntity recibo = new ReciboEntity();
		recibo.setId(1L);
		// recibo.setCliente(mock(ClienteEntity.class));
		recibo.setFecha("2021-01-01");

		ClienteRepository mockedClienteRepository = mock(ClienteRepository.class, Mockito.RETURNS_DEEP_STUBS);

		when(mockedClienteRepository.existsById(1L)).thenReturn(true);
		when(mockedClienteRepository.findById(1L)).thenReturn(Optional.of(new ClienteEntity(1L, "nom1")));

		when(repository.existsById(1L)).thenReturn(true);
		when(repository.findById(1L)).thenReturn(java.util.Optional.of(recibo));
		when(reciboProvider.updateRecibo(1L, 2L)).thenReturn(
				String.format("Recibo actualizado con id %d para el cliente %s el dia %s ", 1L, "nom1", "2021-01-01"));
		when(repository.save(recibo)).thenReturn(recibo);
		assertEquals("Cliente para el recibo no encontrado", reciboProvider.updateRecibo(1L, 2L));
	}
}