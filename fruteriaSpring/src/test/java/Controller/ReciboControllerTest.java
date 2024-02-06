package Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import fruteria.Controller.ReciboController;
import fruteria.DTO.ReciboDTO;
import fruteria.Provider.ReciboProvider;

public class ReciboControllerTest {

	private MockMvc mockMvc;

	@Mock
	private ReciboProvider reciboProvider;

	@InjectMocks
	private ReciboController reciboController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(reciboController).build();
	}

	@Test
	public void getRecibosListTest() throws Exception {
		ReciboDTO recibo = new ReciboDTO();
		recibo.setId(1L);
		recibo.setIdCliente(1L);
		recibo.setFecha(LocalDate.now());

		ReciboDTO recibo2 = new ReciboDTO();
		recibo2.setId(2L);
		recibo2.setIdCliente(2L);
		recibo2.setFecha(LocalDate.now());

		List<ReciboDTO> reciboList = Arrays.asList(recibo, recibo2);
		ResponseEntity<List<ReciboDTO>> response = ResponseEntity.ok(reciboList);

		when(reciboProvider.getRecibosList()).thenReturn(response);

		mockMvc.perform(get("/recibo/lista")).andExpect(status().isOk()).andExpect(result -> {
			String content = result.getResponse().getContentAsString();
			List<ReciboDTO> list = new ObjectMapper().readValue(content, List.class);
			assertEquals(2, list.size());
		});

		verify(reciboProvider).getRecibosList();

	}

	@Test
	public void getReciboByIdTest() throws Exception {
		ReciboDTO recibo = new ReciboDTO();
		recibo.setId(1L);
		recibo.setIdCliente(1L);
		recibo.setFecha(LocalDate.now());

		ResponseEntity<ReciboDTO> response = ResponseEntity.ok(recibo);

		when(reciboProvider.getReciboById(1L)).thenReturn(response);

		mockMvc.perform(get("/recibo/1")).andExpect(status().isOk()).andExpect(result -> {
			String content = result.getResponse().getContentAsString();
			ObjectMapper mapper = new ObjectMapper();
			mapper.findAndRegisterModules();
			ReciboDTO reciboDTO = mapper.readValue(content, ReciboDTO.class);
			assertEquals(1L, reciboDTO.getId());
		});

		verify(reciboProvider).getReciboById(1L);

	}

	@Test
	public void createReciboTest() throws Exception {
		when(reciboProvider.createRecibo(1L))
				.thenReturn("Recibo creado con id 1 para el cliente 1 el dia " + LocalDate.now());

		mockMvc.perform(post("/recibo/add?clienteId=1")).andExpect(status().isCreated());
		verify(reciboProvider).createRecibo(1L);
	}
	@Test
	public void updateReciboTest() throws Exception {
		when(reciboProvider.updateRecibo(1L, 1L))
				.thenReturn("Recibo con el id 1 actualizado para el cliente 1 el dia " + LocalDate.now());

		mockMvc.perform(patch("/recibo/update/1?clienteId=1")).andExpect(status().isOk());
		verify(reciboProvider).updateRecibo(1L, 1L);
	}
	
	@Test
	public void deleteReciboTest() throws Exception {
		when(reciboProvider.deleteRecibo(1L)).thenReturn(true);

		mockMvc.perform(delete("/recibo/delete/1")).andExpect(status().isOk());
		verify(reciboProvider).deleteRecibo(1L);
	}

}
